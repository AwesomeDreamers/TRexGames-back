package com.trexgames.server.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {
    @Id @GeneratedValue
    private int memberId;

    private String email;

    private String password;

    private String username;

    private String profileUrl;

    @Enumerated(EnumType.STRING)
    private RoleType role;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;
}
