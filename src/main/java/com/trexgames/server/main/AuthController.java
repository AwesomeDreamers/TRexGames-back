package com.trexgames.server.main;

import com.trexgames.server.exception.ApiResponse;
import com.trexgames.server.exception.ResponseCode;
import com.trexgames.server.request.EmailVerifyDto;
import com.trexgames.server.request.SignupDto;
import com.trexgames.server.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    
    // 이메일 중복 확인 및 이메일 전송

    /**
     * @param dto
     * (인증코드, 10분 제한)
     */
    @PostMapping("/email-code")
    public ResponseEntity<ApiResponse<Void>> validateMember(@RequestBody EmailVerifyDto dto) {
        try {
            authService.validateMember(dto);
            return ApiResponse.create(ResponseCode.SUCCESS);
        } catch (IllegalArgumentException e) {
            return ApiResponse.create(ResponseCode.DUPLICATE_EMAIL);
        } catch (Exception e) {
            e.getStackTrace();
            return ApiResponse.create(ResponseCode.SIGN_IN_FAILED);
        }
    }

    // 이메일 코드 인증
    @PostMapping("/code-verify")
    public ResponseEntity<ApiResponse<Void>> codeVerify(@RequestBody EmailVerifyDto dto) {
        try {
            authService.codeVerify(dto);
            return ApiResponse.create(ResponseCode.SUCCESS);
        } catch (IllegalStateException e) {
            return ApiResponse.create(ResponseCode.VALIDATION_FAILED);
        } catch (Exception e) {
            return ApiResponse.create(ResponseCode.SIGN_IN_FAILED);
        }
    }

    // 회원 가입
    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<Void>> signup (@RequestBody SignupDto dto) {
        authService.signup(dto);
        return ApiResponse.create(ResponseCode.SUCCESS);
    }


}
