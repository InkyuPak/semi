package com.semi.domain.board;

import com.semi.domain.member.BaseEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor
public class Board extends BaseEntity {


    @Id @GeneratedValue
    @Column(name = "board_id")
    private Long id;

    @Column(nullable = false)
    private String btitle;

    private String bcontent;
    private Integer bhits;
    private String bfile;
    private String bsfile;

/*

    public Board(String btitle, String bcontent, Integer bhits, String bfile, String bsfile) {
        this.btitle = btitle;
        this.bcontent = bcontent;
        this.bhits = bhits;
        this.bfile = bfile;
        this.bsfile = bsfile;
    }

*/

    @Builder
    public Board(String btitle, String bcontent, Integer bhits, String bfile, String bsfile) {
        this.id = id;
        this.btitle = btitle;
        this.bcontent = bcontent;
        this.bhits = bhits;
        this.bfile = bfile;
        this.bsfile = bsfile;
    }


    public void update(String btitle, String bcontent) {
        this.btitle = btitle;
        this.bcontent = bcontent;
    }



}
