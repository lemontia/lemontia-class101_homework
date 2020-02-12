package net.class101.product;

import lombok.NonNull;
import net.class101.etc.MessageManager;
import net.class101.etc.SoldOutException;
import net.class101.product.abs.Product;
import net.class101.product.abs.ProductStock;
import net.class101.product.abs.ProductType;

/**
 * 상품-클래스
 * - 재고있음
 * - 여러개 주문가능
 * - 판매후 재고 감소
 */
public class ProductKit extends Product implements ProductStock {
    private Integer stockNumber;

    public ProductKit(@NonNull Long productNo, @NonNull String name, @NonNull Integer price, @NonNull Integer stockNumber) {
        this.productNo = productNo;
        this.name = name;
        this.price = price;
        this.stockNumber = stockNumber;
        this.productType = ProductType.KIT;
    }

    // 주문시
    public void order(int count) throws SoldOutException {
        check(count);
    }

    private void check(int count) throws SoldOutException {
        if (this.stockNumber == 0) {
            throw new SoldOutException(MessageManager.SOLD_OUT);
        }

        if (this.stockNumber < count) {
            throw new SoldOutException(MessageManager.LACK_PRODUCT + "(" + (count - this.stockNumber) + ")");
        }
    }

    // 판매시
    public void sell(int count) throws SoldOutException {
        synchronized (this) {
            check(count);

            this.stockNumber = this.stockNumber - count;
        }
    }

    @Override
    public Integer getStockNumber() {
        return this.stockNumber;
    }
}
