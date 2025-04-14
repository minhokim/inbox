package kr.re.bgp.jpademo.dto.param;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SortCondition {
    private String sortProperty;
    private SortDirectionEnum sortDirection = SortDirectionEnum.ASC;;

    public SortCondition withSortProperty(String sortProperty) {
        this.sortProperty = sortProperty;
        return this;
    }

    public SortCondition withSortDirection(SortDirectionEnum sortDirection) {
        this.sortDirection = sortDirection;
        return this;
    }
}
