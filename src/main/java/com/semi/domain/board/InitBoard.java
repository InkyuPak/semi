package com.semi.domain.board;

import com.semi.domain.member.Gender;
import com.semi.domain.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Profile("local")
@Component
@RequiredArgsConstructor
public class InitBoard {

    private final InitBoardService initBoardService;

    @PostConstruct
    public void init(){
        initBoardService.init();
    }

    @Component
    static class InitBoardService{
        @PersistenceContext
        private EntityManager em;

        @Transactional
        public void init(){
            for (int i=0; i<100; i++){
                em.persist(new Board("test "+i+"번째 게시글입니다.", "123", 1, "null", "null"));
            }
        }
    }
}
