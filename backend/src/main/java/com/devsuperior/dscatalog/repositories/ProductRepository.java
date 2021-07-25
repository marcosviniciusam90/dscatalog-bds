package com.devsuperior.dscatalog.repositories;

import com.devsuperior.dscatalog.entities.Category;
import com.devsuperior.dscatalog.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(
            "SELECT DISTINCT obj FROM Product obj " +
            "INNER JOIN obj.categories categories " +
            "WHERE (:category IS NULL OR :category IN categories)"
    )
    Page<Product> find(Category category, Pageable pageable);

}
