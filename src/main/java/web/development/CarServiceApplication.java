package web.development;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.config.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import web.development.dto.output.ModelOutputDto;
import web.development.dto.output.OfferOutputDto;
import web.development.dto.output.UserOutputDto;
import web.development.models.entities.Model;
import web.development.models.entities.Offer;
import web.development.models.entities.User;

@SpringBootApplication
public class CarServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarServiceApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper(){
		ModelMapper modelMapper = new ModelMapper();

		modelMapper.getConfiguration()
				.setFieldMatchingEnabled(true)
				.setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);


		// ModelOutputDto
		TypeMap<Model, ModelOutputDto> typeMapModelOutput = modelMapper.createTypeMap(Model.class, ModelOutputDto.class);
		typeMapModelOutput.addMappings(m->m.map(src -> src.getBrand().getName(), ModelOutputDto::setBrand_name));

		// ModelOutputDto
		TypeMap<Offer, OfferOutputDto> typeMapOfferDetails = modelMapper.createTypeMap(Offer.class, OfferOutputDto.class);
		typeMapOfferDetails.addMappings(m->m.map(src -> src.getSeller().getId(), OfferOutputDto::setSeller_id));
		typeMapOfferDetails.addMappings(m->m.map(src -> src.getModel().getId(), OfferOutputDto::setModel_id));

		// UserOutPutDto
		TypeMap<User, UserOutputDto> typeMapUser = modelMapper.createTypeMap(User.class, UserOutputDto.class);
		typeMapUser.addMappings(m->m.map(src -> src.getRole().getId(), UserOutputDto::setRole_id));

		return modelMapper;
	}


}
