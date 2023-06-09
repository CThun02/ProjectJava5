package java5.poly.assignment.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.apache.tomcat.util.codec.binary.Base64;

import java.io.*;
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
public @Data
class Product implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID ID;

    @Column(name = "ma")
    @NotBlank
    private String ma;

    @Column(name = "ten")
    @NotBlank
    private String ten;

    @Column(name = "img")
    private String img;

    @Column(name = "gia")
    @Min(value = 1)
    @NotNull
    private BigDecimal gia;

    @Temporal(TemporalType.DATE)
    @NotNull
    private LocalDate ngayTao;

    @Column(name = "soLuongTon")
    @Min(value = 1)
    @NotNull
    private Integer soLuongTon;

    @ManyToOne
    @JoinColumn(name = "idCate")
    @NotNull
    private Category category;

}
