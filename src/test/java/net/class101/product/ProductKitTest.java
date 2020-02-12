package net.class101.product;

import net.class101.etc.SoldOutException;
import net.class101.product.abs.ProductType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductKitTest {


    @Test
    @DisplayName("키트 오브젝트 생성 테스트")
    void createObjectKitTest() {
        // given
        Long productNo = 1234L;
        String name = "임의 키트";
        Integer price = 3000;
        Integer stockNumber = 10;

        // when
        ProductKit productKit = new ProductKit(productNo, name, price, stockNumber);

        // then
        Assertions.assertEquals(name, productKit.getName());
        Assertions.assertEquals(ProductType.KIT, productKit.getProductType());

        // 재고수 확인
        Assertions.assertEquals(10, productKit.getStockNumber());
    }

    @Test
    @DisplayName("키트 오브젝트 생성시 not-null 체크 ")
    void createObjectNotNullTest() {
        Long productNo = 1234L;
        Integer price = 3000;
        Integer stockNumber = 10;

        Assertions.assertThrows(NullPointerException.class, () -> {
            new ProductKit(productNo, null, price, stockNumber);
        });
    }


    @Test
    @DisplayName("주문 갯수 테스트")
    void orderKitTest() throws SoldOutException {
        // given
        Long productNo = 1234L;
        String name = "임의 키트";
        Integer price = 3000;
        Integer stockNumber = 10;

        // when
        ProductKit productKit = new ProductKit(productNo, name, price, stockNumber);


        //then
        // 재고수보다 많을 때
        Assertions.assertThrows(SoldOutException.class, () -> {
            productKit.order(11);
        });

    }


    @Test
    @DisplayName("판매 후 재고수량 0개일 때 SoldOut")
    void sellAfterSoldOutTest() throws SoldOutException {
        // given
        Long productNo = 1234L;
        String name = "임의 키트";
        Integer price = 3000;
        Integer stockNumber = 10;

        // when
        ProductKit productKit = new ProductKit(productNo, name, price, stockNumber);

        // then
        // 판매 후
        productKit.sell(10);

        // 잔고개 0개일때
        Assertions.assertThrows(SoldOutException.class, () -> {
            productKit.order(3);
        });
    }

    @Test
    @DisplayName("재고수량보다 더 많이 팔 때")
    void overSellTest() {
        // given
        Long productNo = 1234L;
        String name = "임의 키트";
        Integer price = 3000;
        Integer stockNumber = 10;

        // when
        ProductKit productKit = new ProductKit(productNo, name, price, stockNumber);

        // then
        Assertions.assertThrows(SoldOutException.class, () -> {
            productKit.sell(11);
        });
    }
}