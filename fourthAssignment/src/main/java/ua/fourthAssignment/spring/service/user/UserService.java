package ua.fourthAssignment.spring.service.user;

import ua.fourthAssignment.spring.protocol.user.LoginForm;
import ua.fourthAssignment.spring.protocol.user.UserListDto;

import java.util.List;

public interface UserService {
    boolean isUserExist(LoginForm loginForm);

    List<UserListDto> getUsersForList();
}
