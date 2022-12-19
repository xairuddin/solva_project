package kz.solva.repository;

import kz.solva.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query(value = "SELECT u FROM Category u where u.category_name = ?1")
    Optional<Category> findByCategory_name(String name);
}
