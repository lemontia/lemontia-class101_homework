package net.class101;

import net.class101.ask.AskManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * 주문테스트
 */
class ApplicationTest {

    /**
     * 주문순서
     * - [키트] 5개
     * - 주문완료
     */
    @Test
    @DisplayName("정상주문 테스트-주문 pdf 2번(5만 원 미만 택배비포함)")
    void testOrder2Test() {

        //given
        AskManager askManager2 = new AskManager();

        //when
        // 시작
        askManager2.outputMessage();
        askManager2.askFlow("o");

        // 키트 주문
        orderKit(askManager2, "9236", "3");

        // 주문완료
        askManager2.outputMessage();
        askManager2.askFlow(" ");

        // 종료
        askManager2.outputMessage();
        System.out.println("q");
        askManager2.askFlow("q");

        //then
        Assertions.assertTrue(true);
    }

    /**
     * 주문순서
     * - [키트] 1개
     * - [클래스] 1개
     * - 주문완료
     */
    @Test
    @DisplayName("정상주문 테스트-주문 pdf 1번")
    void testOrder1Test() {
        //given
        AskManager askManager2 = new AskManager();

        //when
        // 시작
        askManager2.outputMessage();
        askManager2.askFlow("o");

        orderKit(askManager2, "97166", "1");
        orderClass(askManager2, "65625");

        askManager2.outputMessage();
        System.out.println(" ");
        askManager2.askFlow(" ");

        // 종료
        askManager2.outputMessage();
        System.out.println("q");
        askManager2.askFlow("q");
    }


    @Test
    @DisplayName("주문(키트) => 같은상품 주문(+수량) => 상품 1개에 갯수추가")
    void orderNumberCountAddTest() {
        //given
        AskManager askManager = new AskManager();

        askManager.outputMessage();
        askManager.askFlow("o");

        orderKit(askManager, "9236", "1");
        orderKit(askManager, "9236", "2");

        askManager.outputMessage();
        System.out.println(" ");
        askManager.askFlow(" ");

        // 종료
        askManager.outputMessage();
        System.out.println("q");
        askManager.askFlow("q");
    }

    @Test
    @DisplayName("주문 => 재고 0 => 주문완료 => 같은상품 주문 => 재고없음 안내문")
    void orderNumberCount0Test() {
        //given
        AskManager askManager = new AskManager();

        askManager.outputMessage();
        askManager.askFlow("o");


        orderKit(askManager, "58395", "10");
        orderKit(askManager, "58395", "21");

        askManager.outputMessage();
        System.out.println(" ");
        askManager.askFlow(" ");

        askManager.outputMessage();
        askManager.askFlow("o");

        askManager.outputMessage();
        System.out.println("58395");
        askManager.askFlow("58395");

        askManager.outputMessage();
        System.out.println(" ");
        askManager.askFlow(" ");

        // 종료
        askManager.outputMessage();
        System.out.println("q");
        askManager.askFlow("q");
    }

    /**
     * 재고상품을 전부 주문해 잔고가 0인 경우
     */
    @Test
    @DisplayName("같은상품을 잔고보다 많이 주문해 이후 주문에 재고없음 안내")
    void orderNumberLackTest() {
        //given
        AskManager askManager = new AskManager();

        askManager.outputMessage();
        askManager.askFlow("o");


        orderKit(askManager, "39712", "5");
        orderKit(askManager, "39712", "5");
        askManager.outputMessage();
        System.out.println("3");
        askManager.askFlow("3");

        askManager.outputMessage();
        System.out.println(" ");
        askManager.askFlow(" ");

        // 종료
        askManager.outputMessage();
        System.out.println("q");
        askManager.askFlow("q");
    }





    private void orderClass(AskManager askManager, String productNo) {
        askManager.outputMessage();
        System.out.println(productNo);
        askManager.askFlow(productNo);
    }

    private void orderKit(AskManager askManager, String productNo, String count) {
        // 상품
        askManager.outputMessage();
        System.out.println(productNo);
        askManager.askFlow(productNo);
        // 수량
        askManager.outputMessage();
        System.out.println(count);
        askManager.askFlow(count);
    }
}