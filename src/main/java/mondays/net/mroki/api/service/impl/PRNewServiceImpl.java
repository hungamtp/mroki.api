package mondays.net.mroki.api.service.impl;

import lombok.AllArgsConstructor;
import mondays.net.mroki.api.entity.Customer;
import mondays.net.mroki.api.entity.PRNew;
import mondays.net.mroki.api.entity.Product;
import mondays.net.mroki.api.repository.PRNewRepository;
import mondays.net.mroki.api.service.PRNewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class PRNewServiceImpl implements PRNewService {

    @Autowired
    private final PRNewRepository prNewRepository;
    public PRNewServiceImpl(PRNewRepository prNewRepository){
        this.prNewRepository = prNewRepository;
    }

    private final int PAGE_SIZE = 6;

//    public Page<PRNew> getAllPRNew( int page) {
//
//        Pageable pageable = PageRequest.of(page , PAGE_SIZE);
//        LocalDate publicDate = LocalDate.now().minusDays(2);
//
//        return Optional.ofNullable(prNewRepository.findAllPRNewAfterPublicDate(pageable , publicDate)).orElse(null);
//
//    }

    @Override
    public void addPrNew(Long idolId, Long productId) {

        PRNew prNew = PRNew.builder().customer(new Customer(idolId))
                .product(new Product(productId))
                .publicDate(LocalDate.now())
                .build();

        prNewRepository.save(prNew);

    }

}
