package evgenii.isupov.chess;

public class King extends ChessPiece {

    /**
     * Конструктор класса King, принимающий цвет фигуры.
     *
     * @param color Цвет фигуры ("White" или "Black").
     */
    public King(String color) {
        super(color);
    }

    /**
     * Метод возвращает символ фигуры (король).
     *
     * @return Символ "K".
     */
    @Override
    public String getSymbol() {
        return "K";
    }

    /**
     * Проверяет, может ли король переместиться на указанную позицию.
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

        // Король не может остаться на месте
        if (line == toLine && column == toColumn) {
            return false;
        }

        // Король может двигаться максимум на 1 клетку в любую сторону
        if (Math.abs(line - toLine) <= 1 && Math.abs(column - toColumn) <= 1) {
            // Проверяем, не находится ли конечная позиция под атакой
            if (chessBoard.board[toLine][toColumn] == null || !chessBoard.board[toLine][toColumn].getColor().equals(this.getColor())) {
                // Если на целевой клетке пусто или там фигура противника
                return true;  // Король может переместиться
            }
        }

        return false;
    }

    /**
     * Проверяет, находится ли клетка под атакой.
     *
     * @param chessBoard Шахматная доска.
     * @param line       Строка для проверки.
     * @param column     Столбец для проверки.
     * @return true, если клетка под атакой, иначе false.
     */
    public boolean isUnderAttack(ChessBoard chessBoard, int line, int column) {
        // Проверяем все фигуры противника
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                ChessPiece piece = chessBoard.board[i][j];
                if (piece != null && !piece.getColor().equals(this.getColor())) {
                    if (piece.canMoveToPosition(chessBoard, i, j, line, column)) {
                        return true; // Если фигура противника может атаковать клетку, значит она под атакой
                    }
                }
            }
        }
        return false;
    }
}
