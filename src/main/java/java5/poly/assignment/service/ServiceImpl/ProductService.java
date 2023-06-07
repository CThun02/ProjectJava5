package java5.poly.assignment.service.ServiceImpl;

import java5.poly.assignment.model.Category;
import java5.poly.assignment.model.Product;
import java5.poly.assignment.repository.RepoI.ProductRepositoryDAO;
import java5.poly.assignment.service.ServiceI.ProductServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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
    public Page<Product> getProducts(int pageNumber, int pageSize, Sort sort) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        return repo.findAll(pageable);
    }

    @Override
    public Page<Product> getProductsbyPrice(Double pricemin, Double pricemax, int pageNumber, int pagesize) {
        Pageable page = PageRequest.of(pageNumber, pagesize);
        return repo.findByGiaBetween(pricemin, pricemax, page);
    }

    @Override
    public Page<Product> getProductsbyName(String name, int pageNumber, int pageSize) {
        Pageable page = PageRequest.of(pageNumber, pageSize);
        return repo.findAllByName(name, page);
    }

    @Override
    public Page<Product> findProductsByCategory(Category category, int pageNumber, int pageSize) {
        Pageable page = PageRequest.of(pageNumber, pageSize);
        return repo.findProductsByCategory(category, page);
    }

}
