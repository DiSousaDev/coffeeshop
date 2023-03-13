package br.dev.diego.coffeeshop.models.requests.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record UserInsertRequest(
        @NotBlank
        @Email
        String email,
        @NotBlank
        @Length(min = 5, max = 15)
        String username,
        @NotBlank
        String password
) {
}
