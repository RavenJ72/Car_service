package web.development.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import web.development.models.entities.Model;
import web.development.models.entities.Offer;
import web.development.models.entities.User;
import web.development.services.dto.input.UserDto;
import web.development.services.dto.view.ModelOutputDto;
import web.development.services.dto.view.OfferOutputDto;
import web.development.services.dto.view.UserOutputDto;


@Configuration
public class ApplicationBeanConfiguration {

    @Bean
    public Validator validator(){
        return Validation
                .buildDefaultValidatorFactory()
                .getValidator();
    }

    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);


        // ModelOutputDto
        TypeMap<Model, ModelOutputDto> typeMapModelOutput = modelMapper.createTypeMap(Model.class, ModelOutputDto.class);
        typeMapModelOutput.addMappings(m->m.map(src -> src.getBrand().getName(), ModelOutputDto::setBrand_name));

        // ModelOutputDto
        TypeMap<Offer, OfferOutputDto> typeMapOfferDetails = modelMapper.createTypeMap(Offer.class, OfferOutputDto.class);
        typeMapOfferDetails.addMappings(m->m.map(src -> src.getSeller().getId(), OfferOutputDto::setSeller_id));


        // UserOutPutDto
        TypeMap<User, UserOutputDto> typeMapUser = modelMapper.createTypeMap(User.class, UserOutputDto.class);
        typeMapUser.addMappings(m->m.map(src -> src.getRole().getId(), UserOutputDto::setRole_id));

        TypeMap<User, UserDto> typeMapUserToDto = modelMapper.createTypeMap(User.class, UserDto.class);
        typeMapUserToDto.addMappings(m->m.map(src -> src.getRole().getRole(), UserDto::setRole));



        return modelMapper;
    }
}