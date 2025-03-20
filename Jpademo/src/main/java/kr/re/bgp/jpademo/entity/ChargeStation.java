package kr.re.bgp.jpademo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "charge_station")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChargeStation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stationId;
    private String stationName;
    private String chargeBoxId;

    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="Asia/Seoul")
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    @CreationTimestamp
    private Timestamp created;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)    //optional = false INNER JOIN
    @JoinColumn(name = "place_id")
    private ChargePlace place;      //외래 키를 가진 엔티티가 연관관계의 소유자

    @Transient
    private Long placeId;

    public void setPlace(ChargePlace place) {
        this.place = place;
        place.getStations().add(this);
    }

}
