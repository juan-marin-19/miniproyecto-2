package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SudokuBoard {
    // El tablero se representa como un ArrayList de 6 columnas,
    // donde cada columna es un ArrayList de 6 enteros.
    private ArrayList<ArrayList<Integer>> board;

    public SudokuBoard() {
        board = new ArrayList<>();
        // Inicializa cada columna con 6 ceros (0 representa celda vacía)
        for (int i = 0; i < 6; i++) {
            ArrayList<Integer> column = new ArrayList<>();
            for (int j = 0; j < 6; j++) {
                column.add(0);
            }
            board.add(column);
        }
    }

    /**
     * Llena el tablero colocando EXACTAMENTE un número aleatorio en cada bloque (2x3).
     * El resto de las celdas se mantienen en 0.
     */
    public void fillRandomOneNumberPerBlock() {
        // Cada bloque se define como: {filaInicio, filaFin, colInicio, colFin}
        int[][] blocks = {
                {0, 1, 0, 2}, // Bloque 0: filas 0-1, columnas 0-2
                {0, 1, 3, 5}, // Bloque 1: filas 0-1, columnas 3-5
                {2, 3, 0, 2}, // Bloque 2: filas 2-3, columnas 0-2
                {2, 3, 3, 5}, // Bloque 3: filas 2-3, columnas 3-5
                {4, 5, 0, 2}, // Bloque 4: filas 4-5, columnas 0-2
                {4, 5, 3, 5}  // Bloque 5: filas 4-5, columnas 3-5
        };

        // Para cada bloque, se intenta colocar un número en una de sus celdas
        for (int[] block : blocks) {
            int rowStart = block[0];
            int rowEnd = block[1];
            int colStart = block[2];
            int colEnd = block[3];

            // Se obtienen todas las celdas del bloque (cada celda es {fila, columna})
            List<int[]> cells = new ArrayList<>();
            for (int r = rowStart; r <= rowEnd; r++) {
                for (int c = colStart; c <= colEnd; c++) {
                    cells.add(new int[]{r, c});
                }
            }
            // Mezclamos las celdas para escoger una de forma aleatoria
            Collections.shuffle(cells);

            boolean placed = false;
            // Se recorre cada celda del bloque
            for (int[] cell : cells) {
                int row = cell[0];
                int col = cell[1];

                // Creamos una lista de candidatos (números del 1 al 6) y la mezclamos
                List<Integer> candidates = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
                Collections.shuffle(candidates);

                // Se prueba cada candidato para ver si se puede colocar sin conflictos
                for (int num : candidates) {
                    if (isSafe(row, col, num)) {
                        board.get(col).set(row, num);
                        placed = true;
                        break;
                    }
                }
                if (placed) break; // Se colocó un número en este bloque, pasar al siguiente
            }
            // Si no se pudo colocar ningún número, el bloque permanecerá con 0 en todas sus celdas.
        }
    }

    /**
     * Verifica que colocar 'num' en la celda (row, col) no repita el número en la misma fila ni columna.
     */
    private boolean isSafe(int row, int col, int num) {
        // Revisar la fila: se recorre cada columna de la misma fila
        for (int c = 0; c < 6; c++) {
            if (board.get(c).get(row) == num) {
                return false;
            }
        }
        // Revisar la columna: se recorre cada fila de la misma columna
        for (int r = 0; r < 6; r++) {
            if (board.get(col).get(r) == num) {
                return false;
            }
        }
        return true;
    }

    /**
     * Imprime el tablero en consola, mostrando cada fila en una línea.
     */
    public void printBoard() {
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 6; col++) {
                System.out.print(board.get(col).get(row) + " ");
            }
            System.out.println();
        }
    }
}