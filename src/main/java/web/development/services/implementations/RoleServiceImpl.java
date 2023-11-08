package web.development.services.implementations;

import jakarta.validation.ConstraintViolation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.development.models.entities.Model;
import web.development.models.enums.RoleType;
import web.development.services.dto.input.ModelDto;
import web.development.services.dto.input.RoleDto;
import web.development.models.entities.Role;
import web.development.repositories.RoleRepository;
import web.development.services.exceptions.NotFoundException;
import web.development.services.exceptions.SaveException;
import web.development.services.exceptions.ValidationException;
import web.development.services.interfaces.internalApi.RoleInternalService;
import web.development.services.interfaces.publicApi.RoleService;
import web.development.util.ValidationUtilImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
            String exceptionMessage = "The data is not valid:\n";
            List<String> validationErrors = new ArrayList<>(this.validationUtil
                    .violations(userRole)
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.toList()));

            exceptionMessage += String.join("\n", validationErrors);
            throw new ValidationException(exceptionMessage);

        } else {
            try {
                return modelMapper.map(roleRepository.saveAndFlush(modelMapper.map(userRole, Role.class)), RoleDto.class);
            } catch (Exception e) {
                throw new SaveException("Failed to save the role.");
            }
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
