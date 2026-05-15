package se.nordflow.auth.user.service;

import se.nordflow.auth.user.dto.LoginDTO;
import se.nordflow.auth.user.dto.RegisterDTO;
import se.nordflow.auth.user.dto.ResponseDTO;

public interface UserService {
    ResponseDTO registerAUser (RegisterDTO registerDTO);
    String loginAUser (LoginDTO loginDTO);
}
