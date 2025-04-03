package kr.re.bgp.jpademo.dto.param;

import lombok.Data;

@Data
public class SearchCondition {
    private String searchKey;
    private Object searchValue;

    public SearchCondition withSearchKey(String v) {
        this.searchKey = v;
        return this;
    }

    public SearchCondition withSearchValue(Object v) {
        this.searchValue = v;
        return this;
    }
}
