package evgenii.isupov.chess;

public class Pawn extends ChessPiece {

    /**
     * Конструктор класса Pawn, принимающий цвет фигуры.
     *
     * @param color Цвет фигуры ("White" или "Black").
     */
    public Pawn(String color) {
        super(color);
    }

    /**
     * Метод возвращает символ фигуры (пешка).
     *
     * @return Символ "P".
     */
    @Override
    public String getSymbol() {
        return "P";
    }

    /**
     * Проверяет, может ли пешка переместиться на указанную позицию.
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

        // Пешка не может остаться на месте
        if (line == toLine && column == toColumn) {
            return false;
        }

        // Пешка не может двигаться по диагонали в пустую клетку
        if (column != toColumn) {
            return false;
        }

        // Проверяем движение пешки в зависимости от её цвета
        if (getColor().equals("White")) {
            // Белая пешка
            if (line == 1 && toLine == line + 2 && chessBoard.board[toLine][toColumn] == null) {
                return true; // Первый ход на 2 клетки вперёд
            }
            if (toLine == line + 1 && chessBoard.board[toLine][toColumn] == null) {
                return true; // Ход на 1 клетку вперёд
            }
        } else if (getColor().equals("Black")) {
            // Чёрная пешка
            if (line == 6 && toLine == line - 2 && chessBoard.board[toLine][toColumn] == null) {
                return true; // Первый ход на 2 клетки вперёд
            }
            if (toLine == line - 1 && chessBoard.board[toLine][toColumn] == null) {
                return true; // Ход на 1 клетку вперёд
            }
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
