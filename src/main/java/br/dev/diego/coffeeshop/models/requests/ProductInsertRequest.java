package br.dev.diego.coffeeshop.models.requests;

import br.dev.diego.coffeeshop.models.enums.Category;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.URL;

import java.math.BigDecimal;

public record ProductInsertRequest(
        @NotBlank String title,
        @NotBlank String description,
        @NotBlank @URL String photo,
        @NotNull Integer size,
        @NotNull @DecimalMin(value = "0.0", inclusive = false) @Digits(integer = 3, fraction = 2) BigDecimal price,
        @NotNull Integer preparation,
        @NotNull Category categoryId
        ) {
}
