package java5.poly.assignment.service.ServiceI;

import java5.poly.assignment.model.Account;
import java5.poly.assignment.model.UserOrder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

public interface UserOrderServiceI {
    public UserOrder save(UserOrder userOrder);
    public UserOrder update(UserOrder userOrder);
    public void delete(UserOrder userOrder);
    public List<UserOrder> getList();
    public UserOrder getOne(UUID ID);
    public List<UserOrder> getUserOrderByTrangThai(int tt);
    public List<UserOrder> getUserOrderByAccount(Account account);

}
