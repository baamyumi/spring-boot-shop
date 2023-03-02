package com.shop.entity;

import com.shop.dto.CartItemDto;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="cart_item")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class CartItem extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "cart_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    private int count;

    public void addCount(int count){
        this.count += count;
    }

    public void updateCount(int count){
        this.count = count;
    }
}
