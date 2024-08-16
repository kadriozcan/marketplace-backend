package kadriozcan.marketplaceapp.repository;

import kadriozcan.marketplaceapp.model.Blacklist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BlacklistRepository extends JpaRepository<Blacklist, UUID> {
    List<Blacklist> findAllByUserId(UUID userId);
}
