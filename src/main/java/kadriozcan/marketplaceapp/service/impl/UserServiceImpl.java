package kadriozcan.marketplaceapp.service.impl;

import kadriozcan.marketplaceapp.common.Constants;
import kadriozcan.marketplaceapp.dto.user.UserDto;
import kadriozcan.marketplaceapp.exception.UserNotFoundException;
import kadriozcan.marketplaceapp.mapper.UserMapper;
import kadriozcan.marketplaceapp.model.Role;
import kadriozcan.marketplaceapp.model.User;
import kadriozcan.marketplaceapp.repository.UserRepository;
import kadriozcan.marketplaceapp.service.RoleService;
import kadriozcan.marketplaceapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final RoleService roleService;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<UserDto> listUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(userMapper::toDto).toList();
    }

    @Override
    public void generateSampleUsers() {
        List<User> sampleUsers = new ArrayList<>();

        final String[] NAMES = {"John", "Jane", "Alex", "Emily", "Chris", "Katie"};
        final String[] SURNAMES = {"Smith", "Johnson", "Williams", "Jones", "Brown", "Davis"};
        final String[] EMAIL_DOMAINS = {"example.com", "test.com", "demo.com", "mail.com", "yaho.com", "outlook.com"};

        for (int i = 0; i < NAMES.length; i++) {
            String name = NAMES[i];
            String surname = SURNAMES[i];
            String email = name.toLowerCase() + "." + surname.toLowerCase() +
                    "@" + EMAIL_DOMAINS[i];
            UserDto userDto = UserDto.builder().name(name).surname(surname)
                    .email(email).username(generateUsername(name, surname)).build();
            User userModel = userMapper.toModel(userDto);
            userModel.setPassword(passwordEncoder.encode("pass"));
            userModel.addRole(roleService.findByName(Constants.Roles.USER));
            sampleUsers.add(userModel);
        }
        userRepository.saveAll(sampleUsers);
    }

    @Override
    public UserDto getByUserId(String userId) {
        User user = userRepository.findById(UUID.fromString(userId)).orElseThrow(() -> new UserNotFoundException(userId));

        return userMapper.toDto(user);
    }

    @Override
    public Optional<User> getByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public UserDto create(UserDto userDto) {
        Objects.requireNonNull(userDto, "userDto cannot be null");
        Objects.requireNonNull(userDto.getEmail(), "userDto.getEmail() cannot be null");
        Objects.requireNonNull(userDto.getSurname(), "userDto.getSurname() cannot be null");
        Objects.requireNonNull(userDto.getName(), "userDto.getName() cannot be null");

        if (StringUtils.isBlank(userDto.getUsername())) {
            userDto.setUsername(generateUsername(userDto.getName(), userDto.getSurname()));
        }

        User user = userMapper.toModel(userDto);
        user = userRepository.save(user);
        return userMapper.toDto(user);
    }

    @Override
    public void checkAndCreateAdminUser() {
        userRepository.findByUsername(Constants.ADMIN_USERNAME).orElseGet(() -> {
            Role adminRole = roleService.findByName(Constants.Roles.ADMIN);
            User user = new User();
            user.setEnabled(Boolean.TRUE);
            user.setEmail("admin@mail.com");
            user.setName("Admin");
            user.setSurname("Admin");
            user.addRole(adminRole);
            user.setUsername(Constants.ADMIN_USERNAME);
            user.setPassword(passwordEncoder.encode("pass"));
            userRepository.save(user);
            return user;
        });
    }

    private String generateUsername(String name, String surname) {
        name = name.toLowerCase();
        surname = surname.toLowerCase();
        return String.format("%s.%s", name, surname);
    }
}
