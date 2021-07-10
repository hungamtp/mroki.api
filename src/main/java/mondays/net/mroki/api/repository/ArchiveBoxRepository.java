package mondays.net.mroki.api.repository;

import mondays.net.mroki.api.entity.ArchiveBox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ArchiveBoxRepository extends JpaRepository<ArchiveBox, Long> {

    @Query(value = "SELECT id FROM archive_box WHERE is_available = true LIMIT 1", nativeQuery = true)
    Long getBoxIdIsAvailable();

    @Query(value = "UPDATE archive_box SET is_available = false WHERE id = ?1 " , nativeQuery = true)
    void updateIsNotAvailable(Long boxId);

    @Query(value = "UPDATE archive_box SET is_available = true WHERE id = ?1 " , nativeQuery = true)
    void updateIsAvailable(Long boxId);


}
