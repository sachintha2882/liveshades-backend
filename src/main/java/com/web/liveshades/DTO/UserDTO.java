package com.web.liveshades.DTO;

import com.web.liveshades.Model.Role;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private String username;
    private String email;
    private String password;
    private Role role;
}