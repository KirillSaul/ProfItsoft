package ua.fourthAssignment.spring.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ua.fourthAssignment.spring.protocol.user.LoginForm;
import ua.fourthAssignment.spring.service.user.UserService;

@Controller
public class LoginController {
    private final UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String checkLogin(@ModelAttribute LoginForm loginForm, HttpServletRequest httpServletRequest) {
        if (userService.isUserExist(loginForm)) {
            httpServletRequest.getSession().setAttribute("username", loginForm.getUsername());
            return "redirect:/greeting";
        }
        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        httpServletRequest.getSession().invalidate();
        for (var cookie : httpServletRequest.getCookies()) {
            httpServletResponse.addCookie(new Cookie(cookie.getName(), ""));
        }
        return "redirect:/login";
    }
}
