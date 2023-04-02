package jpabook.start;


import java.util.Date;
import javax.persistence.*;

// final, enum, interface, inner 클래스에는 사용할 수 없다
// 저장할 필드에 final을 사용하면 안 된다.
@Entity
// 엔티티와 매핑할 테이블 지정, 생략하면 매핑한 엔티티 이름을 테이블 이름으로 사용한다
@Table(name="MEMBER", uniqueConstraints = {@UniqueConstraint(
    name = "NAME_AGE_UNIQUE",
    columnNames = {"NAME", "AGE"})})
public class Member {

    @Id // 기본키 직접 할당 - em.persist()로 엔티티를 지정하기 전에 애플리케이션에서 기본 키 직접 할당
//    @GeneratedValue(strategy = GenerationType.IDENTITY) 데이터베이스가 기본 키를 자동으로 생성 + 데이터베이스 접근을 해야 식별자를 구할 수 있기 때문에 트랜잭션을 지원하는 쓰기 지연 동작 X

    @Column(name = "ID")
    private String id;

    @Column(name = "NAME", nullable = false, length = 10)
    private String username;
    private Integer age;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP) // 자바 날짜 타입 매핑 어노테이션
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    @Lob // 길이 제한이 없는 필드
    private String description;

    @Transient()
    private Integer temp;

    //Getter, Setter
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}