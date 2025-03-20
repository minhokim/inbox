package kr.re.bgp.jpademo.repository;

import kr.re.bgp.jpademo.entity.ChargeStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChargeStationRepository extends JpaRepository<ChargeStation, Long> {}
