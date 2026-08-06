package com.varsha.smartlibrary.controller;

import com.varsha.smartlibrary.dto.CategoryRequestDTO;
import com.varsha.smartlibrary.dto.CategoryResponseDTO;
import com.varsha.smartlibrary.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@Tag(
        name = "Categories",
        description = "Manage book categories"
)
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Operation(summary = "Create a new category")
    @PostMapping
    public CategoryResponseDTO createCategory(
            @Valid @RequestBody CategoryRequestDTO request) {

        return categoryService.createCategory(request);
    }

    @Operation(summary = "Get all categories")
    @GetMapping
    public List<CategoryResponseDTO> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @Operation(summary = "Get category by ID")
    @GetMapping("/{id}")
    public CategoryResponseDTO getCategoryById(@PathVariable Long id) {
        return categoryService.getCategoryById(id);
    }

    @Operation(summary = "Update a category")
    @PutMapping("/{id}")
    public CategoryResponseDTO updateCategory(
            @PathVariable Long id,
            @Valid @RequestBody CategoryRequestDTO request) {

        return categoryService.updateCategory(id, request);
    }

    @Operation(summary = "Delete a category")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {

        categoryService.deleteCategory(id);

        return ResponseEntity.ok("Category deleted successfully.");
    }
}