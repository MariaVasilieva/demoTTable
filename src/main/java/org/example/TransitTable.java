package org.example;
import java.util.ArrayList;
public class TransitTable extends FSM{
    private ArrayList<Transition> transitions;
    public TransitTable(String text) {
        super(text);
        transitions = new ArrayList<>();
    }

    @Override
    protected boolean scanner() {
        buildTransitionTable();
        Event event;
        int count=0;
        do {
            if(count<text.length()){
                event = recognizeEvent(text.charAt(count));
            }
            else {
                return true;
            }
            handleEvent(event);
            count++;
        }while(state!=State.ERROR && state!=State.SUCCESS);
        if(state==State.SUCCESS){
            return true;
        }
        else{
            return false;
        }
    }

    private void buildTransitionTable() {
        //Q0
        transitions.add(new Transition(State.Initial,State.Q1,Event.Minus));
        transitions.add(new Transition(State.Initial,State.Q2,Event.Zero));
        transitions.add(new Transition(State.Initial,State.Q3,Event.Digits1_9));
        transitions.add(new Transition(State.Initial,State.ERROR,Event.Dot));
        transitions.add(new Transition(State.Initial,State.ERROR,Event.Any));

        //Q1
        transitions.add(new Transition(State.Q1,State.Q2,Event.Zero));
        transitions.add(new Transition(State.Q1,State.Q3,Event.Digits1_9));
        transitions.add(new Transition(State.Q1,State.ERROR,Event.Minus));
        transitions.add(new Transition(State.Q1,State.ERROR,Event.Dot));
        transitions.add(new Transition(State.Q1,State.ERROR,Event.Any));

        //Q2
        transitions.add(new Transition(State.Q2,State.ERROR,Event.Zero));
        transitions.add(new Transition(State.Q2,State.ERROR,Event.Digits1_9));
        transitions.add(new Transition(State.Q2,State.ERROR,Event.Minus));
        transitions.add(new Transition(State.Q2,State.Q5,Event.Dot));
        transitions.add(new Transition(State.Q2,State.ERROR,Event.Any));

        //Q3
        transitions.add(new Transition(State.Q3,State.Q4,Event.Zero));
        transitions.add(new Transition(State.Q3,State.Q4,Event.Digits1_9));
        transitions.add(new Transition(State.Q3,State.ERROR,Event.Minus));
        transitions.add(new Transition(State.Q3,State.Q5,Event.Dot));
        transitions.add(new Transition(State.Q3,State.ERROR,Event.Any));

        //Q4
        transitions.add(new Transition(State.Q4,State.Q4,Event.Zero));
        transitions.add(new Transition(State.Q4,State.Q4,Event.Digits1_9));
        transitions.add(new Transition(State.Q4,State.ERROR,Event.Minus));
        transitions.add(new Transition(State.Q3,State.Q5,Event.Dot));
        transitions.add(new Transition(State.Q4,State.ERROR,Event.Any));

        //Q5
        transitions.add(new Transition(State.Q5,State.Q6,Event.Zero));
        transitions.add(new Transition(State.Q5,State.Q6,Event.Digits1_9));
        transitions.add(new Transition(State.Q5,State.ERROR,Event.Minus));
        transitions.add(new Transition(State.Q5,State.ERROR,Event.Dot));
        transitions.add(new Transition(State.Q5,State.ERROR,Event.Any));

        //Q6
        transitions.add(new Transition(State.Q6,State.Q6,Event.Zero));
        transitions.add(new Transition(State.Q6,State.Q6,Event.Digits1_9));
        transitions.add(new Transition(State.Q6,State.ERROR,Event.Minus));
        transitions.add(new Transition(State.Q6,State.ERROR,Event.Dot));
        transitions.add(new Transition(State.Q6,State.ERROR,Event.Any));
    }

    @Override
    protected Event recognizeEvent(char symbol) {
        switch (symbol){
            case '0' -> {
                return Event.Zero;
            }
            case '1', '2', '3','4','5','6','7','8','9'-> {
                return Event.Digits1_9;
            }
            case '-' ->{
                return Event.Minus;
            }
            case '.' ->{
                return Event.Dot;
            }
            default -> {
                return Event.Any;
            }
        }
    }

    @Override
    protected void handleEvent(Event event) {
        for (int i=0; i< transitions.size();i++){
            if(transitions.get(i).startState==state && transitions.get(i).trigger==event){
                state = transitions.get(i).endState;
                return;
            }
        }
        state = null;
    }
}
