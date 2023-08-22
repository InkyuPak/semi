package com.semi.web.board.dto;

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
public class BoardCreateDto {

    @NotBlank(message = "제목을 입력해주세요")
    private String btitle;
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
