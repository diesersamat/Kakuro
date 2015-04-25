package kpa.command; 

import java.util.Stack;

/**
 * Facilities for an undo-redo mechanism, on the basis of commands.
 *
 */
// -----8<----- cut line -----8<-----
public class UndoRedo {

//# BEGIN TODO Representation in terms of instance variables, incl. rep. inv.
    private final Stack<Command> stackCommandsDone = new Stack<>();
    private final Stack<Command> stackCommandsUndone = new Stack<>();
//# END TODO

    /**
     * Returns whether an {@code undo} is possible.
     *
     * @return whether {@code undo} is possible
     */
    public boolean canUndo() {
//# BEGIN TODO Implementation of canUndo
        return !stackCommandsDone.isEmpty();
//# END TODO
    }

    /**
     * Returns whether a {@code redo} is possible.
     *
     * @return {@code redo().pre}
     */
    public boolean canRedo() {
//# BEGIN TODO Implementation of canRedo
        return !stackCommandsUndone.isEmpty();
//# END TODO
    }

    /**
     * Returns command most recently done.
     *
     * @return command at top of undo stack
     * @throw IllegalStateException if precondition failed
     * @pre {@code canUndo()}
     */
    public Command lastDone() throws IllegalStateException {
//# BEGIN TODO Implementation of lastDone
// Replace this line
        if (canUndo()) {
            return stackCommandsDone.peek();
        } else {
            throw new IllegalStateException("Precondition violated!");
        }
//# END TODO
    }

    /**
     * Returns command most recently undone.
     *
     * @return command at top of undo stack
     * @throw IllegalStateException if precondition failed
     * @pre {@code canRedo()}
     */
    public Command lastUndone() throws IllegalStateException {
//# BEGIN TODO Implementation of lastUndone
        if (canRedo()) {
            return stackCommandsUndone.peek();
        } else {
            throw new IllegalStateException("Precondition violated!");
        }
//# END TODO
    }

    /**
     * Clears all undo-redo history.
     */
    public void clear() {
//# BEGIN TODO Implementation of clear
        stackCommandsDone.clear();
        stackCommandsUndone.clear();
//# END TODO
    }

    /**
     * Adds given command to the do-history. If the command was not yet
     * executed, it is first executed.
     *
     * @param command the command to incorporate
     */
    public void did(final Command command) {
//# BEGIN TODO Implementation of did
        if (!command.isExecuted()) {
            command.execute();
        }
        stackCommandsUndone.clear();
        stackCommandsDone.add(command);
//# END TODO
    }

    /**
     * Undo the most recently done command, optionally allowing it to be redone.
     *
     * @param redoable whether to allow redo
     * @throws IllegalStateException if precondition violated
     * @pre {@code canUndo()}
     */
    public void undo(final boolean redoable) throws IllegalStateException {
//# BEGIN TODO Implementation of undo
        if (canUndo()) {
            Command toUndo = stackCommandsDone.pop();
            toUndo.revert();
            if (redoable) {
                stackCommandsUndone.add(toUndo);
            }
        } else {
            throw new IllegalStateException("Precondition violated!");
        }
//# END TODO
    }

    /**
     * Redo the most recently undone command.
     *
     * @throws IllegalStateException if precondition violated
     * @pre {@code canRedo()}
     */
    public void redo() throws IllegalStateException {
//# BEGIN TODO Implementation of redo
        if (canRedo()) {
            Command toRedo = stackCommandsUndone.pop();
            toRedo.execute();
            stackCommandsDone.add(toRedo);
        } else {
            throw new IllegalStateException("Precondition violated!");
        }
//# END TODO
    }

    /**
     * Undo all done commands.
     *
     * @param redoable whether to allow redo
     */
    public void undoAll(final boolean redoable) {
//# BEGIN TODO Implementation of undoAll
        int limit = stackCommandsDone.size();
        for (int i = 0; i < limit; i++) {
            undo(redoable);
        }
//# END TODO
    }

    /**
     * Redo all undone commands.
     */
    public void redoAll() {
//# BEGIN TODO Implementation of redoAll
        int limit = stackCommandsUndone.size();
        for (int i = 0; i < limit; i++) {
            redo();
        }
//# END TODO
    }

}
