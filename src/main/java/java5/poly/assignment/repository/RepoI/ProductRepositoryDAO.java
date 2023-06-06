package java5.poly.assignment.repository.RepoI;

import java5.poly.assignment.model.Category;
import java5.poly.assignment.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepositoryDAO extends JpaRepository<Product, UUID> {
    Page<Product> findAll(Pageable pageable);
    Page<Product> findByGiaBetween(Double pricemin, Double pricemax, Pageable pageable);
    @Query("select o from Product o where o.ten like %?1%")
    Page<Product> findAllByName(String name, Pageable pageable);
    Page<Product> findProductsByCategory(Category category, Pageable pageable);
}
