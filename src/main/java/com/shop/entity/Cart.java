package com.shop.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "cart")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Cart extends BaseEntity {
    @Id
    @Column(name = "cart_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)   //일대일 단방향
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne  //다대일 단방향
    @JoinColumn(name = "item_id")
    private Item item;

    private int count;
}
