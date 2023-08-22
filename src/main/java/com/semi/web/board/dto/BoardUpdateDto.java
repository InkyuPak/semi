package com.semi.web.board.dto;

import com.semi.domain.board.Board;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardUpdateDto {

    private Long id;
    @NotBlank(message = "제목을 입력해주세요")
    private String btitle;
    @NotBlank(message = "수정시 내용 입력은 필수 입니다.")
    private String bcontent;
    private String bfile;
    private String bsfile;

    public Board toEntity(){
        return Board.builder()
                .btitle(btitle)
                .bcontent(bcontent)
                .bfile(bfile)
                .bsfile(bsfile)
                .bhits(0)
                .build();
    }
}
