package kr.re.bgp.jpademo.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;

public class Item {
    public int id;
    public String itemName;

    @JsonBackReference
    public User owner;

    public Item(int i, String book, User user) {
        this.id = i;
        this.itemName = book;
        this.owner = user;
    }
}
