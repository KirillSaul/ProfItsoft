package ua.fourthAssignment.spring.protocol.user;

import org.mapstruct.Mapper;
import ua.fourthAssignment.spring.model.User;

@Mapper(componentModel = "spring")
public interface LoginFormMapper {
    User loginFormToUser(LoginForm loginForm);
}
