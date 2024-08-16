package kadriozcan.marketplaceapp.controller;

import jakarta.validation.Valid;
import kadriozcan.marketplaceapp.dto.BaseResponse;
import kadriozcan.marketplaceapp.dto.user.CreateUserRequest;
import kadriozcan.marketplaceapp.dto.user.UserDto;
import kadriozcan.marketplaceapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @GetMapping
    public BaseResponse<List<UserDto>> getAllUsers() {
        return new BaseResponse<>(userService.listUsers());
    }

    @GetMapping("/{userId}")
    public BaseResponse<UserDto> getUserById(@PathVariable String userId) {
        return new BaseResponse<>(userService.getByUserId(userId));
    }

    @PostMapping
    public BaseResponse<UserDto> createUser(@RequestBody @Valid CreateUserRequest createRequest) {
        final UserDto userDto = UserDto.builder().name(createRequest.getName())
                .surname(createRequest.getSurname()).email(createRequest.getEmail())
                .username(createRequest.getUsername()).build();

        return new BaseResponse<>(userService.create(userDto));
    }
}
