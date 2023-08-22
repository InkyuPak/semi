package com.semi.web.board.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class SearchCondition {
    String content;
    SearchType type;

    public SearchCondition(String content, SearchType type){
        this.content = content;
        this.type = type;
    }

}
