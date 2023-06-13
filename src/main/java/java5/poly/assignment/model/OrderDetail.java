package java5.poly.assignment.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orderdetail")
public class OrderDetail implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idpro")
    private Product product;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idorder")
    private UserOrder userOrder;

    @Column(name = "gia")
    private Double gia;

    @Column(name = "quantity")
    private Integer soluong;
}
