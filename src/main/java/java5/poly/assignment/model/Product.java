package java5.poly.assignment.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID ID;

    @Column(name = "ma")
    private String ma;

    @Column(name = "ten")
    private String ten;

    @Column(name = "img")
    private String img;

    @Column(name = "gia")
    private BigDecimal gia;

    @Temporal(TemporalType.DATE)
    private LocalDate ngayTao;

    @Column(name = "soLuongTon")
    private int soLuongTon;

    @ManyToOne
    @JoinColumn(name = "idCate")
    private Category category;
}
