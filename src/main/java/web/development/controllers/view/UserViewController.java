package web.development.controllers.view;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.ParameterResolutionDelegate;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import web.development.services.dto.input.OfferDto;
import web.development.services.dto.input.UserDto;
import web.development.services.interfaces.publicApi.BrandService;
import web.development.services.interfaces.publicApi.ModelService;
import web.development.services.interfaces.publicApi.OfferService;
import web.development.services.interfaces.publicApi.UserService;

import java.security.Principal;

@Controller
public class UserViewController {

    private OfferService offerService;
    private BrandService brandService;
    private ModelService modelService;
    private UserService userService;

    @Autowired
    public void setBrandService(BrandService brandService) {
        this.brandService = brandService;
    }
    @Autowired
    public void setOfferService(OfferService offerService) {
        this.offerService = offerService;
    }
    @Autowired
    public void setModelService(ModelService modelService) {
        this.modelService = modelService;
    }
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/")
    public String usersAll(Model model){

        model.addAttribute("title","Car Service - All users");
        model.addAttribute("users",userService.findAll());

        return "user-all";
    }

    @GetMapping("/user/{id}")
    public String offerDetails(@PathVariable("id") String id, Model model, Principal principal){

        model.addAttribute("title","Car Service - User details page");
        model.addAttribute("user",userService.findUserDetailsById(id));
        UserDto currentUser = userService.findByUsername(principal.getName());
        model.addAttribute("check",currentUser.getId().equals(id) || currentUser.getRole().equals("ADMIN"));



        return "user-details";
    }



    @GetMapping("/user/edit/{id}")
    public String editUser(@PathVariable("id") String id,Model model,Principal principal){

        UserDto userDto = userService.findUserForEdit(id);
        if(userDto.getRole().equals("ADMIN") | userService.findByUsername(principal.getName()).getId().equals(id)){
            model.addAttribute("userDto",userDto);
            return "user-edit";
        }
        return "redirect:/error_not_found";

    }
    @PostMapping("/user/edit/{id}")
    public String editUser(@Valid UserDto userDto, @PathVariable("id") String id, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userDto", userDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userDto", bindingResult);
            return "redirect:/user/edit/" + id;
        }

        userService.save(userDto);
        return "redirect:/user/" + id;
    }

}
