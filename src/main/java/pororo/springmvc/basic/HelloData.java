package pororo.springmvc.basic;

import lombok.*;


//@Getter @Setter @EqualsAndHashCode @ToString @RequiredArgsConstructor 를 모두 만들어줌
@Data
public class HelloData {
    private String username;
    private int age;
}
