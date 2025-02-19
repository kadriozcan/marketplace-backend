package kadriozcan.marketplaceapp.repository;

import kadriozcan.marketplaceapp.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SellerRepository extends JpaRepository<Seller, UUID> {
    Optional<Seller> findByNameEqualsIgnoreCase(String name);
}
