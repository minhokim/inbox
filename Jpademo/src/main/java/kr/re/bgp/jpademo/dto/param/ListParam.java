package kr.re.bgp.jpademo.dto.param;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class ListParam {
    @NotNull(message = "Page is required")
    private Integer page;

    @NotNull(message = "Limit is required")
    private Integer limit;

    private List<SearchCondition> searchConditions;

    private List<SortCondition> sortConditions;

    public ListParam withPage(Integer v) {
        this.page = v;
        return this;
    }

    public ListParam withLimit(Integer v) {
        this.limit = v;
        return this;
    }

    public ListParam withSearchConditions(List<SearchCondition> v) {
        this.searchConditions = v;
        return this;
    }

    public ListParam withSortConditions(List<SortCondition> v) {
        this.sortConditions = v;
        return this;
    }
}
