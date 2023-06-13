package java5.poly.assignment.service.ServiceI;

import java5.poly.assignment.model.Account;

import java.util.List;
import java.util.UUID;

public interface AccountServiceI {
    public Account save(Account account);
    public Account update(Account account);
    public void delete(Account account);
    public List<Account> getList();
    public Account getOne(String user);
    public Account getAccountByEmail(String email);
    public Boolean isAccountExist(String email);
}
