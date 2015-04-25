package kpa.model;

/**
 * A group of cells, without validity condition.
 *
 */
public class Group extends AbstractGroup {

    @Override
    public boolean isValid() {
        return true;
    }

}
