package br.dev.diego.coffeeshop.models.entities;

import br.dev.diego.coffeeshop.models.enums.Category;
import br.dev.diego.coffeeshop.models.requests.product.ProductInsertRequest;
import br.dev.diego.coffeeshop.models.requests.product.ProductUpdateRequest;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "tb_product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String photo;
    private Integer size;
    private BigDecimal price;
    private Integer preparation;
    private Category categoryId;

    public Product(ProductInsertRequest request) {
        title = request.title();
        description = request.description();
        photo = request.photo();
        size = request.size();
        price = request.price();
        preparation = request.preparation();
        categoryId = request.categoryId();
    }

    public void update(ProductUpdateRequest request) {
        this.title = request.title();
        this.description = request.description();
        this.photo = request.photo();
        this.size = request.size();
        this.price = request.price();
        this.preparation = request.preparation();
        this.categoryId = request.categoryId();
    }

}
