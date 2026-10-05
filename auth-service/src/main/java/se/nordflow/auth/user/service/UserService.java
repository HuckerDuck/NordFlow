package se.nordflow.auth.user.service;


import se.nordflow.auth.user.dto.UserResponseDTO;

public interface UserService {
    UserResponseDTO userInformation (String email);
}
