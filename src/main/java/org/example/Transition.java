package org.example;

public class Transition {
    State startState;
    State endState;
    Event trigger;

    public Transition(State startState, State endState, Event trigger) {
        this.startState = startState;
        this.endState = endState;
        this.trigger = trigger;
    }
}
