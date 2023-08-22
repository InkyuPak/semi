package com.semi.web.board;

import com.semi.domain.board.Board;
import com.semi.domain.board.BoardRepository;
import com.semi.web.board.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public Page<BoardListDto> boardListDtos(SearchCondition searchCondition, Pageable pageable) {
        Page<Board> boards = boardRepository.selectBoardList(searchCondition, pageable);
        Page<BoardListDto> list = boards.map(board -> new BoardListDto(board));
        return list;
    }

    /**
     * boardSearch
     */
    public List<BoardListDto> searchBoard(SearchCondition searchCondition) {
        List<Board> search = boardRepository.search(searchCondition);
        List<BoardListDto> list = search.stream()
                .map(board -> new BoardListDto(board)) // 생성자를 호출하여 BoardListDto 객체를 생성합니다.
                .collect(Collectors.toList());
        for (BoardListDto boardListDto : list) {
            System.out.println("boardListDto = " + boardListDto);
        }
        return list;
    }
    /**
     * boardDelete
     */
    @Transactional
    public void boardDelete(Long id){
        boardRepository.deleteById(id);
    }

    public Page<BoardListDto> boardListSelect(Pageable pageable){
        Page<Board> boardList = boardRepository.findAll(pageable);
        Page<BoardListDto> boards = boardList.map(board -> new BoardListDto(board));
        return boards;
    }

    public BoardContentDto boardContentSelect(Long id) {
        Board boardContent = boardRepository.findContentById(id);
        BoardContentDto boardContentDto = new BoardContentDto(boardContent);
        return boardContentDto;
        /*
        return BoardContentDto.builder()
                .btitle(boardContent.getBtitle())
                .bcontent(boardContent.getBcontent())
                .bhits(boardContent.getBhits())
                .bfile(boardContent.getBfile())
                .bsfile(boardContent.getBsfile())
                .createdDate(boardContent.getCreatedDate())
                .modifiedDate(boardContent.getModifiedDate())
                .id(boardContent.getId())
                .build();
         */
    }

    @Transactional
    public Long boardCreate(BoardCreateDto form) {
        Board board = form.toEntity();
        boardRepository.save(board);
        return board.getId();

    }

    @Transactional
    public Long boardUpdate(Long id, BoardUpdateDto form) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("게시물을 찾을 수 없습니다"));

        board.update(form.getBtitle(), form.getBcontent());

        boardRepository.save(board);
        return board.getId();
    }
}
