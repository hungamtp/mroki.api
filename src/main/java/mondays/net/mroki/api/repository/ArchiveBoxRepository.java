package mondays.net.mroki.api.repository;

import mondays.net.mroki.api.entity.ArchiveBox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ArchiveBoxRepository extends JpaRepository<ArchiveBox , Long> {

    @Query(value = "SELECT id FROM archive_box WHERE is_available LIMIT 1" , nativeQuery = true)
    Long getBoxIdIsAvailable ();


}
