package hello.hellospring.domain;

import jakarta.persistence.*;

@Entity
public class Member {

    // PK 등록. DB가 자동으로 ID를 생성해주도록 설정해 두었으므로 GenerationType.IDENTITY 사용
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
