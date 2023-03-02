package com.shop.entity;

import com.shop.constant.ItemSellStatus;
import com.shop.dto.ItemFormDto;
import com.shop.exception.OutOfStockException;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="item")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
 public class Item extends BaseEntity {

    @Id
    @Column(name="item_id") //item 테이블의 item_id와 매핑 되도록 설정
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;    //상품코드

    @Column(nullable = false, length = 50) //String 기본길이 255
    private String itemNm;  //상품명

    @Column(name="price", nullable = false)
    private int price;

    @Column(nullable = false)
    private int stockNumber;    //재고수량

    @Column(nullable = false)
    private String itemDetail;  //상품 상세 설명

    @Enumerated(EnumType.STRING)
    private ItemSellStatus itemSellStatus; //상품 판매 상태

    public void updateItem(ItemFormDto itemFormDto){
       this.itemNm = itemFormDto.getItemNm();
       this.price = itemFormDto.getPrice();
       this.stockNumber = itemFormDto.getStockNumber();
       this.itemDetail = itemFormDto.getItemDetail();
       this.itemSellStatus = itemFormDto.getItemSellStatus();
    }

    public void removeStock(int stockNumber){
       int restStock = this.stockNumber - stockNumber;
       if(restStock<0){
          throw new OutOfStockException("상품의 재고가 부족합니다.(현재 재고 수량: )" + this.stockNumber + ")");
       }
       this.stockNumber = restStock;
    }

    public void addStock(int stockNumber){
       this.stockNumber += stockNumber;
    }
}
