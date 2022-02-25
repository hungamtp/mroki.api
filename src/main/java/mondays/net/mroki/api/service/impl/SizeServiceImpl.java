package mondays.net.mroki.api.service.impl;

import mondays.net.mroki.api.entity.Product;
import mondays.net.mroki.api.entity.Size;
import mondays.net.mroki.api.repository.SizeRepository;
import mondays.net.mroki.api.service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SizeServiceImpl implements SizeService {

    @Autowired
    final SizeRepository repo;

    public SizeServiceImpl(SizeRepository repo) {
        this.repo = repo;
    }

    public List<Size> findByProduct(Long productId){

       return repo.findByProduct(Product.builder().id(productId).build());

    }
}
