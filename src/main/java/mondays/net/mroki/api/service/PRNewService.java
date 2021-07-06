package mondays.net.mroki.api.service;

import mondays.net.mroki.api.entity.PRNew;
import org.springframework.data.domain.Page;

public interface PRNewService {
  //  Page<PRNew> getAllPRNew( int page);
    void addPrNew(Long idolId , Long productId);
}
