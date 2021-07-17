package mondays.net.mroki.api.service.impl;

import lombok.AllArgsConstructor;
import mondays.net.mroki.api.entity.Product;
import mondays.net.mroki.api.entity.Size;
import mondays.net.mroki.api.repository.SizeRepository;
import mondays.net.mroki.api.service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SizeServiceImpl implements SizeService {

    @Autowired
    private final SizeRepository repo;

    public List<Size> findByProduct(Long productId){

       return repo.findByProduct(Product.builder().id(productId).build());

    }
}
