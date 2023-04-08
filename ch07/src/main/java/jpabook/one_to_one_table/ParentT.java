package jpabook.one_to_one_table;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class ParentT {
    @Id
    @GeneratedValue
    @Column(name = "PARENT_ID")
    private Long id;

    private String name;
//    일대일 테이블 조인
//    @OneToOne
//    @JoinTable(name = "PARENT_CHILD",
//            joinColumns = @JoinColumn(name = "PARENT_ID"),
//            inverseJoinColumns = @JoinColumn(name = "CHILD_ID"))
//    private ChildT child;

    @OneToMany
    @JoinTable(name = "PARENT_CHILD",
            joinColumns = @JoinColumn(name = "PARENT_ID"),
            inverseJoinColumns = @JoinColumn(name = "CHILD_ID") )
    private List<ChildT> child = new ArrayList<>();
}
