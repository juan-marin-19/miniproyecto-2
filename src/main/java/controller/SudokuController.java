package controller;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.geometry.Pos;

import java.awt.event.ActionEvent;
import java.io.IOException;


public class SudokuController {

    private TextField[][] cells = new TextField[6][6];

    @FXML
    public GridPane gridSudoku;

    @FXML
    private Button help;


    public void helpButton() {

        help.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            public void handle(javafx.event.ActionEvent event) {
                System.out.println("HELP");
            }
        });

    }



    public void initialize() {
        // Quitar gridLinesVisible desde el FXML o aquí:
        gridSudoku.setGridLinesVisible(false);

        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 6; col++) {
                TextField txField = new TextField();
                txField.setPrefSize(50, 50);
                txField.setAlignment(Pos.CENTER);

                double top    = (row % 2 == 0) ? 5 : 1;    // cada 2 filas, borde más grueso
                double left   = (col % 3 == 0) ? 5 : 1;    // cada 3 columnas, borde más grueso
                double bottom = (row == 5)       ? 5 : 1;  // borde exterior inferior
                double right  = (col == 5)       ? 5 : 1;  // borde exterior derecho

                String style = String.format(
                        "-fx-border-style: solid solid solid solid; " +
                                "-fx-border-color: black black black black; " +
                                "-fx-border-width: %.0f %.0f %.0f %.0f;",
                        top, right, bottom, left
                );
                txField.setStyle(style);

                gridSudoku.add(txField, col, row);
            }
        }
        System.out.println("Sudoku");
    }





}

