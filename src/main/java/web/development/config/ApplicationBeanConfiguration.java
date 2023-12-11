package web.development.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import web.development.models.entities.Model;
import web.development.models.entities.Offer;
import web.development.models.entities.User;
import web.development.services.dto.input.OfferDto;
import web.development.services.dto.input.UserDto;
import web.development.services.dto.view.ModelOutputDto;
import web.development.services.dto.view.OfferOutputDto;
import web.development.services.dto.view.UserOutputDto;


@Configuration
public class ApplicationBeanConfiguration {


    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);


        // ModelOutputDto
        TypeMap<Model, ModelOutputDto> typeMapModelOutput = modelMapper.createTypeMap(Model.class, ModelOutputDto.class);
        typeMapModelOutput.addMappings(m->m.map(src -> src.getBrand().getName(), ModelOutputDto::setBrand_name));

        // OfferOutputDto
        TypeMap<Offer, OfferOutputDto> typeMapOfferDetails = modelMapper.createTypeMap(Offer.class, OfferOutputDto.class);
        typeMapOfferDetails.addMappings(m->m.map(src -> src.getSeller().getId(), OfferOutputDto::setSeller_id));

        TypeMap<Offer, OfferDto> typeMapOfferDto = modelMapper.createTypeMap(Offer.class, OfferDto.class);
        typeMapOfferDto.addMappings(m->m.map(src -> src.getSeller().getId(), OfferDto::setSeller));


        // UserOutPutDto
        TypeMap<User, UserOutputDto> typeMapUser = modelMapper.createTypeMap(User.class, UserOutputDto.class);
        typeMapUser.addMappings(m->m.map(src -> src.getRole().getRole(), UserOutputDto::setRole_id));

        TypeMap<User, UserDto> typeMapUserToDto = modelMapper.createTypeMap(User.class, UserDto.class);
        typeMapUserToDto.addMappings(m->m.map(src -> src.getRole().getRole(), UserDto::setRole));



        return modelMapper;
    }

    @Bean
    public LocalValidatorFactoryBean validator() {
        LocalValidatorFactoryBean validatorFactoryBean = new LocalValidatorFactoryBean();
        return validatorFactoryBean;
    }
}