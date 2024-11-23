package evgenii.isupov.chess;

public abstract class ChessPiece {
    private String color; // цвет фигуры, "White" или "Black"
    protected boolean check = true; // логическая переменная, по умолчанию true

    /**
     * Конструктор, принимающий цвет фигуры.
     *
     * @param color Цвет фигуры ("White" или "Black").
     * @throws IllegalArgumentException Если цвет не "White" или "Black".
     */
    public ChessPiece(String color) {
        if (!color.equals("White") && !color.equals("Black")) {
            throw new IllegalArgumentException("Color must be 'White' or 'Black'");
        }
        this.color = color;
    }

    /**
     * Метод возвращает цвет фигуры.
     *
     * @return Цвет фигуры.
     */
    public String getColor() {
        return this.color;
    }

    /**
     * Абстрактный метод для проверки, может ли фигура переместиться на указанную позицию.
     *
     * @param chessBoard Объект шахматной доски.
     * @param line       Текущая строка (ряды).
     * @param column     Текущий столбец.
     * @param toLine     Конечная строка.
     * @param toColumn   Конечный столбец.
     * @return true, если перемещение возможно, иначе false.
     */
    public abstract boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn);

    /**
     * Абстрактный метод для получения символа фигуры.
     *
     * @return Символ фигуры (например, "P" для пешки).
     */
    public abstract String getSymbol();

    /**
     * Вспомогательный метод для проверки корректности позиции на доске.
     *
     * @param line   Строка.
     * @param column Столбец.
     * @return true, если позиция на доске, иначе false.
     */
    public boolean isValidPosition(int line, int column) {
        return line >= 0 && line < 8 && column >= 0 && column < 8;
    }
}

