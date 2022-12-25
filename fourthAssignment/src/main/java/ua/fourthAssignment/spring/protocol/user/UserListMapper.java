package ua.fourthAssignment.spring.protocol.user;

import org.mapstruct.Mapper;
import ua.fourthAssignment.spring.model.User;

@Mapper(componentModel = "spring")
public interface UserListMapper {
    UserListDto userToUserListDto(User user);
}
