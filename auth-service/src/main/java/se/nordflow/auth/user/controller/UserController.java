package se.nordflow.auth.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.nordflow.auth.user.dto.UserResponseDTO;
import se.nordflow.auth.user.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;


    @GetMapping("/me")
    public ResponseEntity<UserResponseDTO> findUserInformation (@AuthenticationPrincipal UserDetails userDetails){
        UserResponseDTO user = userService.userInformation(userDetails.getUsername());
        return ResponseEntity.ok(user);
    }
}
