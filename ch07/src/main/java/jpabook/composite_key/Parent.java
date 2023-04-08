package jpabook.composite_key;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
//@IdClass(ParentId.class)
public class Parent {
//    IdClass
//    @Id
//    @Column(name = "PARENT_ID1")
//    private String id1;
//
//    @Id
//    @Column(name = "PARENT_ID2")
//    private String id2;

//    @EmbeddedId
//    private ParentId id;
//
//    private String name;

//    public String getId2() {
//        return id2;
//    }
//
//    public void setId2(String id2) {
//        this.id2 = id2;
//    }
//
//    public String getId1() {
//        return id1;
//    }
//
//    public void setId1(String id1) {
//        this.id1 = id1;
//    }

    @Id
    @Column(name = "PARENT_ID")
    private String id;

    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
