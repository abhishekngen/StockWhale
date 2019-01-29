import java.util.ArrayList;

public class Bishop extends Piece {
    private Bishop(){}
    private static Bishop instance = null;

    public static Bishop getInstance() {
        if(instance == null){
            instance = new Bishop();
        }
        return instance;
    }
    @Override
    public ArrayList<String> getPossibleMoves(String originalcoord, boolean isWhitePlaying) {
        possibleMoves.clear();
        int row = Character.getNumericValue(originalcoord.charAt(1));
        int col = Character.getNumericValue(originalcoord.charAt(0));
        boolean enemyPiecePresent = false;
        int testrow = row;
        int testcol = col;
        for(int i = 0; i<4; i++) {
            while (!enemyPiecePresent) {
                if (i == 0) {
                    testcol += 1;
                    testrow += 1;
                } else if (i == 1) {
                    testcol += 1;
                    testrow -= 1;
                } else if (i == 2) {
                    testcol -= 1;
                    testrow += 1;
                } else {
                    testcol -= 1;
                    testrow -= 1;
                }
                if (testrow > 0 && testrow < 9 && testcol > -1 && testcol < 8) {
                    if (!(logic_board.boardLogic[testrow - 1][testcol].equals(""))) {
                        enemyPiecePresent = true;
                    }
                    addIfValid(testrow, testcol, isWhitePlaying);
                } else {
                    enemyPiecePresent = true;
                }
            }
            enemyPiecePresent = false;
            testcol = col;
            testrow = row;
        }
        /*
                addIfValid(testrow, testcol, isWhitePlaying);
                if (testrow < 9 && testrow > 0 && testcol > -1 && testcol < 8) {
                    if (logic_board.boardLogic[testrow - 1][testcol] != "") {
                        enemyPiecePresent = true;
                    }
                } else {
                    enemyPiecePresent = true;
                }
            }
            enemyPiecePresent = false;
            testrow = row;
            testcol = col;
            while (!enemyPiecePresent) {
                testrow--;
                testcol--;
                addIfValid(testrow, testcol, isWhitePlaying);
                if (testrow < 9 && testrow > 0 && testcol > -1 && testcol < 8) {
                    if (logic_board.boardLogic[testrow - 1][testcol] != "") {
                        enemyPiecePresent = true;
                    }
                } else {
                    enemyPiecePresent = true;
                }
            }
            enemyPiecePresent = false;
            testrow = row;
            testcol = col;
            while (!enemyPiecePresent) {
                testrow++;
                testcol--;
                addIfValid(testrow, testcol, isWhitePlaying);
                if (testrow < 9 && testrow > 0 && testcol > -1 && testcol < 8) {
                    if (logic_board.boardLogic[testrow - 1][testcol] != "") {
                        enemyPiecePresent = true;
                    }
                } else {
                    enemyPiecePresent = true;
                }
            }
            enemyPiecePresent = false;
            testrow = row;
            testcol = col;
            while (!enemyPiecePresent) {
                testrow--;
                testcol++;
                addIfValid(testrow, testcol, isWhitePlaying);
                if (testrow < 9 && testrow > 0 && testcol > -1 && testcol < 8) {
                    if (logic_board.boardLogic[testrow - 1][testcol] != "") {
                        enemyPiecePresent = true;
                    }
                } else {
                    enemyPiecePresent = true;
                }
            }
        }*/
        return possibleMoves;
    }
}
