//package com.ecommerce.project.service;
//
//import com.ecommerce.project.model.Product;
//import com.ecommerce.project.payload.ProductDTO;
//import com.ecommerce.project.payload.ProductResponse;
//
//public interface ProductService {
//
//    ProductDTO addProduct(Long categoryId, ProductDTO product);
//    ProductResponse getAllproducts(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);
//
//    ProductResponse searchBycategory(Long categoryId, Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);
//
//    ProductResponse searchProductByKeyword();
//
//    ProductDTO updateProduct(Product product);
//
//    ProductDTO deleteProduct(Long productId);
//
//    ProductDTO updatedProductImage();
//}


package com.ecommerce.project.service;

import com.ecommerce.project.payload.ProductDTO;
import com.ecommerce.project.payload.ProductResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ProductService {

    // ADD PRODUCT
    ProductDTO addProduct(Long categoryId, ProductDTO productDTO);

    // GET ALL PRODUCTS (PAGINATION + SORTING)
    ProductResponse getAllProducts(
            Integer pageNumber,
            Integer pageSize,
            String sortBy,
            String sortOrder
    );

    // GET PRODUCTS BY CATEGORY
    ProductResponse getProductsByCategory(
            Long categoryId,
            Integer pageNumber,
            Integer pageSize,
            String sortBy,
            String sortOrder
    );

    // SEARCH PRODUCT BY KEYWORD
    ProductResponse searchProductByKeyword(
            String keyword,
            Integer pageNumber,
            Integer pageSize,
            String sortBy,
            String sortOrder
    );

    // UPDATE PRODUCT
    ProductDTO updateProduct(Long productId, ProductDTO productDTO);

    // DELETE PRODUCT
    ProductDTO deleteProduct(Long productId);

    ProductDTO updatedProductImage();

    // UPDATE PRODUCT IMAGE
    ProductDTO updateProductImage(Long productId, MultipartFile image) throws IOException;
}