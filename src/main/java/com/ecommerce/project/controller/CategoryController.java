//package com.ecommerce.project.controller;
//
//import com.ecommerce.project.config.AppConstants;
//import com.ecommerce.project.payload.CategoryDTO;
//import com.ecommerce.project.payload.CategoryResponse;
//import com.ecommerce.project.service.CategoryService;
//import jakarta.validation.Valid;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api")
//public class CategoryController {
//
//    @Autowired
//    private CategoryService categoryService;
//
//    @GetMapping("/echo")
//    public ResponseEntity<String> echoMessage(@RequestParam(name = "message", required = false ) String message){
//       // public ResponseEntity<String> echoMessage(@RequestParam(name = "message", defaultValue = "Hello World!") String message){
//
//            return new ResponseEntity<>("Echoed message:" + message, HttpStatus.OK);
//    }
//
//    // GET all categories
//    @GetMapping("/public/categories")
//    public ResponseEntity<CategoryResponse> getAllCategory(
//            @RequestParam(name= "pageNumber" , defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
//            @RequestParam(name= "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize)
//             @RequestParam(name = "sortBY",  defaultValue = AppConstants.SORT_CATEGORIES_BY, required = false) String  sortBy,
//             @RequestParam(name = "sortOrder",  defaultValue = AppConstants.SORT_DIR, required = false) String  sortOrder) {
//        CategoryResponse categoryResponse = categoryService.getAllCategories(pageNumber, pageSize, sortBy, sortOrder);
//        return new ResponseEntity<>(categoryResponse , HttpStatus.OK);
//    }
//
//    // CREATE category
//    @PostMapping("/public/categories")
//    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
//       CategoryDTO savedCategoryDTO=  categoryService.createCategory(categoryDTO);
//        return new  ResponseEntity<>(savedCategoryDTO , HttpStatus.CREATED);
//    }
//
//    // DELETE category
//    @DeleteMapping("/admin/categories/{categoryId}")
//    public ResponseEntity<CategoryDTO> deleteCategory(
//            @PathVariable Long categoryId) {
//        CategoryDTO  deletedCategory = categoryService.deleteCategory(categoryId);
//        return new  ResponseEntity<>(deletedCategory, HttpStatus.OK);
//    }
//
//    // UPDATE category
//    @PutMapping("/public/categories/{categoryId}")
//    public ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody CategoryDTO categoryDTO,
//            @PathVariable Long categoryId) {
//        CategoryDTO  savedCategoryDTO = categoryService.updateCategory(categoryDTO, categoryId);
//        return new  ResponseEntity<>(savedCategoryDTO, HttpStatus.OK);
//    }
//}





package com.ecommerce.project.controller;

import com.ecommerce.project.config.AppConstants;
import com.ecommerce.project.payload.CategoryDTO;
import com.ecommerce.project.payload.CategoryResponse;
import com.ecommerce.project.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // Echo API
    @GetMapping("/echo")
    public ResponseEntity<String> echoMessage(
            @RequestParam(name = "message", required = false) String message) {

        return new ResponseEntity<>("Echoed message: " + message, HttpStatus.OK);
    }

    //  GET all categories (Pagination + Sorting)
    @GetMapping("/public/categories")
    public ResponseEntity<CategoryResponse> getAllCategory(

            @RequestParam(
                    name = "pageNumber",
                    defaultValue = AppConstants.PAGE_NUMBER,
                    required = false
            ) Integer pageNumber,

            @RequestParam(
                    name = "pageSize",
                    defaultValue = AppConstants.PAGE_SIZE,
                    required = false
            ) Integer pageSize,

            @RequestParam(
                    name = "sortBy",
                    defaultValue = AppConstants.SORT_CATEGORIES_BY,
                    required = false
            ) String sortBy,

            @RequestParam(
                    name = "sortOrder",
                    defaultValue = AppConstants.SORT_DIR,
                    required = false
            ) String sortOrder
    ) {

        CategoryResponse categoryResponse =
                categoryService.getAllCategories(pageNumber, pageSize, sortBy, sortOrder);

        return new ResponseEntity<>(categoryResponse, HttpStatus.OK);
    }

    // CREATE category
    @PostMapping("/public/categories")
    public ResponseEntity<CategoryDTO> createCategory(
            @Valid @RequestBody CategoryDTO categoryDTO) {

        CategoryDTO savedCategoryDTO =
                categoryService.createCategory(categoryDTO);

        return new ResponseEntity<>(savedCategoryDTO, HttpStatus.CREATED);
    }

    //  DELETE category
    @DeleteMapping("/admin/categories/{categoryId}")
    public ResponseEntity<CategoryDTO> deleteCategory(
            @PathVariable Long categoryId) {

        CategoryDTO deletedCategory =
                categoryService.deleteCategory(categoryId);

        return new ResponseEntity<>(deletedCategory, HttpStatus.OK);
    }

    //  UPDATE category
    @PutMapping("/public/categories/{categoryId}")
    public ResponseEntity<CategoryDTO> updateCategory(
            @Valid @RequestBody CategoryDTO categoryDTO,
            @PathVariable Long categoryId) {

        CategoryDTO updatedCategory =
                categoryService.updateCategory(categoryDTO, categoryId);

        return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
    }
}