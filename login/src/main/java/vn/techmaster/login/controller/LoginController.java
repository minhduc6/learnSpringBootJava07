package vn.techmaster.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.techmaster.login.dto.UserDto;
import vn.techmaster.login.exception.UserException;
import vn.techmaster.login.model.User;
import vn.techmaster.login.request.LoginRequest;
import vn.techmaster.login.service.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/")
public class LoginController {
    @Autowired
    private UserService userService;

    @GetMapping
    public String showHomePage(Model model, HttpSession session) {
        UserDto userDto = (UserDto) session.getAttribute("user");
        if (userDto != null) {
            model.addAttribute("user", userDto);
        }
        return "index";
    }

    @GetMapping("/admin")
    public String showAdminPage(Model model, HttpSession session) {

        UserDto userDto = (UserDto) session.getAttribute("user");
        if (userDto != null) {
            model.addAttribute("user", userDto);
            return "admin";
        }
        return "redirect:/";
    }

    @GetMapping("login")
    public String showLoginPage(Model model) {
        model.addAttribute("loginrequest", new LoginRequest("", ""));
        return "login";
    }

    @GetMapping("register")
    public String showRegisterPage() {
        return "register";
    }

    @GetMapping("logout")
    public String logout(HttpSession session) {
        session.setAttribute("user",null);
        session.removeAttribute("user");
        return "redirect:/";
    }

    @PostMapping("login")
    public String handleLogin(@Valid @ModelAttribute("loginrequest") LoginRequest loginRequest, BindingResult result, HttpSession session) {
        if (result.hasErrors()) {
            return "login";
        }
        User user;
        try {
            user = userService.login(loginRequest.getEmail(), loginRequest.getPassword());
            session.setAttribute("user", new UserDto(user.getId(), user.getEmail(), user.getFullname()));
            return "redirect:/";
        } catch (UserException ex) {
            switch (ex.getMessage()) {
                case "User is not found":
                    result.addError(new FieldError("loginrequest", "email", "Email does not exist"));
                    break;
                case "User is not actived":
                    result.addError(new FieldError("loginrequest", "email", "User is not actived"));
                    break;
                case "Password is incorrect":
                    result.addError(new FieldError("loginrequest", "password", "Password is incorrect"));
                    break;
            }
            return "login";
        }
    }

}
