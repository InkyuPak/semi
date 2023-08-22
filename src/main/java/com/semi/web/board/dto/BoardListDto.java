package com.semi.web.board.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.semi.domain.board.Board;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardListDto {

    private Long id;
    private String btitle;
    private Integer bhits;
    private LocalDateTime createdDate;
    private String page;

    /**
     * boardList 전용
     */
    @QueryProjection
    public BoardListDto(Board board) {
        this.id = board.getId();
        this.btitle = board.getBtitle();
        this.bhits = board.getBhits();
        this.createdDate = board.getCreatedDate();
    }


}
