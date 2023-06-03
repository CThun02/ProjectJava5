package java5.poly.assignment.repository.RepoI;

import java5.poly.assignment.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CategoryRepositoryDAO extends JpaRepository<Category, UUID> {
}
