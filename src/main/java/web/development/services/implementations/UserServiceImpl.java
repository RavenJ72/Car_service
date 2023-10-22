package web.development.services.implementations;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.development.dto.input.UserDto;
import web.development.dto.output.UserOutputDto;
import web.development.models.entities.User;
import web.development.repositories.UserRepository;
import web.development.services.interfaces.UserService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService<UUID> {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDto save(UserDto user) {
        return modelMapper.map(userRepository.save(modelMapper.map(user, User.class)), UserDto.class);
    }

    @Override
    public UserDto findById(UUID id) {
        return modelMapper.map(userRepository.findById(id).orElse(null), UserDto.class);
    }

    @Override
    public List<UserOutputDto> findAll() {
        return userRepository.findAll().stream().map(e -> modelMapper.map(e, UserOutputDto.class)).collect(Collectors.toList());
    }

    @Override
    public void deleteById(UUID id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<UserOutputDto> findUsersByActivity(Boolean isActive) {
        return userRepository.findByIsActive(isActive).stream().map(e -> modelMapper.map(e, UserOutputDto.class)).collect(Collectors.toList());
    }
}
