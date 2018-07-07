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
        while(!enemyPiecePresent){
            testrow++;
            testcol++;
            addIfValid(testrow, testcol, isWhitePlaying);
            if(testrow<9 && testrow>0 && testcol>-1 && testcol<8){
                if(logic_board.boardLogic[testrow-1][testcol]!="") {
                    enemyPiecePresent = true;
                }
            }
            else{
                enemyPiecePresent = true;
            }
        }
        enemyPiecePresent = false;
        testrow = row;
        testcol = col;
        while(!enemyPiecePresent){
            testrow--;
            testcol--;
            addIfValid(testrow, testcol, isWhitePlaying);
            if(testrow<9 && testrow>0 && testcol>-1 && testcol<8){
                if(logic_board.boardLogic[testrow-1][testcol]!="") {
                    enemyPiecePresent = true;
                }
            }
            else{
                enemyPiecePresent = true;
            }
        }
        enemyPiecePresent = false;
        testrow = row;
        testcol = col;
        while(!enemyPiecePresent){
            testrow++;
            testcol--;
            addIfValid(testrow, testcol, isWhitePlaying);
            if(testrow<9 && testrow>0 && testcol>-1 && testcol<8){
                if(logic_board.boardLogic[testrow-1][testcol]!="") {
                    enemyPiecePresent = true;
                }
            }
            else{
                enemyPiecePresent = true;
            }
        }
        enemyPiecePresent = false;
        testrow = row;
        testcol = col;
        while(!enemyPiecePresent){
            testrow--;
            testcol++;
            addIfValid(testrow, testcol, isWhitePlaying);
            if(testrow<9 && testrow>0 && testcol>-1 && testcol<8){
                if(logic_board.boardLogic[testrow-1][testcol]!="") {
                    enemyPiecePresent = true;
                }
            }
            else{
                enemyPiecePresent = true;
            }
        }
        return possibleMoves;
    }
}
