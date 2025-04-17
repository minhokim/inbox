package kr.re.bgp.jpademo.service;

import kr.re.bgp.jpademo.entity.ChargePlace;
import kr.re.bgp.jpademo.repository.ChargePlaceRepository;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import java.sql.Timestamp;


@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class ChargePlaceServiceTest {
    @Autowired
    private ChargePlaceRepository repository;

    @Test
    public void saveChargePlace() {

        DateTime dateTime = DateTime.now(DateTimeZone.UTC);
        Timestamp created = new Timestamp(dateTime.getMillis());

        ChargePlace chargePlace = ChargePlace.builder()
                .placeName("Test Place")
                .latitude(36.123)
                .longitude(127.123)
                .created(created)
                .build();

        System.out.println(chargePlace.toString());

        repository.save(chargePlace);
    }


}