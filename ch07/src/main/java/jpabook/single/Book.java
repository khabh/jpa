package jpabook.single;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("B")  // 구분 컬럼 사용 필수
public class Book extends Item {

    private String author;
    private String isbn;
}
