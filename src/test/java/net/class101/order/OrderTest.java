package net.class101.order;

import net.class101.etc.MessageManager;
import net.class101.etc.SoldOutException;
import net.class101.product.ProductKit;
import net.class101.product.ProductManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class OrderTest {

    Order order;

    @BeforeEach
    void setup() {
        order = new Order();
    }

    @Test
    @DisplayName("주문추가-키트(재고있는 상품)")
    void addStockTest() throws Exception{
        // given
        Long productNo = 9236L;
        int count = 3;

        // when
        order.addHasStock(productNo, count);

        // then
        System.out.println("주문건수: " + order.orderCount());
        Assertions.assertTrue(order.orderCount() > 0);
    }

    @Test
    @DisplayName("주문추가-클래스")
    void addClassTest() throws Exception{
        // given
        Long productNo = 26825L;

        // when
        order.addClass(productNo);

        // then
        System.out.println("주문건수: " + order.orderCount());
        Assertions.assertTrue(order.orderCount() > 0);
    }

    @Test
    @DisplayName("[에러]주문추가-클래스중복")
    void duplClassTest() {
        // given
        Long productNo = 26825L;

        // when
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            order.addClass(productNo);
            order.addClass(productNo);
        });


        // then
        System.out.println(exception.getMessage());
        Assertions.assertEquals(MessageManager.DUPL_CLASS, exception.getMessage());
    }

    @Test
    @DisplayName("주문해내역 보기-배송비없이")
    void orderStringNoDeliveryTest() throws SoldOutException {
        // given
        Long noHasStockProductNo = 26825L;
        Long hasStockProductNo = 9236L;

        // when
        order.addClass(noHasStockProductNo);
        order.addHasStock(hasStockProductNo, 3);

        //then
        System.out.println(order.showOrderList());
        // 주문금액과 지불금액 같은지 비교
        Assertions.assertEquals(order.getTotalPrice(), order.getTotalAmount());
    }

    @Test
    @DisplayName("주문해내역 보기-배송비있게")
    void orderStringPlusDelivaryTest() throws SoldOutException {
        // given
        Long hasStockProductNo = 9236L;

        // when
        order.addHasStock(hasStockProductNo, 5);

        //then
        System.out.println(order.showOrderList());
        int totalAmount = order.getTotalPrice() + OrderConfigs.deliveryAmount;
        Assertions.assertEquals(totalAmount, order.getTotalAmount());
    }

    @Test
    @DisplayName("주문 후 재고가 줄어드는지 확인")
    void checkStockNumTest() throws Exception{
        // given
        // 주문생성
        Long hasStockProductNo = 9236L;
        int hasStockOrderCount = 3;
        Order order = makeOrder(hasStockProductNo, hasStockOrderCount);
        // 이떄까지의 주문갯수
        ProductManager productManager = ProductManager.getInstance();
        ProductKit hasStockProduct = (ProductKit) productManager.getStock(hasStockProductNo);
        int beforeStockNumber = hasStockProduct.getStockNumber();


        // when
        order.doSell();

        // then
        int afterStockNumber = hasStockProduct.getStockNumber();

        System.out.println("주문전 갯수: " + beforeStockNumber);
        System.out.println("주문후 갯수: " + afterStockNumber);

        Assertions.assertNotEquals(beforeStockNumber, afterStockNumber);
        Assertions.assertEquals(afterStockNumber, beforeStockNumber - hasStockOrderCount);
    }


    Order makeOrder(Long hasStockProductNo, int hasStockOrderCount) throws SoldOutException {
        Order order = new Order();
        Long noHasStockProductNo = 26825L;
        order.addClass(noHasStockProductNo);
        order.addHasStock(hasStockProductNo, hasStockOrderCount);

        return order;
    }


}