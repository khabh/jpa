package jpabook.start;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
public class Team {

    @Id
    @Column(name = "TEAM_ID")
    private String id;

    private String name;

    @OneToMany(mappedBy = "team") // 연관관계의 주인이 아닌 곳에 추가
//    @JoinColumn(name = "TEAM_ID") 다대일 매핑
    private List<Member> members = new ArrayList<>();

    public Team() {}

    public Team(String id, String name) {
        this.id = id;
        this.name = name;
    }

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


    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }
}
