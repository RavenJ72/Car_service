package web.development;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.config.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import web.development.services.dto.output.ModelOutputDto;
import web.development.services.dto.output.OfferOutputDto;
import web.development.services.dto.output.UserOutputDto;
import web.development.models.entities.Model;
import web.development.models.entities.Offer;
import web.development.models.entities.User;

@SpringBootApplication
public class CarServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarServiceApplication.class, args);
	}
}
