package net.class101.product.abs;

import net.class101.etc.SoldOutException;

/**
 * 재고수가 있는 상품
 */
public interface ProductStock {

    void order(int count) throws SoldOutException;

    void sell(int count) throws SoldOutException;

    Integer getStockNumber();
}
