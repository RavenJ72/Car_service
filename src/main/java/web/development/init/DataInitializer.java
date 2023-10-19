package web.development.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import web.development.dto.input.*;
import web.development.models.enums.EngineType;
import web.development.models.enums.ModelCategory;
import web.development.models.enums.RoleType;
import web.development.models.enums.TransmissionType;
import web.development.services.interfaces.*;

import java.math.BigDecimal;

@Component
public class DataInitializer implements CommandLineRunner {

    private final RoleService roleService;

    private final UserService userService;

    private final BrandService brandService;

    private final ModelService modelService;
    private final OfferService offerService;

    @Autowired
    public DataInitializer(RoleService roleService, UserService userService, BrandService brandService, ModelService modelService, OfferService offerService) {
        this.roleService = roleService;
        this.userService = userService;
        this.brandService = brandService;
        this.modelService = modelService;
        this.offerService = offerService;
    }


    @Override
    public void run(String... args) throws Exception {
        seedData();
    }


    public void seedData() {

        roleService.save(new RoleDto(Long.parseLong("0"), RoleType.ADMIN));
        roleService.save(new RoleDto(Long.parseLong("0"), RoleType.USER));


        UserDto userDto1 = new UserDto(Long.parseLong("0") , "test", "111", "test", "test", true, "123123", roleService.findById(Long.parseLong("1")));
        UserDto userDto2 = new UserDto(Long.parseLong("0") , "test", "111", "test", "test", true, "123123", roleService.findById(Long.parseLong("1")));

        userService.save(userDto1);
        userService.save(userDto2);


        BrandDto brandDto = new BrandDto(Long.parseLong("0"),"Tesla");
        BrandDto brandDto2 = new BrandDto(Long.parseLong("0"),"AUDI");
        BrandDto brandDto3 = new BrandDto(Long.parseLong("0"),"BMW");

        brandService.save(brandDto);
        brandService.save(brandDto2);
        brandService.save(brandDto3);

        ModelDto modelDto = new ModelDto(Long.parseLong("0"),"Y8", ModelCategory.CAR,"big big paaaniiis",23,25,brandService.findById(Long.parseLong("1")));
        modelService.save(modelDto);


        OfferDto offerDto = new OfferDto(Long.parseLong("0"),"продаю тачку не бита ни крашена", EngineType.ELECTRIC,"ссылка",2222, new BigDecimal("100"), TransmissionType.AUTOMATIC,2022,modelService.findById(Long.parseLong("1")),userService.findById(Long.parseLong("1")));
        offerService.save(offerDto);

















    }
}
