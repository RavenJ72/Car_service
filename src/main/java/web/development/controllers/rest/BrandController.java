package web.development.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import web.development.services.dto.input.BrandDto;

import web.development.services.interfaces.publicApi.BrandService;

import java.util.List;
@RestController
@RequestMapping("/api/brand")
public class BrandController {
    private final BrandService brandService;
    @Autowired
    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }
    @GetMapping("/findAll")
    public List<BrandDto> getAll() {
        return brandService.findAll();
    }

    @PostMapping("/create")
    public BrandDto save(@RequestBody String brandName){
        return brandService.save(brandName);
    }

    @GetMapping("/findByName/{name}")
    public BrandDto findByName(@RequestParam String name){
        return brandService.findByName(name);
    }
}
