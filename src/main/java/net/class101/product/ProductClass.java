package net.class101.product;

import lombok.Getter;
import lombok.NonNull;
import net.class101.product.abs.Product;
import net.class101.product.abs.ProductType;

/**
 * 상품-클래스
 * - 재고없음
 * - 1개만 주문가능
 * - 판매후 재고변화가 없음
 */
@Getter
public class ProductClass extends Product {

    public ProductClass(@NonNull Long productNo, @NonNull String name, @NonNull Integer price) {
        this.productNo = productNo;
        this.name = name;
        this.price = price;
        this.productType = ProductType.CLASS;
    }
}
