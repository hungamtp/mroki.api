package mondays.net.mroki.api.converter;

import mondays.net.mroki.api.dto.RoleDTO;
import mondays.net.mroki.api.entity.Role;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RoleConverter {

    public RoleDTO entityToDto(Role role){

        return RoleDTO.builder()
                .id(role.getId())
                .roleName(role.getRoleName())
                .build();

    }

    public List<RoleDTO> entityToDto(List<Role> roles){

        return roles.stream()
                .map(role -> entityToDto(role))
                .collect(Collectors.toList());

    }
}
