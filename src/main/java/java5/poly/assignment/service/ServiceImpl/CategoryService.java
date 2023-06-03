package java5.poly.assignment.service.ServiceImpl;

import java5.poly.assignment.model.Category;
import java5.poly.assignment.repository.RepoI.CategoryRepositoryDAO;
import java5.poly.assignment.service.ServiceI.CategoryServicel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryService implements CategoryServicel {
    @Autowired
    private CategoryRepositoryDAO repo;

    @Override
    public Category save(Category category) {
        Category category1 = repo.save(category);
        return category1;
    }

    @Override
    public Category update(Category category) {
        Category category1 = repo.save(category);
        return category1;
    }

    @Override
    public void delete(Category category) {
        repo.delete(category);
    }

    @Override
    public List<Category> getList() {
        return repo.findAll();
    }

    @Override
    public Category getOne(UUID id) {
        return repo.getReferenceById(id);
    }
}
