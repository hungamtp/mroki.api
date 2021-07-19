package mondays.net.mroki.api.service;

import org.springframework.stereotype.Service;


public interface PRNewService {
    //  Page<PRNew> getAllPRNew( int page);
    void addPrNew(Long idolId, Long productId);
}
