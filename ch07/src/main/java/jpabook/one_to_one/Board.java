package jpabook.one_to_one;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Board {

//    create table Board (
//        BOARD_ID bigint not null,
//        title varchar(255),
//    primary key (BOARD_ID)
//    )

    @Id
    @GeneratedValue
    @Column(name = "BOARD_ID")
    private Long id;

    private String title;

    @OneToOne(mappedBy = "board") // 일대일 식별 관계
    private BoardDetail boardDetail;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BoardDetail getBoardDetail() {
        return boardDetail;
    }

    public void setBoardDetail(BoardDetail boardDetail) {
        this.boardDetail = boardDetail;
    }
}
