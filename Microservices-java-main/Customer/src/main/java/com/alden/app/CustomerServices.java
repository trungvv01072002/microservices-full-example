package com.alden.app;

import com.alden.amqp.RabbitMQMessageProducer;
import com.alden.clients.fraud.FraudCheckResponse;
import com.alden.clients.fraud.FraudClient;
import com.alden.clients.notification.NotificationClient;
import com.alden.clients.notification.NotificationRequest;
import org.springframework.stereotype.Service;

@Service
public record CustomerServices(CustomerRepository customerRepository,
                               CustomerConfig customerConfig,
                               FraudClient fraudClient,
                               NotificationClient notificationClient,
                               RabbitMQMessageProducer rabbitMQMessageProducer
                               ) {

    public void registerNewCustomer(CustomerRegisterRequest request) {
        Customer customer = Customer.builder().firstName(request.firstName()).lastName(
                request.lastName()).email(request.email()).build();

        customerRepository.saveAndFlush(customer);

        FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getId());

        if (fraudCheckResponse.isFraud()) {
            customerRepository.delete(customer);
            throw new IllegalStateException("Customer is a fraudster");
        }

//        notificationClient.sendNotification(new NotificationRequest(customer.getId(),
//                customer.getEmail(),
//                String.format("Hi %s, welcome to Alden Bank",
//                        customer.getFirstName())));

        NotificationRequest notificationRequest = new NotificationRequest(customer.getId(),
                customer.getEmail(),
                String.format("Hi %s, welcome to Alden Bank",
                        customer.getFirstName()));

        rabbitMQMessageProducer.publish(notificationRequest,
                "internal.exchange",
                "internal.notification.routing-key");
    }
}
