package mondays.net.mroki.api.repository;

import mondays.net.mroki.api.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role , String> {

    @Query(value = "SELECT id  , role_name FROM role" , nativeQuery = true)
    List<Object[]> getAllRole();
}
