package ua.fourthAssignment.spring.protocol.user;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ua.fourthAssignment.spring.model.User;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-12-25T18:36:34+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.5 (Amazon.com Inc.)"
)
@Component
public class UserListMapperImpl implements UserListMapper {

    @Override
    public UserListDto userToUserListDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserListDto userListDto = new UserListDto();

        userListDto.setName( user.getName() );
        userListDto.setUsername( user.getUsername() );

        return userListDto;
    }
}
