package hr.algebra.view.model;

import hr.algebra.model.Actor;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class ActorTableModel extends AbstractTableModel {

    private static final String[] COLUMNS = {
            "Id", "Name"
    };

    private List<Actor> actors;

    public ActorTableModel(List<Actor> actors) {
        this.actors = actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
        fireTableDataChanged();
    }

    public Actor getActorAt(int rowIndex) {
        return actors.get(rowIndex);
    }

    @Override
    public int getRowCount() {
        return actors == null ? 0 : actors.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMNS.length;
    }

    @Override
    public String getColumnName(int column) {
        return COLUMNS[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Actor actor = actors.get(rowIndex);

        return switch (columnIndex) {
            case 0 -> actor.getId();
            case 1 -> actor.getName();
            default -> "";
        };
    }
}
