package com.devsuperior.aula.services;

import com.devsuperior.aula.dto.CategoryDTO;
import com.devsuperior.aula.dto.ProductDTO;
import com.devsuperior.aula.entities.Category;
import com.devsuperior.aula.entities.Product;
import com.devsuperior.aula.repositories.CategoryRepository;
import com.devsuperior.aula.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private CategoryRepository categoryRepository;

    public ProductDTO insert(ProductDTO dto){
        Product productEntity = new Product();
        productEntity.setName(dto.getName());
        productEntity.setPrice(dto.getPrice());

        for (CategoryDTO catDTO : dto.getCategories()){
            //Category cat = new Category();
            //cat.setId(catDTO.getId());
            //cat.setName(catDTO.getName());

            //Para que retorne també o nome da categoria
            Category cat = categoryRepository.getReferenceById(catDTO.getId());
            productEntity.getCategories().add(cat);
        }

        productEntity = repository.save(productEntity);
        return new ProductDTO(productEntity);

    }
}
