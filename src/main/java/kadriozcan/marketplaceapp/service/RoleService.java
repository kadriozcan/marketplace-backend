package kadriozcan.marketplaceapp.service;

import kadriozcan.marketplaceapp.model.Role;

import java.util.List;

public interface RoleService {
    void checkAndCreateRoles(List<String> roles);

    Role findByName(String name);
}
