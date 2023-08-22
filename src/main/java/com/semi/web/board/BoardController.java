package com.semi.web.board;

import com.semi.web.board.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
@Slf4j
public class BoardController {

    private final BoardService boardService;

    @PostMapping("boardSearch")
    public String boardSearch(@RequestParam("searchType") SearchType searchType,
                              @RequestParam("keyword") String keyword, Pageable pageable,
                              Model model){
        log.info("searchType = {}", searchType);
        log.info("keyword = {}", keyword);
        Page<BoardListDto> boards = boardService.boardListDtos(new SearchCondition(keyword, searchType), pageable);
        model.addAttribute("list", boards);
        model.addAttribute("maxPage", 5);
        model.addAttribute("searchVal", keyword);

        pageModelPut(boards, model);
        return "board/boardList";
    }
    @GetMapping
    public String boards(SearchType searchType, String keyword, Pageable pageable,
                            Model model){
        Page<BoardListDto> boards = boardService.boardListDtos(new SearchCondition(keyword, searchType), pageable);
        model.addAttribute("list", boards);
        model.addAttribute("maxPage", 5);
        model.addAttribute("searchVal", keyword);

        pageModelPut(boards, model);
        return "board/boardList";
    }

    private void pageModelPut(Page<BoardListDto> results, Model model){
        model.addAttribute("totalCount", results.getTotalElements());
        model.addAttribute("size",  results.getPageable().getPageSize());
        model.addAttribute("number",  results.getPageable().getPageNumber());
    }

    //@PostMapping("/boardSearch")
    public String searchBoard(@RequestParam("searchType") SearchType searchType,
                              @RequestParam("keyword") String keyword, Model model, Pageable pageable){
        log.info("searchType = {}", searchType);
        log.info("keyword = {}", keyword);
        model.addAttribute("keyword", keyword);

        if (StringUtils.hasText(keyword)) {
            model.addAttribute("boards", boardService.searchBoard(new SearchCondition(keyword, searchType)));
        }
        return "redirect:/board/boardList";
    }

    @ModelAttribute("searchTypes")
    public SearchType[] searchTypes(){
        return SearchType.values();
    }

    /**
     * boardUpdate 게시글 수정
     */
    @GetMapping("boardUpdate/{id}")
    public String boardUpdate(@PathVariable Long id, Model model){
        BoardContentDto boardContentDto = boardService.boardContentSelect(id);
        model.addAttribute("boardContent", boardContentDto);
        return "board/boardUpdate";
    }

    /**
     * boardUpdate 게시글 수정
     */
    @PostMapping("boardUpdate/{id}")
    public String boardUpdate(@PathVariable Long id,
                              @Validated @ModelAttribute("boardContent") BoardUpdateDto form,
                              BindingResult result,
                              RedirectAttributes redirectAttributes) {
        log.info("id = {}", id);
        if (result.hasErrors()) {
            log.info("result = {}", result);
            return "board/boardUpdate";
        }
        boardService.boardUpdate(id, form);
        redirectAttributes.addFlashAttribute("update_result", "ok");
        return "redirect:/board/boardContent/{id}";
    }

    /**
     * boardList 
     * 페이징 처리와 정렬 -> 좀 더 손봐야할듯
     */
    //@GetMapping
    public String boardList(Pageable pageable,
                            Model model){
        Page<BoardListDto> boards = boardService.boardListSelect(pageable);
        model.addAttribute("boards", boards);

        // 현재 페이지 번호
        model.addAttribute("currentPage", boards.getPageable().getPageNumber());

        // 총 페이지 수
        model.addAttribute("totalPages", boards.getTotalPages());

        return "board/boardList";
    }

    /**
     * boardContent 게시글 상세보기
     */
    @GetMapping("/boardContent/{id}")
    public String boardContent(@PathVariable Long id, Model model){
        BoardContentDto boardContent = boardService.boardContentSelect(id);

        model.addAttribute("boardContent", boardContent);
        return "board/boardContent";
    }

    /**
     * boardCreate 게시글 등록화면 보여주기(GET)
     */
    @GetMapping("/boardCreate")
    public String boardCreate(Model model){
        model.addAttribute("form", new BoardCreateDto());
        return "board/boardCreate";
    }

    /**
     * boardCreate 게시글 등록(POST)
     */
    @PostMapping("/boardCreate")
    public String boardCreateSave(@Validated @ModelAttribute("form") BoardCreateDto form, BindingResult result,
                                  RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "board/boardCreate";
        }
        boardService.boardCreate(form);
        redirectAttributes.addAttribute("msg", "success");
        return "redirect:/";
    }
}
