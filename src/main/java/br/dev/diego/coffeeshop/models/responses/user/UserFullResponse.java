package br.dev.diego.coffeeshop.models.responses.user;

import br.dev.diego.coffeeshop.models.entities.User;
import br.dev.diego.coffeeshop.models.enums.Role;

import java.time.LocalDateTime;

public record UserFullResponse(
        Long id,
        String email,
        String username,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        Role role
) {

    public UserFullResponse(User entity) {
        this(entity.getId(), entity.getEmail(), entity.getUsername(), entity.getCreatedAt(),
                entity.getUpdatedAt(), entity.getRole());
    }
}
