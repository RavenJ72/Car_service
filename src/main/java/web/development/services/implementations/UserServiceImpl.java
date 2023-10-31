package web.development.services.implementations;

import jakarta.validation.ConstraintViolation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.development.models.entities.Role;
import web.development.services.dto.input.RoleDto;
import web.development.services.dto.input.UserDto;
import web.development.services.dto.output.UserOutputDto;
import web.development.models.entities.User;
import web.development.repositories.UserRepository;
import web.development.services.interfaces.UserService;
import web.development.util.ValidationUtilImpl;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService<String> {

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

        if (!this.validationUtil.isValid(user)) {
            this.validationUtil
                    .violations(user)
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);

        } else {
            try {
                return modelMapper.map(userRepository.saveAndFlush(modelMapper.map(user, User.class)), UserDto.class);
            } catch (Exception e) {
                System.out.println("Some thing went wrong!");
            }
        }

        return null;

    }

    @Override
    public User findById(String id) {
        return userRepository.findById(id).orElse(null);
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
        return modelMapper.map(userRepository.findByUsername(username),UserDto.class);
    }

    @Override
    public List<UserOutputDto> findUsersByActivity(Boolean isActive) {
        return userRepository.findByIsActive(isActive).stream().map(e -> modelMapper.map(e, UserOutputDto.class)).collect(Collectors.toList());
    }
}
