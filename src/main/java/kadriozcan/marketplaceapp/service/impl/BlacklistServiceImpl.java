package kadriozcan.marketplaceapp.service.impl;

import kadriozcan.marketplaceapp.dto.seller.SellerDto;
import kadriozcan.marketplaceapp.exception.SellerNotFoundException;
import kadriozcan.marketplaceapp.exception.UserNotFoundException;
import kadriozcan.marketplaceapp.mapper.SellerMapper;
import kadriozcan.marketplaceapp.model.Blacklist;
import kadriozcan.marketplaceapp.model.Seller;
import kadriozcan.marketplaceapp.model.User;
import kadriozcan.marketplaceapp.repository.BlacklistRepository;
import kadriozcan.marketplaceapp.repository.SellerRepository;
import kadriozcan.marketplaceapp.repository.UserRepository;
import kadriozcan.marketplaceapp.service.BlacklistService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class BlacklistServiceImpl implements BlacklistService {
    private final BlacklistRepository blacklistRepository;
    private final UserRepository userRepository;
    private final SellerRepository sellerRepository;
    private final SellerMapper sellerMapper;

    @Override
    public void addSellerToBlacklist(String userId, String sellerName) throws Exception {
        Seller seller = sellerRepository.findByNameEqualsIgnoreCase(sellerName)
                .orElseThrow(() -> new SellerNotFoundException(sellerName));

        User user = userRepository.findById(UUID.fromString(userId))
                .orElseThrow(() -> new UserNotFoundException(userId));

        Blacklist blacklist = new Blacklist();
        blacklist.setUser(user);
        blacklist.setSeller(seller);

        List<Blacklist> existingBlacklists = user.getBlacklists();
        for (Blacklist existingBlacklist : existingBlacklists) {
            if (existingBlacklist.getId().equals(blacklist.getId())) {
                throw new Exception("Already in blacklist!");
            }
        }

        blacklistRepository.save(blacklist);
    }

    @Override
    public List<SellerDto> getBlacklistSellers(String userId) {
        List<Blacklist> blacklistsByUserId = blacklistRepository.findAllByUserId(UUID.fromString(userId));
        List<SellerDto> sellerDtos = new ArrayList<>();

        for (Blacklist blacklist : blacklistsByUserId) {
            UUID sellerId = blacklist.getSeller().getId();
            Seller seller = sellerRepository.findById(sellerId)
                    .orElseThrow(() -> new SellerNotFoundException(sellerId.toString()));
            sellerDtos.add(sellerMapper.toDto(seller));
        }

        return sellerDtos;
    }

    @Override
    public void removeSellerFromBlacklist(String userId, String sellerId) {
        User user = userRepository.findById(UUID.fromString(userId))
                .orElseThrow(() -> new UserNotFoundException(userId));
        user.getBlacklists().removeIf(blacklist -> blacklist.getSeller().getId().equals(UUID.fromString(sellerId)));
        userRepository.save(user);
    }
}
