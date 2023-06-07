package java5.poly.assignment.service.ServiceI;

import java5.poly.assignment.model.Category;
import java5.poly.assignment.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface ProductServiceI {
    public Product save(Product productEntity);
    public Product update(Product productEntity);
    public void delete(Product productEntity);
    public List<Product> getList();
    public Product getOne(UUID id);
    public Page<Product> getProducts(int pageNumber, int pageSize, Sort sort);
    public Page<Product> getProductsbyPrice(Double pricemin, Double pricemax, int pageNumber, int pageSize);
    public Page<Product> getProductsbyName(String name, int pageNumber, int pageSize);
    Page<Product> findProductsByCategory(Category category, int pageNumber, int pageSize);

}
