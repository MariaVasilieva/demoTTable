package org.example;

public class Main {
    public static void main(String[] args) {
        FSM fsm = new TransitTable("-0.123");
        System.out.println(fsm.scanner());

        FSM fsm2 = new TransitTable("0%22%2.123");
        System.out.println(fsm2.scanner());
    }
}