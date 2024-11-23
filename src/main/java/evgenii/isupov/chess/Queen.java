package evgenii.isupov.chess;

public class Queen extends ChessPiece {

    /**
     * Конструктор класса Queen, принимающий цвет фигуры.
     *
     * @param color Цвет фигуры ("White" или "Black").
     */
    public Queen(String color) {
        super(color);
    }

    /**
     * Метод возвращает символ фигуры (ферзь).
     *
     * @return Символ "Q".
     */
    @Override
    public String getSymbol() {
        return "Q";
    }

    /**
     * Проверяет, может ли ферзь переместиться на указанную позицию.
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
        // Проверяем, что позиции корректны
        if (!isValidPosition(line, column) || !isValidPosition(toLine, toColumn)) {
            return false;
        }

        // Ферзь не может остаться на месте
        if (line == toLine && column == toColumn) {
            return false;
        }

        // Проверяем движение как у ладьи (по горизонтали или вертикали)
        if (line == toLine || column == toColumn) {
            return isPathClear(chessBoard, line, column, toLine, toColumn, false);
        }

        // Проверяем движение как у слона (по диагонали)
        if (Math.abs(line - toLine) == Math.abs(column - toColumn)) {
            return isPathClear(chessBoard, line, column, toLine, toColumn, false);
        }

        return false;
    }

    /**
     * Вспомогательный метод для проверки корректности позиции на доске.
     */
    private boolean isValidPosition(int line, int column) {
        return line >= 0 && line < 8 && column >= 0 && column < 8;
    }

    /**
     * Вспомогательный метод для проверки, что путь между начальной и конечной точкой свободен.
     * При этом, если на конечной клетке находится фигура противника, перемещение возможно.
     *
     * @param chessBoard Объект шахматной доски.
     * @param line       Текущая строка (ряд).
     * @param column     Текущий столбец.
     * @param toLine     Конечная строка.
     * @param toColumn   Конечный столбец.
     * @param isAttack   Нужно ли учитывать возможность атаки (если true, то проверяем на фигуру другого цвета).
     * @return true, если путь свободен или фигура может быть съедена, иначе false.
     */
    private boolean isPathClear(ChessBoard chessBoard, int line, int column, int toLine, int toColumn, boolean isAttack) {
        int stepRow = Integer.compare(toLine, line);
        int stepCol = Integer.compare(toColumn, column);

        int currentRow = line + stepRow;
        int currentCol = column + stepCol;

        while (currentRow != toLine || currentCol != toColumn) {
            if (chessBoard.board[currentRow][currentCol] != null) {
                return false; // На пути есть фигура
            }
            currentRow += stepRow;
            currentCol += stepCol;
        }

        // Проверяем, может ли фигура на конечной клетке быть съедена
        ChessPiece targetPiece = chessBoard.board[toLine][toColumn];
        if (targetPiece != null) {
            // Фигура может быть съедена только если она противоположного цвета
            if (targetPiece.getColor().equals(this.getColor())) {
                return false; // Фигура того же цвета, не может быть съедена
            }
        }

        return true; // Путь свободен или фигура может быть съедена
    }
}
