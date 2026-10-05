package se.nordflow.auth.security.auth.service;

import se.nordflow.auth.security.auth.dto.LoginDTO;
import se.nordflow.auth.security.auth.dto.RegisterDTO;
import se.nordflow.auth.security.auth.dto.ResponseDTO;

public interface AuthService {
    ResponseDTO registerAUser (RegisterDTO registerDTO);
    String loginAUser (LoginDTO loginDTO);
}
