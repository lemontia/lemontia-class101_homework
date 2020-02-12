package net.class101.product.abs;

import lombok.Getter;

/**
 * 상품의 추상클래스
 */
@Getter
public abstract class Product {
    protected Long productNo;

    protected ProductType productType;

    protected String name;

    protected Integer price;
}
