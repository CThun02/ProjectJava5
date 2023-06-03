package java5.poly.assignment.repository.RepoI;

import java5.poly.assignment.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.UUID;

@Repository
public interface ProductRepositoryDAO extends JpaRepository<Product, UUID> {
    Page<Product> findAll(Pageable pageable);
    @Query("select o from Product o where o.gia between ?1 and ?2")
    Page<Product> findAllByPrice(BigDecimal pricemin, BigDecimal pricemax, Pageable pageable);
}
