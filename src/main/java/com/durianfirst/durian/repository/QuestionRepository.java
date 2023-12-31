package com.durianfirst.durian.repository;

import com.durianfirst.durian.entity.Question;
import com.durianfirst.durian.repository.search.QuestionSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question,Long>, QuestionSearch {

    /* 조회수 */
    @Modifying
    @Query("update Question q set q.view = q.view + 1 where q.qno = :qno")
    int updateView(Long qno);

    Page<Question> findAll(Pageable pageable);

    Question findByQtitleAndQcontent(String qtitle, String qcontent);

    @Query(value = "select now()", nativeQuery = true)
    String getTime();

    Question findByQtitle(String qtitle);//제목으로 테이블조회

    List<Question> findByQtitleLike(String qtitle);//제목에 특정문자열포함 데이터조회


}
