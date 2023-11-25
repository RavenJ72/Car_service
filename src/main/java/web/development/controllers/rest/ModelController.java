package web.development.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import web.development.services.dto.input.ModelDto;
import web.development.services.dto.view.ModelOutputDto;
import web.development.services.interfaces.publicApi.ModelService;

import java.util.List;

@RestController
@RequestMapping("/api/model")
public class ModelController {
    private final ModelService modelService;
    @Autowired
    public ModelController(ModelService modelService) {
        this.modelService = modelService;
    }

    @GetMapping("/findAll")
    public List<ModelOutputDto> getAll() {
        return modelService.findAll();
    }
    @PostMapping("/create")
    public ModelDto save(ModelDto model){
        return modelService.save(model);
    }

    @GetMapping("/findByBrandName")
    public  List<ModelOutputDto> findByBrandName(@RequestParam String brandName){
        return modelService.findByBrandName(brandName);
    }




}
