package com.delicious_cake.delicious_cake_app.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.delicious_cake.delicious_cake_app.entities.CategoryEntity;
import com.delicious_cake.delicious_cake_app.repositories.CategoryRepository;

@Service
public class CategoryService {

    //Define a private final field for the repository
    private final CategoryRepository categoryRepository;

    //Constructor injection for the repository
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    //Create Method
    public CategoryEntity create(CategoryEntity categoryEntity) {

        if (categoryEntity.getName() == null || categoryEntity.getName().isEmpty()) {
            throw new IllegalArgumentException("Category name cannot be null or empty");
        }

        return categoryRepository.save(categoryEntity);
    }

    //Get Method
    public CategoryEntity getById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Category not found with id: " + id));
    }

    //Get All Method
    public List<CategoryEntity> getAll() {
        return categoryRepository.findAll();
    }

    //Update Method
    public CategoryEntity update(Long id, CategoryEntity categoryEntity) {
       
        CategoryEntity existingCategory = getById(id);

        if (categoryEntity.getName() == null || categoryEntity.getName().isEmpty()) {
            throw new IllegalArgumentException("Category name cannot be null or empty");
        }

        existingCategory.setName(categoryEntity.getName());
        existingCategory.setDescription(categoryEntity.getDescription());
        return categoryRepository.save(existingCategory);
    }

    //Delete Method
    public void delete(Long id) {
        try {
            CategoryEntity existingCategory = getById(id);
            categoryRepository.delete(existingCategory);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Category not found with id: " + id);
        }
    }
}
