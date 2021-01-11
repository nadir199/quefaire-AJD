package com.example.quefaire;
import org.springframework.data.repository.CrudRepository;

import com.example.quefaire.models.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {

}