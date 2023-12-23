package web.development.controllers.view;

import jakarta.validation.Valid;
import lombok.extern.java.Log;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/")
public class MainController {

    private OfferService offerService;
    private BrandService brandService;
    private ModelService modelService;
    private UserService userService;

    private static final Logger LOG = LogManager.getLogger(Controller.class);

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

    @GetMapping("/")
    public String home(@RequestParam(required = false) String brand,
                       @RequestParam(required = false) String engineType,
                       @RequestParam(required = false) String modelType,
                       @RequestParam(required = false) String transmission,
                       Model model,Principal principal){

        model.addAttribute("title","Car Service - Main page");
        model.addAttribute("offers",offerService.findFilteredOffers(brand,engineType,modelType,transmission));
        model.addAttribute("brands",brandService.findAll());

        model.addAttribute("selectedBrand", brand);
        model.addAttribute("selectedEngineType", engineType);
        model.addAttribute("selectedModelType", modelType);
        model.addAttribute("selectedTransmission", transmission);

        String currentUserId = principal != null ? userService.findByUsername(principal.getName()).getId() : null;


        model.addAttribute("currentUserId",currentUserId);


//        System.out.println("Cash test");
//        Long currentTime = System.currentTimeMillis();
//        for (int i = 0; i < 1000; i++) {
//            offerService.findAll();
//        }
//
//        Long timeAfter = System.currentTimeMillis();
//        System.out.printf("Время 1к запросов в базу с кешем: " + String.valueOf(timeAfter-currentTime) + " мс\n");
//
//
//
//        Long currentTime2 = System.currentTimeMillis();
//        for (int i = 0; i < 1000; i++) {
//            offerService.findWithoutCash();
//        }
//
//        Long timeAfter2 = System.currentTimeMillis();
//        System.out.printf("Время 1к запросов в базу без кеша: " + String.valueOf(timeAfter2-currentTime2)+ " мс\n");
//

        

        return "index";
    }

    @GetMapping("/offer/{id}")
    public String offerDetails(@PathVariable("id") String id, Model model,Principal principal){

        model.addAttribute("title","Car Service - Offer details page");
        model.addAttribute("offer",offerService.findOfferDetailsById(id));
        String userId = userService.findByUsername(principal.getName()).getId();

        model.addAttribute("currentUserId",userId);


        return "offer-details";
    }

    @GetMapping("/error_not_found")
    public String errorPage(){
        return "not-found";
    }

    @GetMapping("/offer/add/")
    public String addOffer(Model model){
        model.addAttribute("models",modelService.findAll());
        model.addAttribute("users",userService.findAll());
        return "offer-add";
    }
    @ModelAttribute("offerDto")
    public OfferDto initCompany(Principal principal) {
        OfferDto offerDto = new OfferDto();
        if (principal != null){
            offerDto.setSeller(principal.getName());
        }
        return offerDto;
    }

    @PostMapping("/offer/add/")
    public String addOffer(@Valid OfferDto offerDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {


        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("offerDto", offerDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.offerDto", bindingResult);
            return "redirect:/offer/add/";
        }
        LOG.log(Level.INFO,"Offer added");
        offerService.save(offerDto);
        return "redirect:/";
    }



    @GetMapping("/offer/edit/{id}")
    public String editOffer(@PathVariable("id") String id,Model model,Principal principal){
        OfferDto offerDto = offerService.findOfferForEdit(id);
        UserDto userDto = userService.findByUsername(principal.getName());




        if(userDto.getRole().equals("ADMIN") | offerDto.getSeller().equals(userDto.getId())){


            model.addAttribute("offerDto",offerDto);
            model.addAttribute("models",modelService.findAll());
            model.addAttribute("users",userService.findAll());
            return "offer-edit";
        }
        LOG.log(Level.ERROR,"Access error, user " + userDto.getUsername() +  " have been redirected from edit page.");

        return "redirect:/error_not_found";
    }
    @PostMapping("/offer/edit/{id}")
    public String editOffer(@Valid OfferDto offerDtoModified,@PathVariable("id") String id, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("offerDto", offerDtoModified);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.offerDto", bindingResult);
            return "redirect:/offer/edit/" + id;
        }

        LOG.log(Level.INFO,"Profile with id " + id + " edited.");

        offerService.save(offerDtoModified);
        return "redirect:/offer/" + id;
    }

    @GetMapping("/profile")
    public String profile(Principal principal, Model model) {
        String username = principal.getName();
        model.addAttribute("user", userService.findUserDetailsByUsername(username));
        model.addAttribute("check","true");
        model.addAttribute("currentUserId",userService.findByUsername(username).getId());
        model.addAttribute("offers",offerService.findOffersBySellerUsername(username));

        LOG.log(Level.INFO,"Showing profile info for " + username);

        return "user-details";
    }




}
