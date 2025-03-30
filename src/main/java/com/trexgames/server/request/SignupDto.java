package com.trexgames.server.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SignupDto {
    @Email(message = "이메일 형식이 아닙니다")
    @NotBlank(message = "이메일은 필수입니다")
    private String email;

    @Size(min = 8, max = 12, message = "비밀번호는 8~12자여야 합니다")
    private String password;

    @NotBlank(message = "사용자 이름을 입력하세요")
    private String username;
}
