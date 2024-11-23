package evgenii.isupov.chess;

public class Horse extends ChessPiece {

    /**
     * Конструктор класса Horse, принимающий цвет фигуры.
     *
     * @param color Цвет фигуры ("White" или "Black").
     */
    public Horse(String color) {
        super(color);
    }

    /**
     * Метод возвращает символ фигуры (конь).
     *
     * @return Символ "H".
     */
    @Override
    public String getSymbol() {
        return "H";
    }

    /**
     * Проверяет, может ли конь переместиться на указанную позицию.
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

        // Конь не может остаться на месте
        if (line == toLine && column == toColumn) {
            return false;
        }

        // Проверяем перемещение "буквой Г"
        int deltaRow = Math.abs(line - toLine);
        int deltaCol = Math.abs(column - toColumn);

        // Если перемещение соответствует ходу коня, проверяем занятость целевой клетки
        if ((deltaRow == 2 && deltaCol == 1) || (deltaRow == 1 && deltaCol == 2)) {
            ChessPiece target = chessBoard.board[toLine][toColumn];

            // Если целевая клетка пуста, или на ней стоит фигура другого цвета, конь может съесть фигуру
            return target == null || !target.getColor().equals(getColor());
        }

        return false;
    }

    /**
     * Вспомогательный метод для проверки корректности позиции на доске.
     *
     * @param line   Строка.
     * @param column Столбец.
     * @return true, если позиция на доске, иначе false.
     */
    private boolean isValidPosition(int line, int column) {
        return line >= 0 && line < 8 && column >= 0 && column < 8;
    }
}
