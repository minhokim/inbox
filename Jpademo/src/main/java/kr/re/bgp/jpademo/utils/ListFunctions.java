package kr.re.bgp.jpademo.utils;

import kr.re.bgp.jpademo.dto.param.ListParam;
import kr.re.bgp.jpademo.dto.param.SearchCondition;
import kr.re.bgp.jpademo.dto.param.SortCondition;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class ListFunctions {

    public static Map<String, Object> buildQueryParam(ListParam param) {
        Map<String, Object> map = new HashMap<>();
        for (SearchCondition search : param.getSearchConditions()) {
            if (StringUtils.isEmpty(search.getSearchKey())) {
                continue;
            }
            map.put(search.getSearchKey(), search.getSearchValue());
        }

        for (SortCondition sort : param.getSortConditions()) {
            if (!StringUtils.isEmpty(sort.getSortProperty()) && null != sort.getSortDirection()) {
                map.put("sortProperty", param.getSortConditions().get(0).getSortProperty());
                map.put("sortDirection", param.getSortConditions().get(0).getSortDirection());
            }
        }

        map.put("size", param.getSize());
        map.put("page", param.getPage());
        return map;
    }
}
