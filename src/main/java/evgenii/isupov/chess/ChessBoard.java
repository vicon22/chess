package evgenii.isupov.chess;

public class ChessBoard {
    public ChessPiece[][] board = new ChessPiece[8][8]; // creating a field for game
    String nowPlayer;

    public ChessBoard(String nowPlayer) {
        this.nowPlayer = nowPlayer;
    }

    public String nowPlayerColor() {
        return this.nowPlayer;
    }

    public boolean moveToPosition(int startLine, int startColumn, int endLine, int endColumn) {
        if (checkPos(startLine) && checkPos(startColumn)) {
            ChessPiece piece = board[startLine][startColumn];

            // Проверяем, принадлежит ли фигура текущему игроку
            if (piece == null || !nowPlayer.equals(piece.getColor())) return false;

            // Проверяем, может ли фигура двигаться
            if (piece.canMoveToPosition(this, startLine, startColumn, endLine, endColumn)) {
                // Выполняем перемещение
                board[endLine][endColumn] = piece;
                board[startLine][startColumn] = null;

                // Обновляем флаг check (фигура больше не "неподвижная")
                piece.check = false;

                // Меняем текущего игрока
                nowPlayer = nowPlayer.equals("White") ? "Black" : "White";
                return true;
            }
        }
        return false;
    }


    public void printBoard() {  //print board in console
        System.out.println("Turn " + nowPlayer);
        System.out.println();
        System.out.println("Player 2(Black)");
        System.out.println();
        System.out.println("\t0\t1\t2\t3\t4\t5\t6\t7");

        for (int i = 7; i > -1; i--) {
            System.out.print(i + "\t");
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == null) {
                    System.out.print(".." + "\t");
                } else {
                    System.out.print(board[i][j].getSymbol() + board[i][j].getColor().substring(0, 1).toLowerCase() + "\t");
                }
            }
            System.out.println();
            System.out.println();
        }
        System.out.println("Player 1(White)");
    }

    public boolean checkPos(int pos) {
        return pos >= 0 && pos <= 7;
    }

    public boolean castling0() {
        int row = nowPlayer.equals("White") ? 0 : 7; // Выбираем строку игрока

        // Проверяем, что король и ладья находятся на своих стартовых позициях
        ChessPiece king = board[row][4];
        ChessPiece rook = board[row][0];

        if (king instanceof King && rook instanceof Rook
                && king.check && rook.check) {
            // Проверяем, что между королём и ладьёй нет других фигур
            for (int i = 1; i < 4; i++) {
                if (board[row][i] != null) return false;
            }

            // Проверяем, что король не находится под атакой ни в одной точке пути
            if (!((King) king).isUnderAttack(this, row, 4)
                    && !((King) king).isUnderAttack(this, row, 3)
                    && !((King) king).isUnderAttack(this, row, 2)) {
                // Выполняем рокировку
                board[row][2] = king;
                board[row][3] = rook;
                board[row][4] = null;
                board[row][0] = null;

                // Обновляем статус "check" для короля и ладьи
                king.check = false;
                rook.check = false;

                // Меняем текущего игрока
                nowPlayer = nowPlayer.equals("White") ? "Black" : "White";
                return true;
            }
        }
        return false;
    }

    public boolean castling7() {
        int row = nowPlayer.equals("White") ? 0 : 7; // Выбираем строку игрока

        // Проверяем, что король и ладья находятся на своих стартовых позициях
        ChessPiece king = board[row][4];
        ChessPiece rook = board[row][7];

        if (king instanceof King && rook instanceof Rook
                && king.check && rook.check) {
            // Проверяем, что между королём и ладьёй нет других фигур
            for (int i = 5; i < 7; i++) {
                if (board[row][i] != null) return false;
            }

            // Проверяем, что король не находится под атакой ни в одной точке пути
            if (!((King) king).isUnderAttack(this, row, 4)
                    && !((King) king).isUnderAttack(this, row, 5)
                    && !((King) king).isUnderAttack(this, row, 6)) {
                // Выполняем рокировку
                board[row][6] = king;
                board[row][5] = rook;
                board[row][4] = null;
                board[row][7] = null;

                // Обновляем статус "check" для короля и ладьи
                king.check = false;
                rook.check = false;

                // Меняем текущего игрока
                nowPlayer = nowPlayer.equals("White") ? "Black" : "White";
                return true;
            }
        }
        return false;
    }
}
