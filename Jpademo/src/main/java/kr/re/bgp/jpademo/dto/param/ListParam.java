package kr.re.bgp.jpademo.dto.param;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class ListParam {
    @NotNull(message = "Size is required")
    private Integer size;

    @NotNull(message = "Page is required")
    private Integer page;

    private List<SearchCondition> searchConditions;

    private List<SortCondition> sortConditions;

    public ListParam withSize(Integer v) {
        this.size = v;
        return this;
    }

    public ListParam withPage(Integer v) {
        this.page = v;
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
