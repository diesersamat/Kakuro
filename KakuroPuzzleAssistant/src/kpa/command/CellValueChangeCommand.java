/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kpa.command;

import kpa.model.KCell;

/**
 *
 * @author diesersamat
 */
public class CellValueChangeCommand extends Command {

    private int old_value;
    
    private int new_value;
    
    public CellValueChangeCommand(KCell receiver, int new_value) throws NullPointerException {
        super(receiver);
        this.new_value = new_value;
    }

    @Override
    public void execute() throws IllegalStateException {
        super.execute(); 
        old_value = receiver.getState();
        receiver.setState(new_value);
    }

    @Override
    public void revert() throws IllegalStateException {
        super.revert(); 
        receiver.setState(old_value);
    }
    
    
    
}
