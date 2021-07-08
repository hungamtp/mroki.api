package mondays.net.mroki.api.service.impl;

import lombok.AllArgsConstructor;
import mondays.net.mroki.api.dto.RoleDTO;
import mondays.net.mroki.api.repository.RoleRepository;
import mondays.net.mroki.api.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {
    @Autowired
    private final RoleRepository roleRepository;

    @Override
    public List<RoleDTO> getAllRole() {

        return convertDataToDTO(roleRepository.getAllRole());

    }

    List<RoleDTO> convertDataToDTO(List<Object[]> roles) {

        List<RoleDTO> result = new ArrayList<>();

        roles.stream().forEach((role) -> {
            String id = (String) role[0];
            String roleName = (String) role[1];
            result.add(new RoleDTO(id, roleName));
        });

        return result;
    }
}
