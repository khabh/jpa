package jpabook.single;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("M") // 엔티티 저장 시에 구분 컬럼에 입력할 값 지정
public class Movie extends Item {

    private String director;
    private String actor;
}
