package br.dev.diego.coffeeshop.services.impl;

import br.dev.diego.coffeeshop.models.entities.Product;
import br.dev.diego.coffeeshop.models.enums.Category;
import br.dev.diego.coffeeshop.models.requests.product.ProductInsertRequest;
import br.dev.diego.coffeeshop.models.requests.product.ProductUpdateRequest;
import br.dev.diego.coffeeshop.models.responses.product.ProductFullResponse;
import br.dev.diego.coffeeshop.repositories.ProductRepository;
import br.dev.diego.coffeeshop.services.ProductService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class ProducServiceImpl implements ProductService {

    private ProductRepository repository;

    @Override
    @Transactional(readOnly = true)
    public Page<ProductFullResponse> getProducts(Pageable pageable) {
        Page<Product> products = repository.findAll(pageable);
        return products.map(ProductFullResponse::new);
    }

    @Override
    @Transactional
    public ProductFullResponse saveProduct(ProductInsertRequest request) {
        Product product = repository.save(new Product(request));
        return new ProductFullResponse(product);
    }

    @Override
    @Transactional(readOnly = true)
    public ProductFullResponse getProductById(Long id) {
        Product product = getProduct(id);
        return new ProductFullResponse(product);
    }

    @Override
    @Transactional(readOnly = true)
    public ProductFullResponse getProductByTitle(String title) {
        Product product = repository.findByTitle(title).orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));
        return new ProductFullResponse(product);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductFullResponse> getProductsByCategory(Long categoryId) {
        List<Product> products = repository.findAllByCategoryId(Category.toEnum(categoryId));
        return products.stream().map(ProductFullResponse::new).toList();
    }

    @Override
    @Transactional
    public ProductFullResponse updateProduct(ProductUpdateRequest request, Long id) {
        Product product = repository.getReferenceById(id);
        product.update(request);
        return new ProductFullResponse(product);
    }

    @Override
    @Transactional
    public void deleteProductById(Long id) {
        getProduct(id);
        repository.deleteById(id);
    }

    private Product getProduct(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));
    }
}
