package web.development.controllers.view;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import web.development.services.dto.input.BrandDto;
import web.development.services.dto.input.OfferDto;
import web.development.services.dto.input.UserDto;
import web.development.services.interfaces.publicApi.BrandService;
import web.development.services.interfaces.publicApi.ModelService;
import web.development.services.interfaces.publicApi.OfferService;
import web.development.services.interfaces.publicApi.UserService;

@Controller
public class BrandViewController {

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


    @GetMapping("/brand/add/")
    public String addBrand(Model model){
        model.addAttribute("brands",brandService.findAll());
        return "brand-add";
    }

    @ModelAttribute("brandDto")
    public BrandDto initUserDtoModel() {
        return new BrandDto();
    }

    @PostMapping("/brand/add/")
    public String addBrand(@Valid BrandDto brandDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("brandDto", brandDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.brandDto", bindingResult);

            return "redirect:/brand/add/";
        }
        brandService.save(brandDto.getName());
        return "redirect:/brand/add/";
    }


}
