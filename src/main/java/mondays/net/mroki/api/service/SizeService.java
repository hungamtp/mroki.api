package mondays.net.mroki.api.service;

import mondays.net.mroki.api.entity.Size;

import java.util.List;

public interface SizeService {

    List<Size> findByProduct(Long productId);
}
