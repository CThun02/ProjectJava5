package java5.poly.assignment.service.ServiceImpl;

import java5.poly.assignment.model.Product;
import java5.poly.assignment.repository.RepoI.ProductRepositoryDAO;
import java5.poly.assignment.service.ServiceI.ProductServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class ProductService implements ProductServiceI {
    @Autowired
    private ProductRepositoryDAO repo;

    @Override
    public Product save(Product productEntity) {
        Product product = repo.save(productEntity);
        return product;
    }

    @Override
    public Product update(Product productEntity) {
        Product product = repo.save(productEntity);
        return product;
    }

    @Override
    public void delete(Product productEntity) {
        repo.delete(productEntity);
    }

    @Override
    public List<Product> getList() {
        return repo.findAll();
    }

    @Override
    public Product getOne(UUID id) {
        Product product = repo.getReferenceById(id);
        return product;
    }

    @Override
    public Page<Product> getProducts(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return repo.findAll(pageable);
    }

    @Override
    public Page<Product> getProductsbyPrice(BigDecimal pricemin, BigDecimal pricemax, int pageNumber, int pagesize) {
        Pageable page = PageRequest.of(pageNumber, pagesize);
        return repo.findAllByPrice(pricemin, pricemax, page);
    }
}
