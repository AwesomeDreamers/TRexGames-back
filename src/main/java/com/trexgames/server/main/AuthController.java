package com.trexgames.server.main;

import com.trexgames.server.exception.ApiResponse;
import com.trexgames.server.exception.ResponseCode;
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

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<Void>> signup (@RequestBody SignupDto dto) {
        authService.signup(dto);
        return ApiResponse.create(ResponseCode.SUCCESS);
    }


}
