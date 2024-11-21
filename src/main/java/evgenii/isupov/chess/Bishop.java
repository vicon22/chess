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

        // Проверяем, что путь слона свободен
        int stepRow = (toLine > line) ? 1 : -1;  // Шаг по строкам
        int stepCol = (toColumn > column) ? 1 : -1; // Шаг по столбцам

        int currentRow = line + stepRow;
        int currentCol = column + stepCol;

        while (currentRow != toLine && currentCol != toColumn) {
            if (chessBoard.board[currentRow][currentCol] != null) {
                return false; // На пути есть другая фигура
            }
            currentRow += stepRow;
            currentCol += stepCol;
        }

        return true;
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

