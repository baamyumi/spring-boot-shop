package com.shop.repository;

import com.shop.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long>, //Item 클래스의 기본키 타입 Long
        QuerydslPredicateExecutor<Item>, ItemRepositoryCustom {

    List<Item> findByItemNm(String itemNm);

    List<Item> findByItemNmOrItemDetail(String itemNm, String itemDetail); //Or 조건 처리하기

    List<Item> findByPriceLessThan(Integer price);  //LessThan 조건 처리하기

    List<Item> findByPriceLessThanOrderByPriceDesc(Integer price);

    //JPQL 사용
    @Query("select i from Item i where i.itemDetail like %:itemDetail% order by i.price desc")
    List<Item> findByItemDetail(@Param("itemDetail") String itemDetail);

    //JPQL 사용 - native(기존 데이터베이스에서 사용하는 쿼리를 그대롤 사용할때 -> 특정 데이터베이스에 종속되기때문에 독립성을 잃게됨.)
    @Query(value="select * from item i where i.item_detail like %:itemDetail% order by i.price desc", nativeQuery = true)
    List<Item> findByItemDetailByNative(@Param("itemDetail") String itemDetail);
}
