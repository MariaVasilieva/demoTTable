package org.example;

public abstract class FSM {
    protected String text;
    protected State state;
    public FSM(String text){
        this.text = text;
        state = State.Initial;
    }
    protected abstract boolean scanner();
    protected abstract Event recognizeEvent(char symbol);
    protected abstract void handleEvent(Event event);
}
