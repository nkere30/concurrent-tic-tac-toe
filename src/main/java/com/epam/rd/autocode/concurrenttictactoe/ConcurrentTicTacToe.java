package com.epam.rd.autocode.concurrenttictactoe;

public class ConcurrentTicTacToe implements TicTacToe{
    private final char[][] gameBoard;
    private  char lastMark;
    public ConcurrentTicTacToe() {
        this.gameBoard = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                gameBoard[i][j] = ' ';
            }
        }
    }
    @Override
    public void setMark(int x, int y, char mark) {
        if (gameBoard[x][y] == ' ') {
            gameBoard[x][y] = mark;
            lastMark = mark;
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public char[][] table() {
        char[][] copyGameBoard = new char[3][3];
        for (int i = 0; i < 3; i++) {
            System.arraycopy(gameBoard[i], 0, copyGameBoard[i], 0, 3);
        }
        return copyGameBoard;
    }

    @Override
    public char lastMark() {
        return lastMark;
    }

    public boolean isGameOver() {
        return hasWinner('X') || hasWinner('O') || isBoardFull();
    }

    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(gameBoard[i][j] == ' ')
                    return false;
            }
        }
        return true;
    }

    private boolean hasWinner(char mark) {
        //Check rows
        for (int i = 0; i < 3; i++) {
            if (gameBoard[i][0] == mark && gameBoard[i][1] == mark && gameBoard[i][2] == mark) {
                return true;
            }
        }
        //Check columns
        for (int i = 0; i < 3; i++) {
            if (gameBoard[0][i] == mark && gameBoard[1][i] == mark && gameBoard[2][i] == mark) {
                return true;
            }
        }
        //Check diagonals
        return (gameBoard[0][0] == mark && gameBoard[1][1] == mark && gameBoard[2][2] == mark)
                || (gameBoard[2][0] == mark && gameBoard[1][1] == mark && gameBoard[0][2] == mark);
    }
}
