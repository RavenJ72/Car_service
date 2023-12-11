package web.development.controllers.view;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import web.development.services.dto.input.UserDto;
import web.development.services.interfaces.publicApi.UserService;

import java.security.Principal;

@Controller
@RequestMapping("/")
public class AuthController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }


    @PostMapping("/login-error")
    public String onFailedLogin(
            @ModelAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY) String username,
            RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY, username);
        redirectAttributes.addFlashAttribute("badCredentials", true);

        return "redirect:/login";
    }


    @GetMapping("/register")
    public String register(Model model){
        return "user-add";
    }
    @ModelAttribute("userDto")
    public UserDto initUserDtoModel() {
        return new UserDto();
    }

    @PostMapping("/register")
    public String addUser(@Valid UserDto userDto, BindingResult bindingResult, RedirectAttributes redirectAttributes,Principal principal) {

        userDto.setIsActive(false);
        userDto.setRole("USER");

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userDto", userDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userDto", bindingResult);

            return "redirect:/register";
        }
        userService.save(userDto);
        if(userService.findByUsername(principal.getName()).getRole().equals("ADMIN")){
            return "redirect:/user/";
        }

        return "redirect:/login";



    }









}
