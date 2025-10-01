package kr.re.bgp.jpademo.pattern.Iterator;

import lombok.Data;

@Data
public class Book {
    private String bookName;

    public Book(String bookName) {
        this.bookName = bookName;
    }
}
