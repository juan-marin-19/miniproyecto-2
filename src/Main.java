public class Main {
    public static void main(String[] args) {
        SudokuBoard sudoku = new SudokuBoard();
        sudoku.fillRandomOneNumberPerBlock();
        sudoku.printBoard();
    }
}