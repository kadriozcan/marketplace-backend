package kadriozcan.marketplaceapp.dto.user;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@AllArgsConstructor
public class UserDto {

    @EqualsAndHashCode.Include
    private String id;

    private String name;

    private String surname;

    private String email;

    private String username;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
