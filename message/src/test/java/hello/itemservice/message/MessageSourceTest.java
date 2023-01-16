package hello.itemservice.message;

import static org.assertj.core.api.Assertions.*;

import java.util.Locale;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

@SpringBootTest
public class MessageSourceTest {
  
  @Autowired
  MessageSource ms;
  
  @Test
  public void helloMessage() throws Exception {
    String result = ms.getMessage("hello", null, null);
    assertThat(result).isEqualTo("안녕");
  }
  
  @Test
  public void notFoundMessageCode() throws Exception {
    assertThatThrownBy(() -> ms.getMessage("no_code", null, null)).isInstanceOf(
        NoSuchMessageException.class);
  }
  
  @Test
  public void notFoundMessageCodeDefaultMessage() throws Exception {
    String result = ms.getMessage("no_code", null, "기본 메시지", null);
    assertThat(result).isEqualTo("기본 메시지");
  }
  
  @Test
  public void argumentMessage() throws Exception {
    String result = ms.getMessage("hello.name", new Object[]{"Spring"}, null);
    assertThat(result).isEqualTo("안녕 Spring");
  }
  
  @Test
  void defaultLang() {
    assertThat(ms.getMessage("hello", null, null)).isEqualTo("안녕");
    assertThat(ms.getMessage("hello", null, Locale.KOREA)).isEqualTo("안녕");
  }
  
  @Test
  void enLang() {
    assertThat(ms.getMessage("hello", null, Locale.ENGLISH)).isEqualTo("hello");
  }
}
