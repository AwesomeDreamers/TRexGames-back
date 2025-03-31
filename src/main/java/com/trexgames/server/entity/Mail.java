package com.trexgames.server.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Getter
@Setter
@RedisHash("mail")
public class Mail {

    @Id
    private String email;
    // 난수
    private int code;
    // 10분?
    @TimeToLive(unit = TimeUnit.SECONDS)
    private long expiration = 600;

    public static Mail create(String email) {
        Mail mail = new Mail();
        mail.setEmail(email);
        mail.setCode(new Random().nextInt(900000) + 100000);
        return mail;
    }
}
