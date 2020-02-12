package net.class101;

import net.class101.ask.AskManager;

import java.util.Scanner;

public class Application {

    public static void main(String[] args) {

        System.out.println("상품주문 시스템을 시작합니다");
        System.out.println("-----------------------------------");

        AskManager askManager2 = new AskManager();
        Scanner sc=new Scanner(System.in);
        while (true) {
            askManager2.outputMessage();

            String input = sc.nextLine();

            boolean isLive = askManager2.askFlow(input);
            if(isLive == false) {
                break;
            }
        }
    }
}
