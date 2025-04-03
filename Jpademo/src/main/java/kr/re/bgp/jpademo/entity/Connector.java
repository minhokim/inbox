package kr.re.bgp.jpademo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Table(name = "ocpp_connector")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Connector {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer connectorId;

    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="Asia/Seoul")
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    @CreationTimestamp
    private Timestamp created;

    @ManyToOne(optional = false, fetch = FetchType.LAZY) //optional = false INNER JOIN
    @JoinColumn(name = "station_id")
    private ChargeStation station;
}
