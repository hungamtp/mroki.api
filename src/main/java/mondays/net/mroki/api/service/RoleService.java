package mondays.net.mroki.api.service;

import mondays.net.mroki.api.dto.customer.RoleDTO;
import org.springframework.stereotype.Service;

import java.util.List;


public interface RoleService {
    List<RoleDTO> getAllRole();
}
