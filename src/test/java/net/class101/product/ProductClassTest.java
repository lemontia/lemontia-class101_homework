package net.class101.product;

import net.class101.product.abs.ProductType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductClassTest {

    @Test
    @DisplayName("상품 오브젝트 생성 테스트")
    void createObjectTest() {
        // given
        Long productNo = 1234L;
        String name = "테스트강의";
        Integer price = 300000;

        // when
        ProductClass productClass = new ProductClass(productNo, name, price);

        // then
        Assertions.assertEquals(name, productClass.getName());
        Assertions.assertEquals(ProductType.CLASS, productClass.getProductType());
    }

    @Test
    @DisplayName("상품 오브젝트 생성시 not-null 체크 ")
    void createObjectNotNullTest() {
        String name = "테스트강의";
        Integer price = 300000;

        Assertions.assertThrows(NullPointerException.class, () -> {
           new ProductClass(null, name, price);
        });
    }
}