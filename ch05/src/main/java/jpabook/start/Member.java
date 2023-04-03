package jpabook.start;

import javax.persistence.*;

@Entity
@Table()
public class Member {

    @Id
    @Column(name = "MEMBER_ID")
    private String id;

    private String username;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    public Member() {}

    public Member(String id, String username) {
        this.id = id;
        this.username = username;
    }

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

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        if (this.team != null) {
            this.team.getMembers().remove(this);
            // 데이터베이스 외래키는 잘 변경됐지만 team에서 getMembers() 메서드를 호출하면 this가 반환되기 때문에 관계를 제거하는 것이 안전하다
        }
        this.team = team;
        team.getMembers().add(this); // 안전한 양방향 관계 설정
    }
}