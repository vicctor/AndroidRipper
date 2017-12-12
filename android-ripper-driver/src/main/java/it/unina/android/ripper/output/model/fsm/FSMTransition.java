package it.unina.android.ripper.output.model.fsm;

import it.unina.android.shared.ripper.model.transition.Event;

/**
 *
 * @author Artur
 */
public class FSMTransition {
    private String sourceStateId;
    private Event event;
    private String destinationStateId;

    public FSMTransition(String sourceStateId, Event event, String sourcedestinationStateId) {
        this.sourceStateId = sourceStateId;
        this.event = event;
        this.destinationStateId = sourcedestinationStateId;
    }

    public FSMTransition() {
    }

    public String getSourceStateId() {
        return sourceStateId;
    }

    public void setSourceStateId(String sourceStateId) {
        this.sourceStateId = sourceStateId;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public String getDestinationStateId() {
        return destinationStateId;
    }

    public void setDestinationStateId(String destinationStateId) {
        this.destinationStateId = destinationStateId;
    }    
}
