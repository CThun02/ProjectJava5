package java5.poly.assignment.service.ServiceI;

import java5.poly.assignment.model.OrderDetail;
import java5.poly.assignment.model.UserOrder;

import java.util.List;
import java.util.UUID;

public interface OrderDetailServiceI {
    public OrderDetail save(OrderDetail orderDetail);
    public OrderDetail update(OrderDetail orderDetail);
    public void delete(OrderDetail orderDetail);
    public List<OrderDetail> getList();
    public OrderDetail getOne(UUID id);
    public List<OrderDetail> getOrderDetailByUserOrder(UserOrder userOrder);

}
