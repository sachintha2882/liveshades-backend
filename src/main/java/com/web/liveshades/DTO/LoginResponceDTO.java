package com.web.liveshades.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class LoginResponceDTO {
    private String massage;
    private boolean success;
    private String role;

}
