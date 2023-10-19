package web.development.services.implementations;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.development.dto.input.RoleDto;
import web.development.models.entities.Role;
import web.development.repositories.RoleRepository;
import web.development.services.interfaces.RoleService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService<Long> {

    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository, ModelMapper modelMapper) {
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public RoleDto save(RoleDto userRole) {
        return modelMapper.map(roleRepository.save(modelMapper.map(userRole, Role.class)), RoleDto.class);
    }

    @Override
    public RoleDto findById(Long id) {
        return modelMapper.map(roleRepository.findById(id).orElse(null), RoleDto.class);
    }

    @Override
    public List<RoleDto> findAll() {
        return roleRepository.findAll().stream().map(e -> modelMapper.map(e, RoleDto.class)).collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        roleRepository.deleteById(id);
    }
}
