package org.backendspring_boot.backendspring_boot.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.backendspring_boot.backendspring_boot.entity.User;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private String password;
    private String role;

    public UserDTO(User convertedUser)
    {
        this.id = convertedUser.getId();
        this.username = convertedUser.getUsername();
        this.email = convertedUser.getEmail();
        this.password = convertedUser.getPassword();
        this.role = convertedUser.getRoles().stream().findAny().get().getName();
    }
}
