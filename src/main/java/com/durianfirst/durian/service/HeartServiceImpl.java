package com.durianfirst.durian.service;

import com.durianfirst.durian.dto.HeartDTO;
import com.durianfirst.durian.entity.Heart;
import com.durianfirst.durian.entity.Item;
import com.durianfirst.durian.entity.Member;
import com.durianfirst.durian.repository.HeartRepository;
import com.durianfirst.durian.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Log4j2
@RequiredArgsConstructor
public class HeartServiceImpl implements HeartService {

    private final HeartRepository heartRepository;
    private final ItemRepository itemRepository;

    public boolean addHeart(Member member, Long ino){
        Item item = itemRepository.findById(ino).orElseThrow();

        //중복 찜하기 방지
        if(isNotAlreadyHeart(member, item)){
            heartRepository.save(new Heart(item, member));
            return true;
        }
        return false;
    }

    public void cancelHeart(Member member,Long ino){
        Item item = itemRepository.findById(ino).orElseThrow();
        Heart heart = heartRepository.findByMemberAndItem(member, item).orElseThrow();
        heartRepository.delete(heart);
    }

    /*
     *   1. 좋아요를 count할 대상 recipe를 가져온다.
     *   2. 가져온 recipe로 like테이블에 쿼리한 결과를 List에 담는다.
     *   3. 현재 로그인한 사용자가
     *       이미 해당 레시피에 좋아요를 눌렀는지 검사하고 결과를 List에 담아 반환한다.
     * */
    public List<String> count(Long ino, Member loginMember){
        Item item = itemRepository.findById(ino).orElseThrow();
        Integer itemHeartCount = heartRepository.countByItem(item).orElse(0);

        List<String> resultData = new ArrayList<>(Arrays.asList(String.valueOf(itemHeartCount)));

        if(Objects.nonNull(loginMember)){
            resultData.add(String.valueOf(isNotAlreadyHeart(loginMember, item)));
            return resultData;
        }
        return resultData;
    }

    //사용자가 이미 좋아요 한 게시물인지 체크
    private boolean isNotAlreadyHeart(Member member, Item item){
        return heartRepository.findByMemberAndItem(member, item).isEmpty();
    }

    /*@Override
    public Long register(HeartDTO heartDTO) {

        log.info("찜 등록");
        log.info(heartDTO);

        Heart entity = dtoToEntity(heartDTO);

        log.info(entity);

        heartRepository.save(entity);

        return entity.getHno();

    }*/

    /*@Override
    public HeartDTO read(Long hno) {

        Optional<Heart> result = heartRepository.findById(hno);

        return result.isPresent()? entityToDto(result.get()): null;
    }

    @Override
    public void remove(Long hno) {
        heartRepository.deleteById(hno);
    }*/



}
