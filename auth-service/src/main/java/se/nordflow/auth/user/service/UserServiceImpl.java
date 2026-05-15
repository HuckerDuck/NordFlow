package se.nordflow.auth.user.service;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import se.nordflow.auth.security.JWT.JwtTokenProvider;
import se.nordflow.auth.user.common.exception.EmailAlreadyExistsException;
import se.nordflow.auth.user.dto.LoginDTO;
import se.nordflow.auth.user.dto.RegisterDTO;
import se.nordflow.auth.user.dto.ResponseDTO;
import se.nordflow.auth.user.model.User;
import se.nordflow.auth.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public ResponseDTO registerAUser(RegisterDTO registerDTO) {
        if (userRepository.existsByEmail(registerDTO.email())){
            throw new EmailAlreadyExistsException(registerDTO.email());
        }

        User newUser = new User();

        newUser.setEmail(registerDTO.email());
        newUser.setPassword(passwordEncoder.encode(registerDTO.password()));
        newUser.setPhoneNumber(registerDTO.phoneNumber());
        newUser.setFirstName(registerDTO.firstName());
        newUser.setLastName(registerDTO.lastName());

        userRepository.save(newUser);

        return new ResponseDTO(newUser.getEmail(), newUser.getFirstName(), newUser.getLastName(), newUser.getPhoneNumber());
    }

    @Override
    public String loginAUser(LoginDTO loginDTO) {
        User user = userRepository.findByEmail(loginDTO.email())
                .orElseThrow(()-> new RuntimeException("User with that email was not found"));

        if (!passwordEncoder.matches(loginDTO.password(), user.getPassword())) {
            throw new RuntimeException("Password isn't correct");


        }

        return jwtTokenProvider.generateToken(userDetailsService.loadUserByUsername(user.getEmail()));
    }
}
