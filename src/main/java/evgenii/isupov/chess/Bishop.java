package evgenii.isupov.chess;

public class Bishop extends ChessPiece {

    /**
     * Конструктор класса Bishop, принимающий цвет фигуры.
     *
     * @param color Цвет фигуры ("White" или "Black").
     */
    public Bishop(String color) {
        super(color);
    }

    /**
     * Метод возвращает символ фигуры (слон).
     *
     * @return Символ "B".
     */
    @Override
    public String getSymbol() {
        return "B";
    }

    /**
     * Проверяет, может ли слон переместиться на указанную позицию.
     *
     * @param chessBoard Объект шахматной доски.
     * @param line       Текущая строка (ряды).
     * @param column     Текущий столбец.
     * @param toLine     Конечная строка.
     * @param toColumn   Конечный столбец.
     * @return true, если перемещение возможно, иначе false.
     */
    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // Проверяем, не выходит ли позиция за пределы доски
        if (!isValidPosition(line, column) || !isValidPosition(toLine, toColumn)) {
            return false;
        }

        // Слон не может остаться на месте
        if (line == toLine && column == toColumn) {
            return false;
        }

        // Проверяем движение по диагонали
        int deltaRow = Math.abs(line - toLine);
        int deltaCol = Math.abs(column - toColumn);

        if (deltaRow != deltaCol) {
            return false; // Движение не по диагонали
        }

        // Проверяем, что путь слона свободен или на пути есть фигура противоположного цвета
        int stepRow = (toLine > line) ? 1 : -1;  // Шаг по строкам
        int stepCol = (toColumn > column) ? 1 : -1; // Шаг по столбцам

        int currentRow = line + stepRow;
        int currentCol = column + stepCol;

        while (currentRow != toLine && currentCol != toColumn) {
            if (chessBoard.board[currentRow][currentCol] != null) {
                // Если на пути стоит фигура, проверяем, можем ли мы её съесть
                if (!chessBoard.board[currentRow][currentCol].getColor().equals(this.getColor())) {
                    return true; // Можем съесть фигуру противоположного цвета
                } else {
                    return false; // На пути стоит фигура того же цвета
                }
            }
            currentRow += stepRow;
            currentCol += stepCol;
        }

        return true; // Путь слона свободен
    }
}
