package net.class101.order;

import net.class101.etc.MessageManager;
import net.class101.etc.SoldOutException;
import net.class101.product.ProductClass;
import net.class101.product.ProductManager;
import net.class101.product.abs.Product;
import net.class101.product.abs.ProductStock;
import net.class101.product.abs.ProductType;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import static net.class101.etc.MessageManager.AMOUNT_ERROR;

public class Order {

    private List<OrderItem> orderItems;

    private int totalPrice = 0;

    ProductManager productManager;

    public Order() {
        productManager = ProductManager.getInstance();
        orderItems = new ArrayList<>();
    }

    public int orderCount() {
        return orderItems.size();
    }

    public void addClass(Long productNo) {
        ProductClass product = productManager.getClass(productNo);

        // 중복되는 것 확인
        for (OrderItem orderItem : orderItems) {
            Product itemProduct = orderItem.getProduct();
            if(product.getProductNo().compareTo(itemProduct.getProductNo()) == 0) {
                throw new IllegalArgumentException(MessageManager.DUPL_CLASS);
            }
        }
        orderItems.add(new OrderItem(product, 1));

        System.out.println("==== 추가: " + product.getName() + " - 1");
    }


    public void addHasStock(Long productNo, int count) throws SoldOutException {
        // 이미 있는 상품인지 체크
        ProductStock productStock = null;
        for (OrderItem orderItem : orderItems) {
            if(orderItem.getProduct().getProductNo().compareTo(productNo) == 0) {
                productStock = (ProductStock) orderItem.getProduct();
                int sumCount = orderItem.getCount() + count;
                productStock.order(sumCount);

                Integer beforeCount = orderItem.getCount();
                orderItem.addCount(count);
                System.out.println("==== 수량변경: " + ((Product) productStock).getName() + " - " + beforeCount + " => " + sumCount);
                return;
            }
        }
        if(productStock == null) {
            productStock = productManager.getStock(productNo);
        }


        productStock.order(count);
        orderItems.add(new OrderItem((Product) productStock, count));

        System.out.println("==== 추가: " + ((Product) productStock).getName() + " - " + count);
    }


    public String showOrderList() {
        if(orderItems.size() == 0) {
            return MessageManager.NO_ORDER_ITEMS;
        }

        StringBuffer sb = new StringBuffer();
        sb.append("주문내역\n");
        sb.append("--------------------------------------\n");

        for (OrderItem orderItem : orderItems) {
            Product product = orderItem.getProduct();
            sb.append(product.getName() + " - ");
            sb.append(orderItem.getCount() + "개\n");

            addTotalPrice(orderItem.amount());
        }
        sb.append("--------------------------------------\n");
        sb.append("주문금액: ");
        sb.append(NumberFormat.getInstance().format(this.totalPrice) + "\n");
        sb.append("--------------------------------------\n");
        sb.append("지불금액: ");
        sb.append(NumberFormat.getInstance().format(getTotalAmount()) + "\n");
        sb.append("--------------------------------------\n");


        return sb.toString();
    }


    /**
     * 주문금액 합
     */
    private void addTotalPrice(int amount) {
        if(amount < 0) {
            throw new IllegalArgumentException(AMOUNT_ERROR);
        }
        this.totalPrice = this.totalPrice + amount;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    /**
     * 결제금액 계산
     */
    public int getTotalAmount() {
        if(totalPrice < OrderConfigs.deliveryThreshold) {
            return totalPrice + OrderConfigs.deliveryAmount;
        }
        return totalPrice;
    }


    /**
     * 장바구니 담기
     */
    public boolean cart(Long productNo) {
        Product product = productManager.get(productNo);

        if(product.getProductType().equals(ProductType.CLASS) == true) {
            addClass(productNo);
            return false;
        } else {
            if(product instanceof ProductStock) {
                if(((ProductStock) product).getStockNumber() == 0) {
                    System.out.println("재고가 없는 상품입니다.");
                    return false;
                }
            }

            return true;
        }
    }

    /**
     * 주문하기
     */
    public void doSell() throws SoldOutException {
        List<OrderItem> orderItems = this.orderItems;

        for (OrderItem orderItem : orderItems) {
            if(orderItem.getProduct() instanceof ProductStock) {
                // 재고수 차감
                ProductStock productStock = (ProductStock) orderItem.getProduct();
                productStock.sell(orderItem.getCount());
            }
        }
    }
}
