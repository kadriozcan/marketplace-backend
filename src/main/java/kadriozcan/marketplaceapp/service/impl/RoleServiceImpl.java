package kadriozcan.marketplaceapp.service.impl;

import kadriozcan.marketplaceapp.exception.RoleNotFoundException;
import kadriozcan.marketplaceapp.model.Role;
import kadriozcan.marketplaceapp.repository.RoleRepository;
import kadriozcan.marketplaceapp.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public void checkAndCreateRoles(List<String> roles) {
        if (roles.isEmpty()) {
            return;
        }
        roles.forEach(role -> {
            roleRepository.findByName(role).orElseGet(() -> roleRepository.save(new Role(role)));
        });
    }

    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name).orElseThrow(() -> new RoleNotFoundException(name));
    }
}
