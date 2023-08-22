package com.semi.web.board.dto;

public enum SearchType {
    TIT("제목"), STUD("작성자"), TITCONT("제목+작성자");

    private final String description;

    SearchType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
