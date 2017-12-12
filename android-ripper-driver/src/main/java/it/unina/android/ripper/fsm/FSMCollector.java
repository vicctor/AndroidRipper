package it.unina.android.ripper.fsm;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.unina.android.ripper.output.model.fsm.FSMTransition;
import it.unina.android.ripper.output.model.fsm.FsmModel;
import it.unina.android.shared.ripper.model.state.ActivityDescription;
import it.unina.android.shared.ripper.model.transition.Event;
import it.unina.android.shared.ripper.model.transition.IEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author Artur
 */
public class FSMCollector {

    private static final Logger LOG = Logger.getLogger(FSMCollector.class.getCanonicalName());
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private Map<String, ActivityDescription> states = new HashMap<>();

    // Map<sourceState.id, Map<Event, nextState.id>>
    private Map<String, Map<Event, String>> transitions = new HashMap<>();

    private File dumpFile;

    public FSMCollector(final File dumpFile) {
        this.dumpFile = dumpFile;
    }

    private void addStateIfMissing(ActivityDescription state) {
        if (states.containsKey(state.getId()) == false) {
            states.put(state.getId(), state);
        }
    }

    public void updateState(ActivityDescription previousState, IEvent event, ActivityDescription newState) {
        if (previousState != null && event != null && newState != null) {
            Event eventInstance = (Event) event;
            LOG.log(Level.INFO, "Transition from state #{0} to state #{1} on event: {2} (widget={3})", new Object[]{previousState.getUid(), newState.getUid(), event.toString(), eventInstance.getWidget().getName()});

            if (transitions.containsKey(previousState.getUid()) == false) {
                transitions.put(previousState.getUid(), new HashMap<>());
            }
            Map<Event, String> transition = transitions.get(previousState.getUid());
            transition.put((Event) event, newState.getUid());

            addStateIfMissing(newState);
            addStateIfMissing(previousState);
        }
    }

    public void dumpFSM() {
        FsmModel model = new FsmModel();

        model.setStates(states.values());

        List<FSMTransition> transitionsList = transitions
                .keySet()
                .stream()
                .flatMap(sourceStateId
                        -> transitions.get(sourceStateId)
                        .keySet()
                        .stream()
                        .map(event -> new FSMTransition(sourceStateId, event, transitions.get(sourceStateId).get(event)))
                )
                .collect(Collectors.toList());

        model.setTransitions(transitionsList);

        try (OutputStream outputStream = new FileOutputStream(dumpFile)) {
            try (Writer writer = new OutputStreamWriter(outputStream)) {
                gson.toJson(model, writer);
            }
        } catch (IOException ex) {
            Logger.getLogger(FSMCollector.class.getName()).log(Level.SEVERE, "Error while dumping FSM model", ex);
        }
    }
}
