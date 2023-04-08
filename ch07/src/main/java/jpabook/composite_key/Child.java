package jpabook.composite_key;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

//EmbeddedId 1
//@Entity
//public class Child {
//
//    @Id
//    private String id;
//
//    @ManyToOne
//    @JoinColumns({
//        @JoinColumn(name = "PARENT_ID1"),
//        @JoinColumn(name = "PARENT_ID2")
//    })
//    private Parent parent;
//}

// EmbeddedId 2
@Entity
public class Child {
    @EmbeddedId
    private ChildId id;

    @MapsId("parentId")
    @ManyToOne
    @JoinColumn(name = "PARENT_ID")
    public Parent parent;

    private String name;
}