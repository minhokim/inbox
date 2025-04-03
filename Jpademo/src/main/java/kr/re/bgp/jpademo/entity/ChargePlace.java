package kr.re.bgp.jpademo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "charge_place")
@Setter
@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ChargePlace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long placeId;
    private String placeName;
    private Double latitude;
    private Double longitude;

    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="Asia/Seoul")
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    @CreationTimestamp
    private Timestamp created;

    @JsonIgnore
    @OneToMany(mappedBy = "place", fetch = FetchType.LAZY)  //OneToMany의 기본 설정은 FetchType.LAZY
    private List<ChargeStation> stations = new ArrayList<>();

}
