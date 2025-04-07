package kr.re.bgp.jpademo.dto.param;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SortCondition {
    private String sortKey;
    private SortDirectionEnum sortDirection;

    public SortCondition withSortKey(String sortKey) {
        this.sortKey = sortKey;
        return this;
    }

    public SortCondition withSortDirection(SortDirectionEnum sortDirection) {
        this.sortDirection = sortDirection;
        return this;
    }
}
