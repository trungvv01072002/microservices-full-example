package com.trungvv.openfeign;

import com.trungvv.openfeign.client.UserClient;
import com.trungvv.openfeign.dto.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@EnableFeignClients
@RestController
public class OpenFeignApplication {

    @Autowired
    UserClient userClient;

    @GetMapping("/getAllUsers")
    public List<ServiceResponse> getAllUsers() {
        return userClient.getUsers();
    }

    public static void main(String[] args) {
        SpringApplication.run(OpenFeignApplication.class, args);
    }

}
