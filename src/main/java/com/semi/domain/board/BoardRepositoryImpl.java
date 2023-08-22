package com.semi.domain.board;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.semi.web.board.dto.SearchCondition;
import com.semi.web.board.dto.SearchType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.function.Supplier;

import static com.semi.domain.board.QBoard.board;

public class BoardRepositoryImpl implements BoardCustomRepository{

    public final JPAQueryFactory queryFactory;

    public BoardRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<Board> selectBoardList(SearchCondition searchCondition, Pageable pageable) {
        List<Board> content = getBoardList(searchCondition, pageable);
        Long count = getCount(searchCondition);
        return new PageImpl<>(content, pageable, count);
    }

    private Long getCount(SearchCondition searchCondition) {
        Long count = queryFactory
                .select(board.count())
                .from(board)
                .where(containSearch(searchCondition))
                .fetchOne();
        return count;
    }

    private BooleanBuilder containSearch(SearchCondition searchCondition) {
        return searchCondition != null ? isSearchable(searchCondition.getType(), searchCondition.getContent()) : null;
    }

    private List<Board> getBoardList(SearchCondition searchCondition, Pageable pageable) {
        return queryFactory
                .select(board)
                .from(board)
                .where(containSearch(searchCondition))
                .orderBy(board.createdDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    @Override
    public List<Board> search(SearchCondition condition) {
        return queryFactory
                .selectFrom(board)
                .where(isSearchable(condition.getType(), condition.getContent()))
                .orderBy(board.createdDate.desc())
                .offset(1)
                .limit(10)
                .fetch();
    }


    BooleanBuilder nullSafeBuilder(Supplier<BooleanExpression> f) {
        try {
            return new BooleanBuilder(f.get());
        } catch (Exception e) {
            return new BooleanBuilder();
        }
    }

    BooleanBuilder userEq(String content) {
        return null;
    }
    BooleanBuilder titleCt(String content) {
        return nullSafeBuilder(() -> board.btitle.contains(content));
    }
    BooleanBuilder contentCt(String content) {
        return nullSafeBuilder(() -> board.bcontent.contains(content));
    }
    private BooleanBuilder isSearchable(SearchType type, String content) {
        if (type == SearchType.TIT) {
            return titleCt(content);
        }
        else if(type == SearchType.STUD) {
            return userEq(content);
        }
        else {
            return titleCt(content).or(contentCt(content));
        }
    }
}
