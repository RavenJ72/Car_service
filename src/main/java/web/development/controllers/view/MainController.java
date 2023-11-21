package web.development.controllers.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.development.services.interfaces.publicApi.BrandService;
import web.development.services.interfaces.publicApi.OfferService;

@Controller
@RequestMapping("/")
public class MainController {


    private OfferService offerService;
    private BrandService brandService;
    @Autowired
    public void setBrandService(BrandService brandService) {
        this.brandService = brandService;
    }
    @Autowired
    public void setOfferService(OfferService offerService) {
        this.offerService = offerService;
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
        System.out.println(brand + " " + engineType);
        return "index";
    }





}
