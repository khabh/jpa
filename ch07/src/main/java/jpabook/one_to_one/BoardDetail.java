package jpabook.one_to_one;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

@Entity
public class BoardDetail {

//    create table BoardDetail (
//        BOARD_ID bigint not null,
//        content varchar(255),
//    primary key (BOARD_ID)
//    )

    @Id
    private Long boardId;

    @MapsId // 일대일 식별 관계
    @OneToOne
    @JoinColumn(name = "BOARD_ID")
    private Board board;

    private String content;


    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getBoardId() {
        return boardId;
    }

    public void setBoardId(Long boardId) {
        this.boardId = boardId;
    }
}
