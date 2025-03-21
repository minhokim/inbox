package kr.re.bgp.jpademo.repository;

import kr.re.bgp.jpademo.entity.ChargePlace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChargePlaceRepository extends JpaRepository<ChargePlace, Long> {

}
