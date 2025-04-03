package kr.re.bgp.jpademo.repository;

import kr.re.bgp.jpademo.entity.Connector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConnectorRepository extends JpaRepository<Connector, Long> {}
