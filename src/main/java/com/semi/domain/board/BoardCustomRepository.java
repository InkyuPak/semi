package com.semi.domain.board;

import com.semi.web.board.dto.SearchCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BoardCustomRepository {
    List<Board> search(SearchCondition searchCondition);

    Page<Board> selectBoardList(SearchCondition searchCondition, Pageable pageable);
}
