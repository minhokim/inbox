package kr.re.bgp.jpademo.mapper;

import kr.re.bgp.jpademo.dto.param.ListParam;
import kr.re.bgp.jpademo.entity.ChargePlace;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface BgpMapper {
    List<ChargePlace> chargePlaces(Map<String, Object> params);
    int chargePlaceTotalCount(Map<String, Object> params);
}
