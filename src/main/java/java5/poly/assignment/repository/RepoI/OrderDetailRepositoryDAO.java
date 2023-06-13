package java5.poly.assignment.repository.RepoI;

import java5.poly.assignment.model.OrderDetail;
import java5.poly.assignment.model.UserOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.List;

@Repository
public interface OrderDetailRepositoryDAO extends JpaRepository<OrderDetail, UUID> {
    public List<OrderDetail> getOrderDetailByUserOrder(UserOrder userOrder);
}
