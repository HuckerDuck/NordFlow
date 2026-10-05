package se.nordflow.auth.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import se.nordflow.auth.user.dto.UserResponseDTO;
import se.nordflow.auth.user.model.User;
import se.nordflow.auth.user.repository.UserRepository;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    @Override
    public UserResponseDTO userInformation(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User with that email is not found"));

        return new UserResponseDTO(
                user.getFirstName(),
                user.getLastName(),
                user.getEmail()
        );
    }
}
