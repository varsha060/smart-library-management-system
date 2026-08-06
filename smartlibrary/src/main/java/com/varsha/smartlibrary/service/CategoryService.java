package com.varsha.smartlibrary.service;
import java.util.List;
import com.varsha.smartlibrary.dto.CategoryRequestDTO;
import com.varsha.smartlibrary.dto.CategoryResponseDTO;
import com.varsha.smartlibrary.entity.Category;
import com.varsha.smartlibrary.exception.DuplicateResourceException;
import com.varsha.smartlibrary.exception.ResourceNotFoundException;
import com.varsha.smartlibrary.repository.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryResponseDTO createCategory(CategoryRequestDTO request) {

        if (categoryRepository.findByName(request.getName()).isPresent()) {
            throw new DuplicateResourceException("Category already exists.");
        }

        Category category = new Category();
        category.setName(request.getName());

        Category savedCategory = categoryRepository.save(category);

        return new CategoryResponseDTO(
                savedCategory.getId(),
                savedCategory.getName()
        );
    }
    public List<CategoryResponseDTO> getAllCategories() {

        List<Category> categories = categoryRepository.findAll();

        return categories.stream()
                .map(category -> new CategoryResponseDTO(
                        category.getId(),
                        category.getName()
                ))
                .toList();
    }
    public CategoryResponseDTO getCategoryById(Long id) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Category not found."));

        return new CategoryResponseDTO(
                category.getId(),
                category.getName()
        );
    }
    public CategoryResponseDTO updateCategory(Long id, CategoryRequestDTO request) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Category not found."));

        if (categoryRepository.findByName(request.getName()).isPresent()
                && !category.getName().equalsIgnoreCase(request.getName())) {
            throw new DuplicateResourceException("Category already exists.");
        }

        category.setName(request.getName());

        Category updatedCategory = categoryRepository.save(category);

        return new CategoryResponseDTO(
                updatedCategory.getId(),
                updatedCategory.getName()
        );
    }
    public void deleteCategory(Long id) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Category not found."));

        categoryRepository.delete(category);
    }

}