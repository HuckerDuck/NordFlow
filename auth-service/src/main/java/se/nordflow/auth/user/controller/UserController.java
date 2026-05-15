package se.nordflow.auth.user.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.nordflow.auth.user.dto.LoginDTO;
import se.nordflow.auth.user.dto.RegisterDTO;
import se.nordflow.auth.user.dto.ResponseDTO;
import se.nordflow.auth.user.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> registerAUser (@Valid @RequestBody RegisterDTO dto){
        ResponseDTO response = userService.registerAUser(dto);
        return ResponseEntity.ok(response);
    }

    @PostMapping ("/login")
    public ResponseEntity<String> loginAUser (@Valid @RequestBody LoginDTO loginDTO){
        String token = userService.loginAUser(loginDTO);
        return ResponseEntity.ok(token);
    }
}
