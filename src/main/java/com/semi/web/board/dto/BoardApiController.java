package com.semi.web.board.dto;

import com.semi.web.board.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class BoardApiController {

    private final BoardService boardService;

    @DeleteMapping("boardDelete/{id}")
    public void boardDelete(@PathVariable Long id){
        log.info("id = {} ", id);
        boardService.boardDelete(id);
    }

}
