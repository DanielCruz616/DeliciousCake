package com.delicious_cake.delicious_cake_app.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.delicious_cake.delicious_cake_app.dtos.CategoryDTO;
import com.delicious_cake.delicious_cake_app.entities.CategoryEntity;
import com.delicious_cake.delicious_cake_app.mappers.CategoryMapper;
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
    public CategoryDTO create(CategoryDTO dto) {

        if (dto.getName() == null || dto.getName().isEmpty()) {
            throw new IllegalArgumentException("Category name cannot be null or empty");
        }

        CategoryEntity category = CategoryMapper.toEntity(dto);

        CategoryEntity savedCategory = categoryRepository.save(category);

        return CategoryMapper.toDTO(savedCategory);
    }

    //Get Method
    public CategoryDTO getById(Long id) {

        CategoryEntity category = categoryRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Category not found with id: " + id));

        return CategoryMapper.toDTO(category);
    }

    //Get All Method
    public List<CategoryDTO> getAll() {

        return categoryRepository.findAll()
                .stream()
                .map(CategoryMapper::toDTO)
                .collect(Collectors.toList());
    }

    //Update Method
    public CategoryDTO update(Long id, CategoryDTO dto) {

        CategoryEntity existingCategory = categoryRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Category not found with id: " + id));

        if (dto.getName() == null || dto.getName().isEmpty()) {
            throw new IllegalArgumentException("Category name cannot be null or empty");
        }

        existingCategory.setName(dto.getName());
        existingCategory.setDescription(dto.getDescription());

        CategoryEntity updatedCategory = categoryRepository.save(existingCategory);

        return CategoryMapper.toDTO(updatedCategory);
    }

    //Delete Method
    public void delete(Long id) {

        CategoryEntity category = categoryRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Category not found with id: " + id));

        categoryRepository.delete(category);
    }
}