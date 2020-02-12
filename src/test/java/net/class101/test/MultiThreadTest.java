package net.class101.test;

import net.class101.etc.SoldOutException;
import net.class101.order.Order;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class MultiThreadTest {
    /**
     * 멀티쓰레드, 재고수 체크
     */
    @Disabled   // 테스트 시 주석을 풀어주세요
    @Test
    @DisplayName("멀티쓰레드 재고수동기화 테스트")
    void multiThreadTest() throws SoldOutException, InterruptedException {
        // 주문생성
        Long hasStockProductNo = 9236L;
        Random random = new Random();
        CountDownLatch cdl = new CountDownLatch(20);


        new ThreadTest(cdl, makeOrder(hasStockProductNo, random.nextInt(10) + 1)).start();
        new ThreadTest(cdl, makeOrder(hasStockProductNo, random.nextInt(10) + 1)).start();
        new ThreadTest(cdl, makeOrder(hasStockProductNo, random.nextInt(10) + 1)).start();
        new ThreadTest(cdl, makeOrder(hasStockProductNo, random.nextInt(10) + 1)).start();
        new ThreadTest(cdl, makeOrder(hasStockProductNo, random.nextInt(10) + 1)).start();
        new ThreadTest(cdl, makeOrder(hasStockProductNo, random.nextInt(10) + 1)).start();
        new ThreadTest(cdl, makeOrder(hasStockProductNo, random.nextInt(10) + 1)).start();
        new ThreadTest(cdl, makeOrder(hasStockProductNo, random.nextInt(10) + 1)).start();
        new ThreadTest(cdl, makeOrder(hasStockProductNo, random.nextInt(10) + 1)).start();
        new ThreadTest(cdl, makeOrder(hasStockProductNo, random.nextInt(10) + 1)).start();
        new ThreadTest(cdl, makeOrder(hasStockProductNo, random.nextInt(10) + 1)).start();
        new ThreadTest(cdl, makeOrder(hasStockProductNo, random.nextInt(10) + 1)).start();
        new ThreadTest(cdl, makeOrder(hasStockProductNo, random.nextInt(10) + 1)).start();
        new ThreadTest(cdl, makeOrder(hasStockProductNo, random.nextInt(10) + 1)).start();
        new ThreadTest(cdl, makeOrder(hasStockProductNo, random.nextInt(10) + 1)).start();
        new ThreadTest(cdl, makeOrder(hasStockProductNo, random.nextInt(10) + 1)).start();
        new ThreadTest(cdl, makeOrder(hasStockProductNo, random.nextInt(10) + 1)).start();
        new ThreadTest(cdl, makeOrder(hasStockProductNo, random.nextInt(10) + 1)).start();
        new ThreadTest(cdl, makeOrder(hasStockProductNo, random.nextInt(10) + 1)).start();
        new ThreadTest(cdl, makeOrder(hasStockProductNo, random.nextInt(10) + 1)).start();


        cdl.await();

        System.out.println("================ 멀티쓰레드 종료");

        Assertions.assertTrue(true);
    }

    Order makeOrder(Long hasStockProductNo, int hasStockOrderCount) throws SoldOutException {
        Order order = new Order();
        Long noHasStockProductNo = 26825L;
        order.addClass(noHasStockProductNo);
        order.addHasStock(hasStockProductNo, hasStockOrderCount);

        return order;
    }
}
