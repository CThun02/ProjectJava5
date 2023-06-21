package java5.poly.assignment.repository.RepoI;

import java5.poly.assignment.model.Account;
import java5.poly.assignment.model.UserOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.List;

@Repository
public interface UserOrderRepositoryDAO extends JpaRepository<UserOrder, UUID> {
    public List<UserOrder> getUserOrderByTrangThai(int trangThai);
    public List<UserOrder> getUserOrderByAccount(Account account);

}
