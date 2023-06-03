package java5.poly.assignment.service.ServiceI;


import java5.poly.assignment.model.Category;

import java.util.List;
import java.util.UUID;

public interface CategoryServicel {
    public Category save(Category category);
    public Category update(Category category);
    public void delete(Category category);
    public List<Category> getList();
    public Category getOne(UUID id);
}
