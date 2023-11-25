package web.development.services.implementations;

import jakarta.validation.ConstraintViolation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.development.models.enums.RoleType;
import web.development.services.dto.input.RoleDto;
import web.development.models.entities.Role;
import web.development.repositories.RoleRepository;
import web.development.services.exceptions.NotFoundException;
import web.development.services.exceptions.SaveException;
import web.development.services.exceptions.ValidationException;
import web.development.services.interfaces.internalApi.RoleInternalService;
import web.development.services.interfaces.publicApi.RoleService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService<String>, RoleInternalService<String> {

    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;


    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository, ModelMapper modelMapper) {
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public RoleDto save(RoleDto userRole) {

            try {
                return modelMapper.map(roleRepository.saveAndFlush(modelMapper.map(userRole, Role.class)), RoleDto.class);
            } catch (Exception e) {
                throw new SaveException("Failed to save the role.");
            }

    }

    @Override
    public RoleDto findById(String id) {
        Optional<Role> role = roleRepository.findById(id);
        if (role.isEmpty()){
            throw new NotFoundException("Model with this id not found");
        }
        return modelMapper.map(role.get(),RoleDto.class);
    }

    @Override
    public List<RoleDto> findAll() {
        return roleRepository.findAll().stream().map(e -> modelMapper.map(e, RoleDto.class)).collect(Collectors.toList());
    }

    @Override
    public void deleteById(String id) {
        roleRepository.deleteById(id);
    }

    @Override
    public Role findByRoleType(RoleType roleType) {
        return roleRepository.findAllByRole(roleType);
    }
}
