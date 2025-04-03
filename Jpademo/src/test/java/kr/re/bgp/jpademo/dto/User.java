package kr.re.bgp.jpademo.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.ArrayList;
import java.util.List;


public class User {
    public int id;
    public String name;

    @JsonManagedReference
    public List<Item> userItems = new ArrayList<Item>();

    public User(int i, String john) {
        this.id = i;
        this.name = john;
    }

    public void addItem(Item item) {
        this.userItems.add(item);
    }
}
