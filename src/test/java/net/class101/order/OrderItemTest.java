package net.class101.order;

import net.class101.product.ProductClass;
import net.class101.product.abs.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderItemTest {

    @Test
    @DisplayName("오브젝트 생성 테스트")
    void createObjectTest() throws Exception{
        // given
        Product product = new ProductClass(123L, "테스트클래스", 100000);

        // when
        // 상품없음 에러
        NullPointerException exception = assertThrows(NullPointerException.class, () -> {
            new OrderItem(null, 10);
        });
        System.out.println("exception = " + exception);

        // 갯수없음 에러
        NullPointerException exception1 = assertThrows(NullPointerException.class, () -> {
            new OrderItem(product, null);
        });
        System.out.println("exception1 = " + exception1);
    }

    @Test
    @DisplayName("금액비교 에러")
    void checkAmountTest() throws Exception{
        // given
        Product product = new ProductClass(123L, "테스트클래스", 100000);

        // when
        OrderItem orderItem = new OrderItem(product, 2);

        // then
        System.out.println("주문금액: " + orderItem.amount());
        Assertions.assertEquals(200000, orderItem.amount());
    }

    @Test
    @DisplayName("주문추가")
    void addItemCount() throws Exception{
        // given
        Product product = new ProductClass(123L, "테스트클래스", 100000);

        // when & then (1)
        OrderItem orderItem = new OrderItem(product, 2);
        System.out.println("주문갯수: " + orderItem.getCount());
        Assertions.assertEquals(2, orderItem.getCount());

        // when & then (2)
        orderItem.addCount(3);
        System.out.println("주문갯수: " + orderItem.getCount());
        Assertions.assertEquals(5, orderItem.getCount());
    }
}