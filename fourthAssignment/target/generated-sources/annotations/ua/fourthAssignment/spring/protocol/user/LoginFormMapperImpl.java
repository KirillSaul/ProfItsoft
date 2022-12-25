package ua.fourthAssignment.spring.protocol.user;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ua.fourthAssignment.spring.model.User;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-12-25T18:36:35+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.5 (Amazon.com Inc.)"
)
@Component
public class LoginFormMapperImpl implements LoginFormMapper {

    @Override
    public User loginFormToUser(LoginForm loginForm) {
        if ( loginForm == null ) {
            return null;
        }

        String username = null;
        String password = null;

        username = loginForm.getUsername();
        password = loginForm.getPassword();

        String name = null;

        User user = new User( name, username, password );

        return user;
    }
}
