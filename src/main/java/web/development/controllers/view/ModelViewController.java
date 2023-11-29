package web.development.controllers.view;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import web.development.services.dto.input.BrandDto;
import web.development.services.dto.input.ModelDto;
import web.development.services.interfaces.publicApi.BrandService;
import web.development.services.interfaces.publicApi.ModelService;
import web.development.services.interfaces.publicApi.OfferService;
import web.development.services.interfaces.publicApi.UserService;





@Controller
public class ModelViewController {

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


    @GetMapping("/model/add/")
    public String addBrand(Model model){
        model.addAttribute("brands",brandService.findAll());
        model.addAttribute("models",modelService.findAll());
        return "model-add";
    }

    @ModelAttribute("modelDto")
    public ModelDto initUserDtoModel() {
        return new ModelDto();
    }

    @PostMapping("/model/add/")
    public String addBrand(@Valid ModelDto modelDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {



        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("modelDto", modelDto);
            redirectAttributes.addAttribute("brands",brandService.findAll());
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.modelDto", bindingResult);

            return "redirect:/model/add/";
        }
        modelService.save(modelDto);
        return "redirect:/model/add/";
    }


}
