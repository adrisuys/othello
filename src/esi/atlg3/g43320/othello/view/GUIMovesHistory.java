/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esi.atlg3.g43320.othello.view;

import esi.atlg3.g43320.othello.model.OthelloModel;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author s_u_y_s_a
 */
public class GUIMovesHistory extends TableView {
    
    ObservableList<Move> data;

    public GUIMovesHistory() {
        TableColumn<Move, Number> idCol = new TableColumn<Move, Number>("ID");
        idCol.setSortable(false);
        idCol.setCellValueFactory(column -> new ReadOnlyObjectWrapper<Number>(getItems().indexOf(column.getValue())));
        TableColumn nameCol = new TableColumn("Name");
        TableColumn actionCol = new TableColumn("Action");
        TableColumn positionCol = new TableColumn("Position");
        TableColumn takenCol = new TableColumn("Prises");
        getColumns().addAll(idCol, nameCol, actionCol, positionCol, takenCol);

        data = FXCollections.observableArrayList();
        
        

        nameCol.setCellValueFactory(
                new PropertyValueFactory<Move, String>("name")
        );
        actionCol.setCellValueFactory(
                new PropertyValueFactory<Move, String>("action")
        );
        positionCol.setCellValueFactory(
                new PropertyValueFactory<Move, String>("pos")
        );
        takenCol.setCellValueFactory(
                new PropertyValueFactory<Move, String>("taken")
        );

        setItems(data);
        setMinHeight(550);
    }
    
    public void updateMovesHistory(OthelloModel othello){
        String name = othello.getMoveName();
        String action = othello.getMoveAction();
        String pos = othello.getMovePos();
        String taken = othello.getMoveTaken();
        data.add(new Move(name, action, pos, taken));
    }
}
