package org.timespace.category;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    default Category findOne(Long id) {
        return (Category) findById(id).orElse(null);
    }
    default void delete(Long id) {
        deleteById(id);
    }
}
