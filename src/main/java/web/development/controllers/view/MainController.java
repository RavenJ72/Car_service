package web.development.controllers.view;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import web.development.services.dto.input.OfferDto;
import web.development.services.interfaces.publicApi.BrandService;
import web.development.services.interfaces.publicApi.ModelService;
import web.development.services.interfaces.publicApi.OfferService;
import web.development.services.interfaces.publicApi.UserService;

@Controller
@RequestMapping("/")
public class MainController {

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

    @GetMapping("/")
    public String home(@RequestParam(required = false) String brand,
                       @RequestParam(required = false) String engineType,
                       @RequestParam(required = false) String modelType,
                       @RequestParam(required = false) String transmission,
                       Model model){

        model.addAttribute("title","Car Service - Main page");
        model.addAttribute("offers",offerService.findFilteredOffers(brand,engineType,modelType,transmission));
        model.addAttribute("brands",brandService.findAll());

        model.addAttribute("selectedBrand", brand);
        model.addAttribute("selectedEngineType", engineType);
        model.addAttribute("selectedModelType", modelType);
        model.addAttribute("selectedTransmission", transmission);
        return "index";
    }

    @GetMapping("/offer/{id}")
    public String offerDetails(@PathVariable("id") String id, Model model){

        model.addAttribute("title","Car Service - Offer details page");
        model.addAttribute("offer",offerService.findOfferDetailsById(id));

        return "offer-details";
    }

    @GetMapping("/offer/add/")
    public String addOffer(Model model){
        model.addAttribute("models",modelService.findAll());
        model.addAttribute("users",userService.findAll());
        return "offer-add";
    }
    @ModelAttribute("offerDto")
    public OfferDto initCompany() {
        return new OfferDto();
    }

    @PostMapping("/offer/add/")
    public String addOffer(@Valid OfferDto offerDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("offerDto", offerDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.offerDto", bindingResult);
            return "redirect:/offer/add/";
        }
        offerService.save(offerDto);
        return "redirect:/";
    }



    @GetMapping("/offer/edit/{id}")
    public String editOffer(@PathVariable("id") String id,Model model){
        model.addAttribute("offerDto",offerService.findOfferForEdit(id));
        model.addAttribute("models",modelService.findAll());
        model.addAttribute("users",userService.findAll());

        return "offer-edit";
    }
    @PostMapping("/offer/edit/{id}")
    public String editOffer(@Valid OfferDto offerDto,@PathVariable("id") String id, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("offerDto", offerDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.offerDto", bindingResult);
            return "redirect:/offer/edit/" + id;
        }

        offerService.save(offerDto);
        return "redirect:/offer/" + id;
    }




}
