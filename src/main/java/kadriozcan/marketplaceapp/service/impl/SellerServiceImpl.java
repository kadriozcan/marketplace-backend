package kadriozcan.marketplaceapp.service.impl;

import kadriozcan.marketplaceapp.dto.seller.CreateSellerRequest;
import kadriozcan.marketplaceapp.dto.seller.SellerDto;
import kadriozcan.marketplaceapp.mapper.SellerMapper;
import kadriozcan.marketplaceapp.model.Seller;
import kadriozcan.marketplaceapp.repository.SellerRepository;
import kadriozcan.marketplaceapp.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class SellerServiceImpl implements SellerService {
    private final SellerRepository sellerRepository;
    private final SellerMapper sellerMapper;

    @Override
    public List<SellerDto> listSellers() {
        List<Seller> all = sellerRepository.findAll();

        return all.stream().map(sellerMapper::toDto).toList();
    }

    @Override
    public SellerDto create(CreateSellerRequest createRequest) {
        Seller seller = new Seller();
        seller.setName(createRequest.getName());
        sellerRepository.save(seller);
        return sellerMapper.toDto(seller);
    }

    @Override
    public void generateSampleSellers() {
        List<Seller> sellers = new ArrayList<>();

        Seller seller = new Seller();
        seller.setName("MediaMarkt");
        sellers.add(seller);

        Seller seller2 = new Seller();
        seller2.setName("Cloth Store");
        sellers.add(seller2);

        sellerRepository.saveAll(sellers);
    }
}
