package com.epam.rd.autocode.concurrenttictactoe;

public class PlayerImpl extends Thread implements Player{
    private final TicTacToe ticTacToe;
    private final char mark;
    private final PlayerStrategy strategy;

    public PlayerImpl(TicTacToe ticTacToe, char mark, PlayerStrategy strategy){
        this.ticTacToe = ticTacToe;
        this.mark = mark;
        this.strategy = strategy;
    }
    @Override
    public void run() {
        while (!ticTacToe.isGameOver()) {
            synchronized (ticTacToe) {
                if (ticTacToe.isGameOver()) {
                    break;
                }
                Move move = strategy.computeMove(mark, ticTacToe);
                if (move != null) {
                    ticTacToe.setMark(move.row, move.column, mark);
                }
            }
        }
    }
}
