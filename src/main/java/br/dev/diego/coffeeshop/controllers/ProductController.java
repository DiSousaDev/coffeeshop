package br.dev.diego.coffeeshop.controllers;

import br.dev.diego.coffeeshop.models.requests.product.ProductInsertRequest;
import br.dev.diego.coffeeshop.models.requests.product.ProductUpdateRequest;
import br.dev.diego.coffeeshop.models.responses.product.ProductFullResponse;
import br.dev.diego.coffeeshop.services.ProductService;
import br.dev.diego.coffeeshop.utils.GeneralUtilities;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
public class ProductController {

    private ProductService service;

    @GetMapping
    public ResponseEntity<Page<ProductFullResponse>> getAllProducts(Pageable pageable) {
        Page<ProductFullResponse> products = service.getProducts(pageable);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/findByCategoryId/{id}")
    public ResponseEntity<List<ProductFullResponse>> getByTitle(@PathVariable Long id) {
        List<ProductFullResponse> products = service.getProductsByCategory(id);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductFullResponse> getById(@PathVariable Long id) {
        ProductFullResponse product = service.getProductById(id);
        return ResponseEntity.ok(product);
    }

    @GetMapping("/findByTitle/{title}")
    public ResponseEntity<ProductFullResponse> getByTitle(@PathVariable String title) {
        ProductFullResponse product = service.getProductByTitle(title);
        return ResponseEntity.ok(product);
    }

    @PostMapping
    public ResponseEntity<ProductFullResponse> save(@RequestBody @Valid ProductInsertRequest request) {
        ProductFullResponse obj = service.saveProduct(request);
        URI uri = GeneralUtilities.toUri("/{id}", obj.id());
        log.info("Produto id: {} cadastrado com sucesso.", obj.id());
        return ResponseEntity.created(uri).body(obj);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductFullResponse> update(@RequestBody @Valid ProductUpdateRequest request, @PathVariable Long id) {
        ProductFullResponse obj = service.updateProduct(request, id);
        return ResponseEntity.ok(obj);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteProductById(id);
        return ResponseEntity.noContent().build();
    }

}
