package web.development.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import web.development.services.dto.input.UserDto;
import web.development.services.dto.view.UserOutputDto;
import web.development.services.interfaces.publicApi.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/findAll")
    public List<UserOutputDto> getAll() {
        return userService.findAll();
    }

    @PostMapping("/create")
    public UserDto save(@RequestBody UserDto user){
        System.out.println(user.getUsername());
        return userService.save(user);
    }

    @GetMapping("/findByUsername/{username}")
    public UserDto findByUsername(@PathVariable String username){
        return userService.findByUsername(username);
    }

    @GetMapping("/findByActivity/{isActive}")
    public List<UserOutputDto> findUsersByActivity(@PathVariable String isActive){
        return userService.findUsersByActivity(Boolean.getBoolean(isActive));
    }

}
