package kr.re.bgp.jpademo.pattern.Iterator;

import java.util.Iterator;

public class BookMain {
    public static void main(String[] args) {
        BookShelf bookShelf = new BookShelf(4);
        bookShelf.appendBook(new Book("AA"));
        bookShelf.appendBook(new Book("BB"));
        bookShelf.appendBook(new Book("CC"));
        bookShelf.appendBook(new Book("DD"));

        Iterator<Book> iterator = bookShelf.iterator();
        while (iterator.hasNext()) {
            Book book = iterator.next();
            System.out.println(book.getBookName());
        }
        System.out.println();

        for (Book book : bookShelf) {
            System.out.println(book.getBookName());
        }
        System.out.println();

    }
}
