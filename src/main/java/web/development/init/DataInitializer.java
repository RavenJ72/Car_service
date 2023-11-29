package web.development.init;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import web.development.models.enums.EngineType;
import web.development.models.enums.ModelCategory;
import web.development.models.enums.RoleType;
import web.development.models.enums.TransmissionType;
import web.development.services.dto.input.*;
import web.development.services.implementations.ModelServiceImpl;
import web.development.services.interfaces.internalApi.ModelInternalService;
import web.development.services.interfaces.publicApi.*;

import java.math.BigDecimal;


@Component
public class DataInitializer implements CommandLineRunner {

    private final RoleService roleService;

    private final UserService userService;

    private final BrandService brandService;

    private final ModelServiceImpl modelService;
    private final OfferService offerService;
    private final ModelMapper modelMapper;

    @Autowired
    public DataInitializer(RoleService roleService, UserService userService, BrandService brandService, ModelServiceImpl modelService, OfferService offerService, ModelMapper modelMapper) {
        this.roleService = roleService;
        this.userService = userService;
        this.brandService = brandService;
        this.modelService = modelService;
        this.offerService = offerService;
        this.modelMapper = modelMapper;
    }


    @Override
    public void run(String... args) throws Exception {
        seedData();
    }


    public void seedData() {


        String adminRoleId = roleService.save(new RoleDto(RoleType.ADMIN)).getId();
        String userRoleID = roleService.save(new RoleDto(RoleType.USER)).getId();

        String adminUser = userService.save(new UserDto( "Alex", "alex2023", "Alex", "Johnson", true, "https://7960777a-2fd1-4b07-8bbb-896e98c4659c.selcdn.net/upload/prod_add3/703/product-303703-1.jpg", "ADMIN")).getUsername();
        String user1 = userService.save(new UserDto( "Elena", "elena2023", "Elena", "Smith", true, "https://s00.yaplakal.com/pics/pics_original/5/0/6/17827605.jpg", "USER")).getUsername();
        String user2 = userService.save(new UserDto( "Michael", "mike2023", "Michael", "Brown", true, "https://avatars.mds.yandex.net/i?id=e708784c502029fd3580605e76109216_l-5761336-images-thumbs&n=27&h=480&w=480", "USER")).getUsername();
        String user3 = userService.save(new UserDto( "Sophia", "sophia2023", "Sophia", "Williams", true, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQKBMglABe03_aY-m4umgPieAOtHvJS4kqAfg&usqp=CAU", "USER")).getUsername();
        String user4 = userService.save(new UserDto( "Daniel", "danny2023", "Daniel", "Miller", true, "https://avatars.mds.yandex.net/i?id=49bc5ea56e4920b47934ff306d8fe17d_l-5233110-images-thumbs&n=27&h=480&w=480", "USER")).getUsername();
        String user5 = userService.save(new UserDto( "Isabella", "izzy2023", "Isabella", "Davis", true, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSHMIdkvbqVXbLMCptwSOIK50Z41QuPRI1PPg&usqp=CAU", "USER")).getUsername();
        String user6 = userService.save(new UserDto( "Вася", "11111", "giga", "wqe", true, "https://s0.rbk.ru/v6_top_pics/media/img/5/31/756806793338315.png", "USER")).getUsername();
        String user7 = userService.save(new UserDto( "Антон", "11111", "sassy", "sssss", true, "https://webtous.ru/wp-content/uploads/2023/04/shedevrum-obzor.jpg", "USER")).getUsername();


        brandService.save("TESLA");
        brandService.save("AUDI");
        brandService.save("BMW");
        brandService.save( "FORD");



        String teslaModel1 = modelService.save(new ModelDto( "Model S", ModelCategory.CAR, "Электрический седан", 2012, 2023, "TESLA")).getId();

        String teslaModel2 = modelService.save(new ModelDto( "Model 3", ModelCategory.CAR, "Электрический седан", 2017, 2023, "TESLA")).getId();

        String teslaModel3 = modelService.save(new ModelDto("Model X", ModelCategory.CAR, "Электрический кроссовер", 2015, 2023, "TESLA")).getId();

        // Создаем модели для AUDI
        String audiModel1 =  modelService.save(new ModelDto( "A3", ModelCategory.CAR, "Компактный седан", 1996, 2023, "AUDI")).getId();

        String audiModel2 = modelService.save( new ModelDto( "Q7", ModelCategory.CAR, "Полноразмерный кроссовер", 2005, 2023, "AUDI")).getId();

        String audiModel3 =  modelService.save(new ModelDto( "R8", ModelCategory.CAR, "Спорткар", 2006, 2023, "AUDI")).getId();

        // Создаем модели для BMW
        String bmwModel1 =  modelService.save(new ModelDto("3 Series", ModelCategory.CAR, "Седан среднего класса", 1975, 2023, "BMW")).getId();

        String bmwModel2 = modelService.save( new ModelDto("X5", ModelCategory.CAR, "Кроссовер", 1999, 2023, "BMW")).getId();

        String bmwModel3 =    modelService.save(new ModelDto("i8", ModelCategory.CAR, "Гибридный спорткар", 2014, 2023, "BMW")).getId();

        // Создаем модели для Ford
        String fordModel1 = modelService.save( new ModelDto( "Mustang", ModelCategory.CAR, "Спорткар", 1964, 2023, "FORD")).getId();

        String fordModel2 = modelService.save(new ModelDto( "Explorer", ModelCategory.CAR, "Кроссовер", 1990, 2023, "FORD")).getId();

        String fordModel3 =   modelService.save(new ModelDto( "F-150", ModelCategory.CAR, "Пикап", 1975, 2023, "FORD")).getId();
//
//
        OfferDto offerDto4 = new OfferDto("Отдаю за банку компота",EngineType.ELECTRIC,"https://s.auto.drom.ru/img4/catalog/photos/fullsize/tesla/model_s/tesla_model_s_182016.jpg",2200,new BigDecimal("50000.00"),TransmissionType.AUTOMATIC,2000,teslaModel1,user1);
        OfferDto offerDto5 = new OfferDto("Протеккает крыша",EngineType.ELECTRIC,"https://avatars.mds.yandex.net/get-verba/1540742/2a0000017761d1c8de5ba80a8148f180432f/cattouch",1223,new BigDecimal("5000000.00"),TransmissionType.AUTOMATIC,2000,teslaModel2,user5);
        OfferDto offerDto6 = new OfferDto("Не работают тормаза, продаю со скидкой",EngineType.GASOLINE,"https://autopragmat.ru/upload/delight.webpconverter/upload/iblock/fe7/n1cgecm0cfzy6f41dshweodk6jilsoeu.jpg.webp?1693040709143612",20120,new BigDecimal("12312310.00"),TransmissionType.MANUAL,2000,fordModel2,user6);
        OfferDto offerDto7 = new OfferDto("Новая, не бита, не крашена!",EngineType.GASOLINE,"https://autoreview.ru/images/Article/1658/Article_165836_860_575.jpg",20220,new BigDecimal("10000000.00"),TransmissionType.MANUAL,2000,bmwModel2,user7);
//
        offerService.save(offerDto4);
        offerService.save(offerDto5);
        offerService.save(offerDto6);
        offerService.save(offerDto7);
//
//        modelService.findAll().forEach(System.out::println);
//        userService.findAll().forEach(System.out::println);
//        offerService.findAll().forEach(System.out::println);


    }
}
