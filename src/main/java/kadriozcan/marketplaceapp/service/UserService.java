package kadriozcan.marketplaceapp.service;

import kadriozcan.marketplaceapp.dto.user.UserDto;
import kadriozcan.marketplaceapp.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserDto> listUsers();

    void generateSampleUsers();

    UserDto getByUserId(String userId);

    Optional<User> getByUsername(String username);

    UserDto create(UserDto userDto);

    void checkAndCreateAdminUser();
}
