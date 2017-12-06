/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esi.atlg3.g43320.othello.view.fx;

import esi.atlg3.g43320.othello.model.OthelloModel;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * This class represents the table of all the moves of one game.
 *
 * @author s_u_y_s_a
 */
public class GUIMovesHistory {

    private TableView history;
    ObservableList<Move> data;

    /**
     * Creates an instance of GUIMovesHistory.
     */
    public GUIMovesHistory() {
        history = new TableView();
        TableColumn<Move, Number> idCol = new TableColumn<>("ID");
        idCol.setPrefWidth(30);
        idCol.setSortable(false);
        idCol.setCellValueFactory(column -> new ReadOnlyObjectWrapper<>(history.getItems().indexOf(column.getValue())));
        TableColumn nameCol = new TableColumn("Name");
        TableColumn actionCol = new TableColumn("Action");
        actionCol.setPrefWidth(120);
        TableColumn positionCol = new TableColumn("Position");
        positionCol.setPrefWidth(100);
        TableColumn takenCol = new TableColumn("Prises");
        takenCol.setPrefWidth(70);
        history.getColumns().addAll(idCol, nameCol, actionCol, positionCol, takenCol);

        data = FXCollections.observableArrayList();

        nameCol.setCellValueFactory(
                new PropertyValueFactory<>("name")
        );
        actionCol.setCellValueFactory(
                new PropertyValueFactory<>("action")
        );
        positionCol.setCellValueFactory(
                new PropertyValueFactory<>("pos")
        );
        takenCol.setCellValueFactory(
                new PropertyValueFactory<>("taken")
        );

        history.setItems(data);
        history.setMinHeight(525);
    }

    /**
     * Creates an instance of an GUIMovesHistory which takes its values from
     * another one.
     *
     * @param historic the GUIMovesHistory the new instance takes its values
     * from.
     */
    public GUIMovesHistory(GUIMovesHistory historic) {
        this.data = historic.data;
        this.history = historic.history;
    }

    /**
     * Update the table after each turn.
     *
     * @param othello
     */
    public void updateMovesHistory(OthelloModel othello) {
        String name = othello.getMoveName();
        String action = othello.getMoveAction();
        String pos = othello.getMovePos();
        String taken = othello.getMoveTaken();
        data.add(new Move(name, action, pos, taken));
    }

    /**
     * Reinitialize the table.
     */
    public void reinitMovesHistory() {
        data.clear();
    }

    /**
     * Returns the TableView representing the history of moves of a game.
     *
     * @return the TableView representing the history of moves of a game.
     */
    public TableView getHistory() {
        return history;
    }

}
