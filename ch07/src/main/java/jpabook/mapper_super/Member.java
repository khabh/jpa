package jpabook.mapper_super;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@AttributeOverrides({
    @AttributeOverride(name = "id", column = @Column(name = "MEMEBER_ID")),
    @AttributeOverride(name = "name", column = @Column(name = "MEMEBR_NAME"))
})
public class Member extends BaseEntity {

    private String email;
}
