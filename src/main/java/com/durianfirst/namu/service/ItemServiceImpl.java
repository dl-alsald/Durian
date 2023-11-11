package com.durianfirst.namu.service;

import com.durianfirst.namu.dto.ItemDTO;
import com.durianfirst.namu.dto.PageRequestDTO;
import com.durianfirst.namu.dto.PageResultDTO;
import com.durianfirst.namu.entity.Item;
import com.durianfirst.namu.entity.ItemImg;
import com.durianfirst.namu.entity.QItem;
import com.durianfirst.namu.repository.ItemImgRepository;
import com.durianfirst.namu.repository.ItemRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor //의존성 주입
public class ItemServiceImpl implements ItemService{

    private final ItemRepository itemRepository;

    private final ItemImgRepository itemImgRepository;

    @Override
    public Long register(ItemDTO itemDTO){

        Map<String, Object> entityMap = dtoToEntity(itemDTO);
        Item item = (Item) entityMap.get("item");
        List<ItemImg> itemImgList = (List<ItemImg>) entityMap.get("imgList");

        itemRepository.save(item);

        itemImgList.forEach(itemImg -> {
            itemImgRepository.save(itemImg);
        });

        return item.getIno();
    }

    @Override
    public ItemDTO read(Long ino) {

        Optional<Item> result = itemRepository.findById(ino);

        return result.isPresent()? entityToDTO(result.get()): null;
    }

    @Override
    public void remove(Long ino) {

        itemRepository.deleteById(ino);
    }

    @Override
    public void modify(ItemDTO itemDTO) {

        Optional<Item> result = itemRepository.findById(itemDTO.getIno());

        if(result.isPresent()){

            Item item = result.get();

            item.changeIname(itemDTO.getIname());
            item.changeIprice(itemDTO.getIprice());
            item.changeIdealway(itemDTO.getIdealway());
            item.changeIlocateion(itemDTO.getIlocation());
            item.changeIcate(itemDTO.getIcate());
            item.changeIcondition(itemDTO.getIcondition());
            item.changeIdesc(itemDTO.getIdesc());
            item.changeIstatus(itemDTO.getIstatus());

            itemRepository.save(item);


        }
    }

    @Override
    public PageResultDTO<ItemDTO, Item> getList(PageRequestDTO requestDTO){

        Pageable pageable = requestDTO.getPageable(Sort.by("ino").descending());

        BooleanBuilder booleanBuilder = getSearch(requestDTO);

        Page<Item> result = itemRepository.findAll(booleanBuilder, pageable);

        Function<Item, ItemDTO> fn = (entity -> entityToDTO(entity));

        return new PageResultDTO<>(result, fn);
    }

    private BooleanBuilder getSearch(PageRequestDTO requestDTO) {

        String type = requestDTO.getType();

        BooleanBuilder booleanBuilder = new BooleanBuilder();

        QItem qItem = QItem.item;

        String keyword = requestDTO.getKeyword();

        BooleanExpression expression = qItem.ino.gt(0L);

        booleanBuilder.and(expression);

        if (type == null || type.trim().length() == 0) {
            return booleanBuilder;
        }

        BooleanBuilder conditionBuilder = new BooleanBuilder();

        if (type.contains("n")) {
            conditionBuilder.or(qItem.iname.contains(keyword));
        }
        if (type.contains("d")) {
            conditionBuilder.or(qItem.idealway.contains(keyword));
        }
        if (type.contains("l")) {
            conditionBuilder.or(qItem.ilocation.contains(keyword));
        }
        if (type.contains("c")) {
            conditionBuilder.or(qItem.icate.contains(keyword));
        }

        booleanBuilder.and(conditionBuilder);

        return booleanBuilder;
    }



}
