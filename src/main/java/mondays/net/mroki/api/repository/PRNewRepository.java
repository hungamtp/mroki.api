package mondays.net.mroki.api.repository;

import mondays.net.mroki.api.entity.PRNew;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface PRNewRepository extends JpaRepository<PRNew, Long> {

    //Page<PRNew> findAllPRNewAfterPublicDate(Pageable pageable , LocalDate publicDate);
}
