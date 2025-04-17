package kr.re.bgp.jpademo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.re.bgp.jpademo.dto.Item;
import kr.re.bgp.jpademo.dto.User;
import org.junit.jupiter.api.Test;

public class BidirectionRelationTest {

    @Test
    public void bidrirectionRelationTest() throws JsonProcessingException {
        User user = new User(1, "John");
        Item item = new Item(2, "book", user);
        user.addItem(item);

        final String itemJson = new ObjectMapper().writeValueAsString(item);
        final String userJson = new ObjectMapper().writeValueAsString(user);

        System.out.println("itemJson: " + itemJson);
        System.out.println("userJson: " + userJson);

    }
}
