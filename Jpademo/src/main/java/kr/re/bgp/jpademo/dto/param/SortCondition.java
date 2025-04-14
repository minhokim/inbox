package kr.re.bgp.jpademo.dto.param;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SortCondition {
    private String sortProperty;
    private SortDirectionEnum sortDirection;
}
