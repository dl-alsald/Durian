package com.durianfirst.durian.repository;

import com.durianfirst.durian.dto.ItemDTO;
import com.durianfirst.durian.entity.Member;
import com.durianfirst.durian.entity.QHeart;
import com.durianfirst.durian.entity.QItem;
import com.durianfirst.durian.entity.QMember;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.hibernate.criterion.Projection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Repository
public class HeartQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public Page<ItemDTO> findHeartItem(String mid, Pageable pageable){
        QHeart heart = QHeart.heart;
        QItem item = QItem.item;
        QMember member = QMember.member;
        QueryResults<ItemDTO> itemDTOQueryResults = jpaQueryFactory
                .select(Projections.constructor(
                        ItemDTO.class, item.ino, item.iname, item.iprice, item.isaleStatus, item.icategory, item.idealway ,item.idescription,
                        item.ilocation, item.icondition, member.mid))
                .from(heart)
                .leftJoin(heart.item, item)
                .leftJoin(heart.member, member)
                .where(member.eq(member))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(heart.hno.desc())
                .fetchResults();

        List<ItemDTO> contents = itemDTOQueryResults.getResults();

        long total = itemDTOQueryResults.getTotal();

        return new PageImpl<>(contents, pageable, total);
    }
}
