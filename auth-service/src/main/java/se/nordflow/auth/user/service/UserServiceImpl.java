package se.nordflow.auth.user.service;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import se.nordflow.auth.user.common.exception.EmailAlreadyExistsException;
import se.nordflow.auth.user.dto.LoginDTO;
import se.nordflow.auth.user.dto.RegisterDTO;
import se.nordflow.auth.user.dto.ResponseDTO;
import se.nordflow.auth.user.model.user;
import se.nordflow.auth.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public ResponseDTO registerAUser(RegisterDTO registerDTO) {
        if (userRepository.existsByEmail(registerDTO.email())){
            throw new EmailAlreadyExistsException(registerDTO.email());
        }
        user newUser = new user();

        newUser.setEmail(registerDTO.email());
        newUser.setPassword(registerDTO.password());
        newUser.setPhoneNumber(registerDTO.phoneNumber());
        newUser.setFirstName(registerDTO.firstName());
        newUser.setLastName(registerDTO.lastName());

        userRepository.save(newUser);

        return new ResponseDTO(newUser.getEmail(), newUser.getFirstName(), newUser.getLastName(), newUser.getPhoneNumber());
    }

    @Override
    public Void loginAUser(LoginDTO loginDTO) {
        return null;
    }
}
