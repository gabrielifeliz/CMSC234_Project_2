package com.zybooks.tictactoe;

import java.util.*;

/**
 * This class contains all attributes for a Tic Tac Toe game
 * @author Gabriel Isaac Feliz
 */
public class TicTacToeGame {

    // Number of rows and columns
    public static final int NUM_ROWS = 3;
    public static final int NUM_COLS = 3;

    // Lights that make up the grid
    private char[][] mTicTacToe;

    // Characters that represent two different players and no selection
    public static final char PLAYER_X = 'X';
    public static final char PLAYER_O = 'O';
    public static final char EMPTY = ' ';

    // Character that represent the current turn
    private char turn = EMPTY;

    // Initialize 2D array that represents the game state
    public TicTacToeGame() {
        mTicTacToe = new char[NUM_ROWS][NUM_COLS];
    }


    public void newGame() {
        for (int row = 0; row < NUM_ROWS; row++) {
            for (int col = 0; col < NUM_COLS; col++) {
                // Set each square empty for a new game
                mTicTacToe[row][col] = EMPTY;
            }
        }
    }

    public void selectButton(int row, int col) {
        // Check if current turn is X
        if (turn == PLAYER_X) {
            // Set the selection to X
            mTicTacToe[row][col] = PLAYER_X;
        } // Check if current turn is O
        else if (turn == PLAYER_O) {
            // Set the selection to O
            mTicTacToe[row][col] = PLAYER_O;
        }
    }

    public boolean isThereWinner() {
        // Check if any column, row, or diagonal has the same type of selection
        return isAnyColumnEqual() || isAnyRowEqual() || isAnyDiagonalEqual();
    }

    private boolean isAnyColumnEqual() {
        for (int row = 0; row < NUM_ROWS; row++) {
            // Check if the first cell in the current row has been selected for player X or O
            if (new ArrayList<>(Arrays.asList(PLAYER_X, PLAYER_O))
                    .contains(mTicTacToe[row][0])) {
                // Check if the entire row has the same type of selection
                if (mTicTacToe[row][0] == mTicTacToe[row][1] &&
                        mTicTacToe[row][0] == mTicTacToe[row][2]) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isAnyRowEqual() {
        for (int col = 0; col < NUM_COLS; col++) {
            // Check if the first cell in the current column has been selected for player X or O
            if (new ArrayList<>(Arrays.asList(PLAYER_X, PLAYER_O))
                    .contains(mTicTacToe[0][col])) {
                // Check if the entire column has the same type of selection
                if (mTicTacToe[0][col] == mTicTacToe[1][col] &&
                        mTicTacToe[0][col] == mTicTacToe[2][col]) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isAnyDiagonalEqual() {
        // Initialize match to false
        boolean match = false;

        if (new ArrayList<>(Arrays.asList(PLAYER_X, PLAYER_O))
                .contains(mTicTacToe[0][0])) {
            if (mTicTacToe[0][0] == mTicTacToe[1][1] && mTicTacToe[0][0] == mTicTacToe[2][2]) {
                // Set match turn if the first diagonal checked has the same selection type
                match = true;
            }
        }

        if (new ArrayList<>(Arrays.asList(PLAYER_X, PLAYER_O))
                .contains(mTicTacToe[2][0])) {
            if (mTicTacToe[2][0] == mTicTacToe[1][1] && mTicTacToe[2][0] == mTicTacToe[0][2]) {
                // Set match turn if the second diagonal checked has the same selection type
                match = true;
            }
        }
        return match;
    }

    public boolean isGameComplete() {
        for (int row = 0; row < NUM_ROWS; row++) {
            for (int col = 0; col < NUM_COLS; col++) {
                if (mTicTacToe[row][col] == EMPTY) {
                    // Return false if current selection is empty
                    return false;
                }
            }
        }
        // Return true if all selections come from either X or O
        return true;
    }

    public void initializeTurn() {
        // Randomly select the initial turn
        if (new Random().nextBoolean()){
            turn = PLAYER_X;
        } else {
            turn = PLAYER_O;
        }
    }

    public boolean isTurnComputer(boolean cpuMode) {
        return cpuMode && new Random().nextBoolean();
    }

    public void changeTurn() {
        if (turn == 'X') {
            // Change turn to O if turn used to be X
            turn = PLAYER_O;
        } else if (turn == 'O') {
            // Change turn to X if turn used to be O
            turn = PLAYER_X;
        }
    }

    public char getTurn() {
        // Return current turn
        return turn;
    }

    public ArrayList<Integer> cpuMode() {
        ArrayList<Character> list = new ArrayList<>(Arrays.asList(PLAYER_X, PLAYER_O));
        ArrayList<Integer> indexes = new ArrayList<>();

        for (int col = 0; col < NUM_COLS; col++) {
            // Check if the first cell in the current column has been selected for player X or O
            if (list.contains(mTicTacToe[0][col]) || list.contains(mTicTacToe[1][col])
                    || list.contains(mTicTacToe[2][col])) {
                // Check if the entire column has the same type of selection
                if (mTicTacToe[0][col] == mTicTacToe[1][col]) {
                    mTicTacToe[2][col] = turn;
                    indexes = new ArrayList<>(Arrays.asList(2, col));
                } else if (mTicTacToe[0][col] == mTicTacToe[2][col]) {
                    mTicTacToe[1][col] = turn;
                    indexes = new ArrayList<>(Arrays.asList(1, col));
                } else if (mTicTacToe[1][col] == mTicTacToe[2][col]) {
                    mTicTacToe[0][col] = turn;
                    indexes = new ArrayList<>(Arrays.asList(0, col));
                }
            }
        }

        for (int row = 0; row < NUM_ROWS; row++) {
            // Check if the first cell in the current row has been selected for player X or O
            if (list.contains(mTicTacToe[row][0]) || list.contains(mTicTacToe[row][1])
                    || list.contains(mTicTacToe[row][2])) {
                // Check if the entire row has the same type of selection
                if (mTicTacToe[row][0] == mTicTacToe[row][1]) {
                    mTicTacToe[row][2] = turn;
                    indexes = new ArrayList<>(Arrays.asList(row, 2));
                } else if (mTicTacToe[row][0] == mTicTacToe[row][2]) {
                    mTicTacToe[row][1] = turn;
                    indexes = new ArrayList<>(Arrays.asList(row, 1));
                } else if (mTicTacToe[row][1] == mTicTacToe[row][2]) {
                    mTicTacToe[row][0] = turn;
                    indexes = new ArrayList<>(Arrays.asList(row, 0));
                }
            }
        }

        if (list.contains(mTicTacToe[0][0]) || list.contains(mTicTacToe[1][1])
                || list.contains(mTicTacToe[2][2])) {
            if (mTicTacToe[0][0] == mTicTacToe[1][1]) {
                mTicTacToe[2][2] = turn;
                indexes = new ArrayList<>(Arrays.asList(2, 2));
            } else if (mTicTacToe[0][0] == mTicTacToe[2][2]) {
                mTicTacToe[1][1] = turn;
                indexes = new ArrayList<>(Arrays.asList(1, 1));
            } else if (mTicTacToe[1][1] == mTicTacToe[2][2]) {
                mTicTacToe[0][0] = turn;
                indexes = new ArrayList<>(Arrays.asList(0, 0));
            }
        }

        if (list.contains(mTicTacToe[2][0]) || list.contains(mTicTacToe[1][1])
                || list.contains(mTicTacToe[0][2])) {
            if (mTicTacToe[2][0] == mTicTacToe[1][1]) {
                mTicTacToe[0][2] = turn;
                indexes = new ArrayList<>(Arrays.asList(0, 2));
            } else if (mTicTacToe[2][0] == mTicTacToe[0][2]) {
                mTicTacToe[1][1] = turn;
                indexes = new ArrayList<>(Arrays.asList(1, 1));
            } else if (mTicTacToe[1][1] == mTicTacToe[0][2]) {
                mTicTacToe[2][0] = turn;
                indexes = new ArrayList<>(Arrays.asList(2, 0));
            }
        }
        return indexes;
    }

    public String getState() {
        // Create string builder for game state
        StringBuilder boardString = new StringBuilder();
        for (int row = 0; row < NUM_ROWS; row++) {
            for (int col = 0; col < NUM_COLS; col++) {
                // Add each selection to string builder
                boardString.append(mTicTacToe[row][col]);
            }
        }
        // Return string version of game state
        return boardString.toString();
    }

    public void restoreState(String gameState, char initTurn) {
        // Initialize game state index, and number of X's and O's to zero
        int index = 0, numXs = 0, numOs = 0;
        for (int row = 0; row < NUM_ROWS; row++) {
            for (int col = 0; col < NUM_COLS; col++) {
                // Set saved game state character into current game state
                mTicTacToe[row][col] = gameState.charAt(index);
                if (mTicTacToe[row][col] == PLAYER_X) {
                    // Increase number of X's if current character in game state is X
                    numXs++;
                } else if (mTicTacToe[row][col] == PLAYER_O) {
                    // Increase number of O's if current character in game state is O
                    numOs++;
                }
                // Increase game state
                index++;
            }
        }

        if (numXs > numOs) {
            // Set turn to O if there are more X's than O's in game state
            turn = PLAYER_O;
        } else if (numXs < numOs) {
            // Set turn to X if there are more O's than X's in game state
            turn = PLAYER_X;
        } else {
            // Set turn to initial turn if the number of X's and O's is the same
            turn = initTurn;
        }
    }
}
