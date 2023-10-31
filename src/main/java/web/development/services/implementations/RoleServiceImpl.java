package web.development.services.implementations;

import jakarta.validation.ConstraintViolation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.development.services.dto.input.RoleDto;
import web.development.models.entities.Role;
import web.development.repositories.RoleRepository;
import web.development.services.interfaces.internalApi.RoleInternalService;
import web.development.services.interfaces.publicApi.RoleService;
import web.development.util.ValidationUtilImpl;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService<String>, RoleInternalService<String> {

    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtilImpl validationUtil;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository, ModelMapper modelMapper, ValidationUtilImpl validationUtil) {
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public RoleDto save(RoleDto userRole) {

        if (!this.validationUtil.isValid(userRole)) {
            this.validationUtil
                    .violations(userRole)
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);

        } else {
            try {
                return modelMapper.map(roleRepository.saveAndFlush(modelMapper.map(userRole, Role.class)), RoleDto.class);
            } catch (Exception e) {
                System.out.println("Some thing went wrong!");
            }
        }

        return null;

    }

    @Override
    public RoleDto findById(String id) {
        return modelMapper.map(roleRepository.findById(id).orElse(null),RoleDto.class);
    }

    @Override
    public List<RoleDto> findAll() {
        return roleRepository.findAll().stream().map(e -> modelMapper.map(e, RoleDto.class)).collect(Collectors.toList());
    }

    @Override
    public void deleteById(String id) {
        roleRepository.deleteById(id);
    }
}
