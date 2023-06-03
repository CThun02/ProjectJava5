package java5.poly.assignment.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orderdetail")
public class OrderDetail implements Serializable {
    @EmbeddedId
    private OrderDetailPrimaryKey primaryKey;

    @Column(name = "gia")
    private Double gia;

    @Column(name = "soLuong")
    private Integer soluong;

    @ManyToOne
    @JoinColumn(name = "idPro")
    private Product product;

    @ManyToOne @JoinColumn(name = "idOrder")
    private UserOrder order;
}
