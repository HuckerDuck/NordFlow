package se.nordflow.auth.security.auth.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.nordflow.auth.security.auth.dto.LoginDTO;
import se.nordflow.auth.security.auth.dto.LoginResponseDTO;
import se.nordflow.auth.security.auth.dto.RegisterDTO;
import se.nordflow.auth.security.auth.dto.ResponseDTO;
import se.nordflow.auth.security.auth.service.AuthService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService userService;

    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> registerAUser (@Valid @RequestBody RegisterDTO dto){
        ResponseDTO response = userService.registerAUser(dto);
        return ResponseEntity.ok(response);
    }

    @PostMapping ("/login")
    public ResponseEntity<LoginResponseDTO> loginAUser (@Valid @RequestBody LoginDTO loginDTO){
        String token = userService.loginAUser(loginDTO);
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }
}
