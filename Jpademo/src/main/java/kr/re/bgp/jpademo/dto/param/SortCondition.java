package kr.re.bgp.jpademo.dto.param;

import lombok.Data;

@Data
public class SortCondition {
    private String sortKey;
    private SortDirectionEnum sortDirection;
}
