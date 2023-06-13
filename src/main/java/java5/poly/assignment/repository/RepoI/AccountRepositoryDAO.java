package java5.poly.assignment.repository.RepoI;

import java5.poly.assignment.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface AccountRepositoryDAO extends JpaRepository<Account, String> {
    public Account getAccountByUserName(String username);
    public Account findAccountByEmail(String email);
}
