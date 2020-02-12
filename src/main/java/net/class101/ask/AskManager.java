package net.class101.ask;

import net.class101.etc.MessageManager;
import net.class101.etc.SoldOutException;
import net.class101.order.Order;
import net.class101.product.ProductManager;

public class AskManager {

    ProductManager productManager;

    private Order order;
    Steps nowStep;
    Long productNo = 0L;

    public AskManager() {
        this.productManager = ProductManager.getInstance();
        nowStep = Steps.FIRST;
    }

    enum Steps {
        FIRST, INPUT_PRODUCT, INPUT_COUNT
    }

    public boolean askFlow(String input) {

        try {
            // 주문내역
            if (input.equals(" ") == true) {
                return order();
            }

            if (nowStep.equals(Steps.FIRST)) {
                if(input.equals("o") == true) {
                    orderStart();
                } else if (input.equals("q") == true){
                    System.out.println("고객님의 주문 감사합니다.");
                    return false;
                }
            } else if (nowStep.equals(Steps.INPUT_PRODUCT)) {
                orderProduct(input);
            } else if (nowStep.equals(Steps.INPUT_COUNT)) {
                orderProductCount(input);
            }
        } catch (SoldOutException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } catch (NullPointerException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            System.err.println("숫자를 입력해 주세요");
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        } catch (Throwable e){
            e.printStackTrace();
        }

        return true;
    }

    private boolean order() throws SoldOutException {
        if(order == null) {
            return true;
        }
        String orderList = order.showOrderList();
        System.out.println(orderList);
        if(orderList.equals(MessageManager.NO_ORDER_ITEMS)) {
            nowStep = Steps.FIRST;
            return true;
        }

        // 주문하기
        order.doSell();

        nowStep = Steps.FIRST;
        order = null;
        return true;
    }

    // 주문수량 입력
    private void orderProductCount(String input) throws SoldOutException {
        Integer count = Integer.valueOf(input);

        order.addHasStock(productNo, count);
        nowStep = Steps.INPUT_PRODUCT;
    }

    // 상품입력
    private void orderProduct(String input) {
        // 주문
        productNo = Long.valueOf(input);
        boolean nextStep = order.cart(Long.valueOf(productNo));
        if (nextStep == true) {
            nowStep = Steps.INPUT_COUNT;
        }
    }

    // 주문하기
    private void orderStart() {
        String productString = productManager.productListString();
        System.out.println(productString);

        order = new Order();
        nowStep = nowStep.INPUT_PRODUCT;
    }

    public void outputMessage() {
        if(nowStep.equals(Steps.FIRST)) {
            System.out.print("입력(o[net.class101.order]: 주문, q[quit]: 종료) : ");
        } else if (nowStep.equals(Steps.INPUT_PRODUCT)) {
            System.out.print("상품번호: ");
        } else if (nowStep.equals(Steps.INPUT_COUNT)) {
            System.out.print("수량: ");
        }
    }
}
