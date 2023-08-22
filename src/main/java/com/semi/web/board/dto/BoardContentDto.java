package com.semi.web.board.dto;

import com.semi.domain.board.Board;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardContentDto {

    private Long id;
    private String btitle;
    private Integer bhits;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private String bcontent;
    private String bfile;
    private String bsfile;

    public BoardContentDto(Board board) {
        this.id= board.getId();
        this.btitle= board.getBtitle();
        this.bhits= board.getBhits();
        this.createdDate= board.getCreatedDate();
        this.modifiedDate= board.getModifiedDate();
        this.bcontent= board.getBcontent();
        this.bfile= board.getBfile();
        this.bsfile= board.getBsfile();
    }
}
