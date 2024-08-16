package kadriozcan.marketplaceapp.mapper;

import kadriozcan.marketplaceapp.dto.user.UserDto;
import kadriozcan.marketplaceapp.model.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserMapper {
    public User toModel(UserDto userDto) {
        User user = new User();
        if (StringUtils.isNotBlank(userDto.getId())) {
            user.setId(UUID.fromString(userDto.getId()));
        }
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setSurname(userDto.getSurname());
        user.setUsername(userDto.getUsername());
        user.setEnabled(Boolean.TRUE);
        user.setCreatedAt(userDto.getCreatedAt());
        user.setUpdatedAt(userDto.getUpdatedAt());
        return user;
    }

    public UserDto toDto(User user) {
        return UserDto.builder()
                .name(user.getName())
                .email(user.getEmail())
                .surname(user.getSurname())
                .username(user.getUsername())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .id(String.valueOf(user.getId())).build();
    }
}
