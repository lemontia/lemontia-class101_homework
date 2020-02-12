package net.class101.product;

import net.class101.etc.MessageManager;
import net.class101.etc.SoldOutException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProductManagerTest {

    @Test
    @DisplayName("싱글톤 오프젝트 확인")
    public void checkSingletonTest() {
        //given

        //when
        ProductManager products = ProductManager.getInstance();
        ProductManager products2 = ProductManager.getInstance();

        //then
        System.out.println("singleton object1: " + products);
        System.out.println("singleton object1: " + products2);
        Assertions.assertEquals(products, products2);
    }

    @Test
    @DisplayName("상품목록 조회")
    public void showProductListTest() throws Exception{
        // given
        ProductManager products = ProductManager.getInstance();

        // when
        String str = products.productListString();
        System.out.println(str);

        // then
        Assertions.assertEquals(products.keySet().size() + 1, str.split("\\n").length);
    }

    @Test
    @DisplayName("없는상품을 조회할 때")
    void checkEmptyProductTest() {
        //given
        ProductManager productsIns1 = ProductManager.getInstance();

        //when
        NullPointerException exception = Assertions.assertThrows(NullPointerException.class, () ->
                productsIns1.get(1L)
        );

        //then
        System.out.println(exception.getMessage());
        Assertions.assertEquals(MessageManager.EMPTY_PRODUCT, exception.getMessage());
    }

    @Test
    @DisplayName("재고동기화 학인")
    void checkStockNumSyncTest() throws SoldOutException {
        // given
        ProductManager products1 = ProductManager.getInstance();
        ProductKit product1 = (ProductKit) products1.get(9236L);
        product1.sell(2);

        //when
        ProductManager products2 = ProductManager.getInstance();
        ProductKit product2 = (ProductKit) products2.get(9236L);

        //then
        Assertions.assertEquals(product1.getStockNumber(), product2.getStockNumber());
    }
    
    @Test
    @DisplayName("클래스 객체 조회")
    void getProductClassTest() {
        // given
        // 클래스
        Long productClassNo = 16374L;
        // 키트
        Long productKitNo = 9236L;
        ProductManager products1 = ProductManager.getInstance();
        

        ProductClass aClass = products1.getClass(productClassNo);
        Assertions.assertEquals(productClassNo, aClass.getProductNo());

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            products1.getClass(productKitNo);
        });
    }

    @Test
    @DisplayName("키트 객체(재고있는 상품) 조회")
    void getProductHasStockTest() {
        // given
        // 클래스
        Long productClassNo = 16374L;
        // 키트
        Long productKitNo = 9236L;
        ProductManager products1 = ProductManager.getInstance();


        ProductKit aClass = (ProductKit) products1.getStock(productKitNo);
        Assertions.assertEquals(productKitNo, aClass.getProductNo());

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            products1.getStock(productClassNo);
        });
    }
}