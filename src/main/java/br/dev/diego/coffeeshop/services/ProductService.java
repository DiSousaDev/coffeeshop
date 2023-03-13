package br.dev.diego.coffeeshop.services;

import br.dev.diego.coffeeshop.models.requests.product.ProductInsertRequest;
import br.dev.diego.coffeeshop.models.requests.product.ProductUpdateRequest;
import br.dev.diego.coffeeshop.models.responses.product.ProductFullResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    Page<ProductFullResponse> getProducts(Pageable pageable);

    ProductFullResponse saveProduct(ProductInsertRequest request);

    ProductFullResponse getProductById(Long id);

    ProductFullResponse getProductByTitle(String title);

    List<ProductFullResponse> getProductsByCategory(Long categoryId);

    ProductFullResponse updateProduct(ProductUpdateRequest request, Long id);

    void deleteProductById(Long id);

}
