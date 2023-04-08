package jpabook.mapper_super;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass // 테이블과 매핑되지 않고 매핑 정보 상속을 위해 사용
public abstract class BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
}
