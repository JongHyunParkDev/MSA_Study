package com.example.userservice.vo;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class Greeting {
    // application 으로 가져오기 2
    @Value("${greeting.message}")
    private String message;
}
