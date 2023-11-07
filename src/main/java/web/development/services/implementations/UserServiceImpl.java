package web.development.services.implementations;

import jakarta.validation.ConstraintViolation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.development.models.entities.Model;
import web.development.services.dto.input.ModelDto;
import web.development.services.dto.input.UserDto;
import web.development.services.dto.output.UserOutputDto;
import web.development.models.entities.User;
import web.development.repositories.UserRepository;
import web.development.services.exceptions.NotFoundException;
import web.development.services.exceptions.SaveException;
import web.development.services.exceptions.ValidationException;
import web.development.services.interfaces.internalApi.UserInternalService;
import web.development.services.interfaces.publicApi.UserService;
import web.development.util.ValidationUtilImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService<String>, UserInternalService<String> {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtilImpl validationUtil;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, ValidationUtilImpl validationUtil) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public UserDto save(UserDto user) {

        System.out.println(user.getRole().getRole().getUserRoleTypeCode());
        if (!this.validationUtil.isValid(user)) {
            String exceptionMessage = "The data is not valid:\n";
            List<String> validationErrors = new ArrayList<>(this.validationUtil
                    .violations(user)
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.toList()));

            exceptionMessage += String.join("\n", validationErrors);
            throw new ValidationException(exceptionMessage);

        } else {
            try {
                return modelMapper.map(userRepository.saveAndFlush(modelMapper.map(user, User.class)), UserDto.class);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                throw new SaveException("Failed to save the user.");
            }
        }
    }

    @Override
    public User findById(String id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()){
            throw new NotFoundException("User with this id not found");
        }
        return (User) user.get();
    }

    @Override
    public List<UserOutputDto> findAll() {
        return userRepository.findAll().stream().map(e -> modelMapper.map(e, UserOutputDto.class)).collect(Collectors.toList());
    }

    @Override
    public void deleteById(String id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDto findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if(user == null){
            throw new NotFoundException("User with this username not found");
        }
        return modelMapper.map(user,UserDto.class);
    }

    @Override
    public List<UserOutputDto> findUsersByActivity(Boolean isActive) {
        return userRepository.findByIsActive(isActive).stream().map(e -> modelMapper.map(e, UserOutputDto.class)).collect(Collectors.toList());
    }
}
