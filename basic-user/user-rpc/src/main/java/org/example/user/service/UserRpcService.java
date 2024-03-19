package org.example.user.service;

import org.example.user.dto.UserDTO;

import java.util.List;

public interface UserRpcService {

    UserDTO getUserByUsername(String username);

    /**
     * 更新用户头像
     * @param username 用户名
     * @param avatarUrl 头像图片链接
     * @return
     */
    Boolean updateUserAvatar(String username,String avatarUrl);

    List<UserDTO> friendList(String username,String nickname);


    void insertUser(UserDTO userDTO);


}
