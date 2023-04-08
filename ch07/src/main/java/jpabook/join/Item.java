package jpabook.join;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED) //상속 매핑 - 부모 클래스 : 조인 전략
// 상속받은 엔티티 테이블에는 item_id, name, price를 제외한 값만 저장
@DiscriminatorColumn(name = "DTYPE") // 부모 클래스에 구분 칼럼 지정 기본값 - DTYPE
public abstract class Item {

    @Id
    @GeneratedValue
    @Column(name = "ITEM_ID")
    private Long id;

    private String name;
    private int price;
}
