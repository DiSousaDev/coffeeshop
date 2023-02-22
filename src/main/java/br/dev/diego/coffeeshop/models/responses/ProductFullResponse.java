package br.dev.diego.coffeeshop.models.responses;

import br.dev.diego.coffeeshop.models.entities.Product;
import br.dev.diego.coffeeshop.models.enums.Category;

import java.math.BigDecimal;

public record ProductFullResponse(
        Long id,
        String title,
        String description,
        String photo,
        Integer size,
        BigDecimal price,
        Integer preparation,
        Category categoryId
) {

    public ProductFullResponse(Product entity) {
        this(
                entity.getId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getPhoto(),
                entity.getSize(),
                entity.getPrice(),
                entity.getPreparation(),
                entity.getCategoryId()
        );
    }
}
