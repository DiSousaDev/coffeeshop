package br.dev.diego.coffeeshop.repositories;

import br.dev.diego.coffeeshop.models.entities.Product;
import br.dev.diego.coffeeshop.models.enums.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByTitle(String title);
    List<Product> findAllByCategoryId(Category category);

}
