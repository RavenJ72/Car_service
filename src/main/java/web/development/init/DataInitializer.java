package web.development.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import web.development.models.enums.EngineType;
import web.development.models.enums.ModelCategory;
import web.development.models.enums.RoleType;
import web.development.models.enums.TransmissionType;
import web.development.services.dto.input.*;
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


        String adminRoleId = roleService.save(new RoleDto(RoleType.ADMIN)).getId();

        String userRoleID = roleService.save(new RoleDto(RoleType.USER)).getId();

        String adminUser = userService.save(new UserDto( "Alex", "alex2023", "Alex", "Johnson", true, "alexj2023", roleService.findById(adminRoleId))).getId();
        String user1 = userService.save(new UserDto( "Elena", "elena2023", "Elena", "Smith", true, "elenas123", roleService.findById(userRoleID))).getId();
        String user2 = userService.save(new UserDto( "Michael", "mike2023", "Michael", "Brown", true, "mikeb123", roleService.findById(userRoleID))).getId();
        String user3 = userService.save(new UserDto( "Sophia", "sophia2023", "Sophia", "Williams", true, "sophiaw123", roleService.findById(userRoleID))).getId();
        String user4 = userService.save(new UserDto( "Daniel", "danny2023", "Daniel", "Miller", true, "dannym123", roleService.findById(userRoleID))).getId();
        String user5 = userService.save(new UserDto( "Isabella", "izzy2023", "Isabella", "Davis", true, "izzyd123", roleService.findById(userRoleID))).getId();
        String user6 = userService.save(new UserDto( "Вася", "111", "giga", "wqe", true, "123123", roleService.findById(userRoleID))).getId();
        String user7 = userService.save(new UserDto( "Антон", "111", "jopa", "sssss", true, "123123", roleService.findById(userRoleID))).getId();
//
        brandService.save(new BrandDto("TESLA"));
        brandService.save(new BrandDto("AUDI"));
        brandService.save(new BrandDto("BMW"));
        brandService.save(new BrandDto( "FORD"));

        //brandService.findByName("BMW");

        String teslaModel1 = modelService.save(new ModelDto( "Model S", ModelCategory.CAR, "Электрический седан", 2012, 2023, brandService.findByName("TESLA"))).getId();

        String teslaModel2 = modelService.save(new ModelDto( "Model 3", ModelCategory.CAR, "Электрический седан", 2017, 2023, brandService.findByName("TESLA"))).getId();

        String teslaModel3 = modelService.save(new ModelDto("Model X", ModelCategory.CAR, "Электрический кроссовер", 2015, 2023, brandService.findByName("TESLA"))).getId();

        // Создаем модели для AUDI
        String audiModel1 =  modelService.save(new ModelDto( "A3", ModelCategory.CAR, "Компактный седан", 1996, 2023, brandService.findByName("AUDI"))).getId();

        String audiModel2 = modelService.save( new ModelDto( "Q7", ModelCategory.CAR, "Полноразмерный кроссовер", 2005, 2023, brandService.findByName("AUDI"))).getId();

        String audiModel3 =  modelService.save(new ModelDto( "R8", ModelCategory.CAR, "Спорткар", 2006, 2023, brandService.findByName("AUDI"))).getId();

        // Создаем модели для BMW
        String bmwModel1 =  modelService.save(new ModelDto("3 Series", ModelCategory.CAR, "Седан среднего класса", 1975, 2023, brandService.findByName("BMW"))).getId();

        String bmwModel2 = modelService.save( new ModelDto("X5", ModelCategory.CAR, "Кроссовер", 1999, 2023, brandService.findByName("BMW"))).getId();

        String bmwModel3 =    modelService.save(new ModelDto("i8", ModelCategory.CAR, "Гибридный спорткар", 2014, 2023, brandService.findByName("BMW"))).getId();

        // Создаем модели для Ford
        String fordModel1 = modelService.save( new ModelDto( "Mustang", ModelCategory.CAR, "Спорткар", 1964, 2023, brandService.findByName("FORD"))).getId();

        String fordModel2 = modelService.save(new ModelDto( "Explorer", ModelCategory.CAR, "Кроссовер", 1990, 2023, brandService.findByName("FORD"))).getId();

        String fordModel3 =   modelService.save(new ModelDto( "F-150", ModelCategory.CAR, "Пикап", 1975, 2023, brandService.findByName("FORD"))).getId();


        OfferDto offerDto4 = new OfferDto("very cool",EngineType.GASOLINE,"images/car/bigcar222.png",2200,new BigDecimal("50000.00"),TransmissionType.MANUAL,2000,modelService.findById(teslaModel1),userService.findById(user1));
        OfferDto offerDto5 = new OfferDto("Протеккает крыша",EngineType.GASOLINE,"images/supercar2.png",1223,new BigDecimal("5000000.00"),TransmissionType.MANUAL,2000,modelService.findById(teslaModel2),userService.findById(user5));
        OfferDto offerDto6 = new OfferDto("Не работают тормаза, продаю со скидкой",EngineType.GASOLINE,"images/supercar22323.png",20120,new BigDecimal("12312310.00"),TransmissionType.MANUAL,2000,modelService.findById(fordModel2),userService.findById(user6));
        OfferDto offerDto7 = new OfferDto("Новая, не бита, не крашена!",EngineType.GASOLINE,"images/supercar/secret/vehicle/momscar.png",20220,new BigDecimal("10000000.00"),TransmissionType.MANUAL,2000,modelService.findById(bmwModel2),userService.findById(user7));

        offerService.save(offerDto4);
        offerService.save(offerDto5);
        offerService.save(offerDto6);
        offerService.save(offerDto7);

        modelService.findAll().forEach(System.out::println);
        userService.findAll().forEach(System.out::println);
        offerService.findAll().forEach(System.out::println);





























    }
}
