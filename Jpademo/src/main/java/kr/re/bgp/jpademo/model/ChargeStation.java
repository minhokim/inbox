package kr.re.bgp.jpademo.model;

import jakarta.persistence.*;
import lombok.*;
import org.joda.time.DateTime;

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
    private DateTime created;

    @ManyToOne(optional = true)    //optional = false INNER JOIN
    @JoinColumn(name = "place_id")
    private ChargePlace place;      //외래 키를 가진 엔티티가 연관관계의 소유자

    public void setPlace(ChargePlace place) {
        this.place = place;
        place.getStations().add(this);
    }

}
