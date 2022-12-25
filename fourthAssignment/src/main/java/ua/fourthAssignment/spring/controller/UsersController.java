package ua.fourthAssignment.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ua.fourthAssignment.spring.service.user.UserService;

@Controller
public class UsersController {
    private final UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users-list")
    public String getUsersListPage(Model model) {
        model.addAttribute("usersList", userService.getUsersForList());
        return "usersList";
    }
}
