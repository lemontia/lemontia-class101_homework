package net.class101.order;

import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import net.class101.product.abs.Product;

@ToString
@Getter
public class OrderItem {
    private Product product;
    private Integer count;

    public OrderItem(@NonNull Product product, @NonNull Integer count) {
        this.product = product;
        this.count = count;
    }

    public int amount() {
        return this.product.getPrice() * count;
    }

    public void addCount(Integer addCount) {
        this.count = count + addCount;
    }
}
