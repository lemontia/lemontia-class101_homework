package net.class101.test;

import net.class101.etc.SoldOutException;
import net.class101.order.Order;
import net.class101.product.ProductManager;

import java.util.concurrent.CountDownLatch;

public class ThreadTest extends Thread{


    private CountDownLatch cdl;

    private Order order;

    public ThreadTest(CountDownLatch cdl, Order order) {
        this.cdl = cdl;
        this.order = order;
    }

    @Override
    public void run() {
        try {
            order.doSell();
        } catch (SoldOutException e) {
            e.printStackTrace();
        } finally {
            cdl.countDown();
        }
    }
}
