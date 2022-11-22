package com.example.userservice.vo;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class ResponseOrder {
    private String productId;
    private Integer qty;
    private Integer unitPrice;
    private Integer totalPrice;

    // 강의에선 Date를 사용했지만,
    // LocalDateTime 1.8 이후 부터 사용을 추천한다.
    private LocalDateTime createdAt;
//    private Date createdAt;

    private String orderId;
}
