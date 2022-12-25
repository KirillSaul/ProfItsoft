package ua.fourthAssignment.spring.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.fourthAssignment.spring.protocol.user.LoginForm;
import ua.fourthAssignment.spring.protocol.user.LoginFormMapper;
import ua.fourthAssignment.spring.protocol.user.UserListDto;
import ua.fourthAssignment.spring.protocol.user.UserListMapper;
import ua.fourthAssignment.spring.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final LoginFormMapper loginFormMapper;
    private final UserListMapper userListMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, LoginFormMapper loginFormMapper, UserListMapper userListMapper) {
        this.userRepository = userRepository;
        this.loginFormMapper = loginFormMapper;
        this.userListMapper = userListMapper;
    }

    @Override
    public boolean isUserExist(LoginForm loginForm) {
        return userRepository.findAllUsers().contains(loginFormMapper.loginFormToUser(loginForm));
    }

    @Override
    public List<UserListDto> getUsersForList() {
        return userRepository.findAllUsers().stream().map(userListMapper::userToUserListDto).collect(Collectors.toList());
    }
}
