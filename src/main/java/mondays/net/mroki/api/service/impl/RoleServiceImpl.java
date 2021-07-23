package mondays.net.mroki.api.service.impl;

import lombok.AllArgsConstructor;
import mondays.net.mroki.api.converter.RoleConverter;
import mondays.net.mroki.api.dto.customerDTO.RoleDTO;
import mondays.net.mroki.api.repository.RoleRepository;
import mondays.net.mroki.api.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {
    @Autowired
    private final RoleRepository roleRepository;

    @Autowired
    private final RoleConverter converter;

    @Override
    public List<RoleDTO> getAllRole() {

        return converter.entityToDto(roleRepository.findAll());

    }

}
