package com.epam.rd.autocode.concurrenttictactoe;

import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PlayerImpl extends Thread implements Player{
    private final TicTacToe ticTacToe;
    private final char mark;
    private final PlayerStrategy strategy;
    static final Object monitor = new Object();

    public PlayerImpl(TicTacToe ticTacToe, char mark, PlayerStrategy strategy){
        this.ticTacToe = ticTacToe;
        this.mark = mark;
        this.strategy = strategy;
    }
    @Override
    public void run() {
        synchronized (monitor) {
            try {
                while (!ticTacToe.isGameOver()) {
                    if (ticTacToe.lastMark() == mark) {
                        monitor.wait();
                    } else {
                        Move move = strategy.computeMove(mark, ticTacToe);
                        ticTacToe.setMark(move.row, move.column, mark);
                        monitor.notify();
                    }
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
