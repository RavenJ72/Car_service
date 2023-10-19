package web.development.services.implementations;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.development.dto.input.UserDto;
import web.development.models.entities.User;
import web.development.repositories.UserRepository;
import web.development.services.interfaces.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService<Long> {

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
    public UserDto findById(Long id) {
        return modelMapper.map(userRepository.findById(id).orElse(null), UserDto.class);
    }

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll().stream().map(e -> modelMapper.map(e, UserDto.class)).collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
