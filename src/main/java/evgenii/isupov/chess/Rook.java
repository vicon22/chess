package evgenii.isupov.chess;

public class Rook extends ChessPiece {

    /**
     * Конструктор класса Rook, принимающий цвет фигуры.
     *
     * @param color Цвет фигуры ("White" или "Black").
     */
    public Rook(String color) {
        super(color);
    }

    /**
     * Метод возвращает символ фигуры (ладья).
     *
     * @return Символ "R".
     */
    @Override
    public String getSymbol() {
        return "R";
    }

    /**
     * Проверяет, может ли ладья переместиться на указанную позицию.
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

        // Ладья не может остаться на месте
        if (line == toLine && column == toColumn) {
            return false;
        }

        // Ладья может двигаться только по прямой
        if (line != toLine && column != toColumn) {
            return false; // Движение не по прямой
        }

        // Проверяем, что путь ладьи свободен
        if (line == toLine) {
            // Движение по горизонтали
            int step = (toColumn > column) ? 1 : -1; // Направление движения
            for (int currentCol = column + step; currentCol != toColumn; currentCol += step) {
                if (chessBoard.board[line][currentCol] != null) {
                    return false; // На пути есть другая фигура
                }
            }
        } else {
            // Движение по вертикали
            int step = (toLine > line) ? 1 : -1; // Направление движения
            for (int currentRow = line + step; currentRow != toLine; currentRow += step) {
                if (chessBoard.board[currentRow][column] != null) {
                    return false; // На пути есть другая фигура
                }
            }
        }

        // Проверяем, можно ли съесть фигуру на целевой клетке
        ChessPiece target = chessBoard.board[toLine][toColumn];
        if (target != null && !target.getColor().equals(this.getColor())) {
            return true; // Если на клетке фигура другого цвета, ладья может её съесть
        }

        // Если на клетке нет фигуры, или она того же цвета, перемещение возможно
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
