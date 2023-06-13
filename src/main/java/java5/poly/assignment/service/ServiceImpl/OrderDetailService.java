package java5.poly.assignment.service.ServiceImpl;

import java5.poly.assignment.model.OrderDetail;
import java5.poly.assignment.model.UserOrder;
import java5.poly.assignment.repository.RepoI.OrderDetailRepositoryDAO;
import java5.poly.assignment.service.ServiceI.OrderDetailServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrderDetailService implements OrderDetailServiceI {
    @Autowired
    private OrderDetailRepositoryDAO repo;

    @Override
    public OrderDetail save(OrderDetail orderDetail) {
        OrderDetail orderDetail1 = repo.save(orderDetail);
        return orderDetail1;
    }

    @Override
    public OrderDetail update(OrderDetail orderDetail) {
        OrderDetail orderDetail1 = repo.save(orderDetail);
        return orderDetail1;
    }

    @Override
    public void delete(OrderDetail orderDetail) {
        repo.delete(orderDetail);
    }

    @Override
    public List<OrderDetail> getList() {
        return repo.findAll();
    }

    @Override
    public OrderDetail getOne(UUID id) {
        return repo.getReferenceById(id);
    }

    @Override
    public List<OrderDetail> getOrderDetailByUserOrder(UserOrder userOrder) {
        return repo.getOrderDetailByUserOrder(userOrder);
    }
}
