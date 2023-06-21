package java5.poly.assignment.service.ServiceImpl;

import java5.poly.assignment.model.Account;
import java5.poly.assignment.model.UserOrder;
import java5.poly.assignment.repository.RepoI.UserOrderRepositoryDAO;
import java5.poly.assignment.service.ServiceI.UserOrderServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserOrderService implements UserOrderServiceI {

    @Autowired
    private UserOrderRepositoryDAO repo;

    @Override
    public UserOrder save(UserOrder userOrder) {
        UserOrder userOrder1 = repo.save(userOrder);
        return userOrder1;
    }

    @Override
    public UserOrder update(UserOrder userOrder) {
        UserOrder userOrder1 = repo.save(userOrder);
        return userOrder1;
    }

    @Override
    public void delete(UserOrder userOrder) {
        repo.delete(userOrder);
    }

    @Override
    public List<UserOrder> getList() {
        return repo.findAll();
    }

    @Override
    public UserOrder getOne(UUID ID) {
        return repo.getReferenceById(ID);
    }

    @Override
    public List<UserOrder> getUserOrderByTrangThai(int tt) {
        return repo.getUserOrderByTrangThai(tt);
    }

    @Override
    public List<UserOrder> getUserOrderByAccount(Account account) {
        return repo.getUserOrderByAccount(account);
    }
}
