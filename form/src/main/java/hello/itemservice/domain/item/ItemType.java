package hello.itemservice.domain.item;

import java.awt.print.Book;

public enum ItemType {
  BOOK("도서"), FOOD("음식"), ETC("기타");
  
  private final String description;
  
  ItemType(String description) {
    this.description = description;
  }
}
