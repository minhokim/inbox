package kr.re.bgp.jpademo.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import kr.re.bgp.jpademo.dto.ConnectorDto;
import kr.re.bgp.jpademo.entity.ChargeStation;
import kr.re.bgp.jpademo.entity.Connector;
import kr.re.bgp.jpademo.repository.ChargeStationRepository;
import kr.re.bgp.jpademo.repository.ConnectorRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ConnectorService {
    private final EntityManager entityManager;
    private final ConnectorRepository connectorRepository;
    private final ChargeStationService stationService;
    private final ModelMapper modelMapper;

    public List<Connector> list() {
        return connectorRepository.findAll();
    }

    public Connector create(ConnectorDto dto) {
        ChargeStation station = retrieveStation(dto);

        Connector connector = modelMapper.map(dto, Connector.class);
        connector.setStation(station);

        return connectorRepository.save(connector);
    }

    private ChargeStation retrieveStation(ConnectorDto dto) {
        return stationService.retrieve(dto.getStationId());
    }

    public Connector update(Connector connector) {
        return connectorRepository.save(connector);
    }

    public void delete(Long id) {
        connectorRepository.deleteById(id);
    }

    public Connector retrieve(Long id) {
        return connectorRepository.findById(id).orElse(null);
    }

    public List<Connector> enhancedList() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Connector> criteriaQuery = builder.createQuery(Connector.class);
        Root<Connector> connector = criteriaQuery.from(Connector.class);

        criteriaQuery.select(connector);

        TypedQuery<Connector> query = entityManager.createQuery(criteriaQuery);
        List<Connector> connectors = query.getResultList();

        return connectors;
    }
}

