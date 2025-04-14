package kr.re.bgp.jpademo.dto.param;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SearchCondition {
    private String searchKey;
    private Object searchValue;
}
