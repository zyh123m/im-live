package org.example.user.service;

import org.example.user.dto.UserDTO;

public interface UserRpcService {

    UserDTO getUserByUsername(String username);


}
