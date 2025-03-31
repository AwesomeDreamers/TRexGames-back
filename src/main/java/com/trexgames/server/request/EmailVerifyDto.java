package com.trexgames.server.request;

import lombok.Data;

@Data
public class EmailVerifyDto {

    private String email;
    private int code;
}
