package java5.poly.assignment.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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

    public String getIMGByteStream() {
        if(this.img!=null) {
            File file = new File(this.img);
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            if (fis != null) {
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                byte[] buf = new byte[1024];
                try {
                    for (int readNum; (readNum = fis.read(buf)) != -1; ) {
                        //Writes to this byte array output stream
                        bos.write(buf, 0, readNum);
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                byte[] bytes = bos.toByteArray();

                byte[] encodeBase64 = Base64.encodeBase64(bytes, true);
                String base64Encoded = null;
                try {
                    base64Encoded = new String(encodeBase64, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                return base64Encoded;
            }
        }
        return "";
    }
}
