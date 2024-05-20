package com.workshhop.elza.dto;
// authentification request object
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AuthentificationRequest {
    private String email;
    private String password;
}
