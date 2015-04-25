package kpa.gui;

import java.util.EventListener;

/**
 * Intefrace to connect KakuroSolver and GUI
 *
 */

public interface RefreshListener extends EventListener {
    void refreshGUI(); //to refresh GUI, when one piece of action
        //completed
        boolean isCanceled(); //to get isCancelled flag

    void setCanceled(); //to set isCancelled flag to {@code true}
    }
