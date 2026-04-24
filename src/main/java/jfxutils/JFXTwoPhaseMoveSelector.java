package jfxutils;

import common.TwoPhaseMoveState;
import common.util.TwoPhaseMoveSelector;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;

/**
 * A subclass of {@link common.util.TwoPhaseMoveSelector} that provides a
 * property to observe the selection phase.
 *
 * @param <T> represents the moves that can be applied to the states
 */
public class JFXTwoPhaseMoveSelector<T> extends TwoPhaseMoveSelector<T> {

    private final ReadOnlyObjectWrapper<Phase> phaseProperty = new ReadOnlyObjectWrapper<>(phase);

    /**
     * Creates a {@code JFXTwoPhaseMoveSelector} object to determine the next
     * move in the state specified.
     *
     * @param state the state in which the next move is to be made
     */
    public JFXTwoPhaseMoveSelector(TwoPhaseMoveState<T, ?> state) {
        super(state);
    }

    /**
     * Represents the current selection phase.
     */
    public ReadOnlyObjectProperty<Phase> phaseProperty() {
        return phaseProperty.getReadOnlyProperty();
    }

    @Override
    protected void setPhase(Phase phase) {
        super.setPhase(phase);
        phaseProperty.set(phase);
    }

}
