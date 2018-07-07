import java.util.ArrayList;

public class Queen extends Piece {
    private Queen(){}
    private static Queen instance = null;

    public static Queen getInstance() {
        if(instance == null){
            instance = new Queen();
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
        testrow = row;
        testcol = col;
        enemyPiecePresent = false;
        while(!enemyPiecePresent){
            testrow+=1;
            if(testrow<9) {
                addIfValid(testrow, testcol, isWhitePlaying);
                if (!(logic_board.boardLogic[testrow - 1][testcol] == "")) {
                    enemyPiecePresent = true;
                }
            }
            else{
                enemyPiecePresent = true;
            }
        }
        enemyPiecePresent = false;
        testrow = row;
        while(!enemyPiecePresent){
            testrow-=1;
            if(testrow>0) {
                addIfValid(testrow, testcol, isWhitePlaying);
                if (!(logic_board.boardLogic[testrow - 1][testcol] == "")) {
                    enemyPiecePresent = true;
                }
            }
            else{
                enemyPiecePresent = true;
            }
        }
        enemyPiecePresent = false;
        testrow = row;
        while(!enemyPiecePresent){
            testcol+=1;
            if(testcol<8) {
                addIfValid(testrow, testcol, isWhitePlaying);
                if (!(logic_board.boardLogic[testrow - 1][testcol] == "")) {
                    enemyPiecePresent = true;
                }
            }else{
                enemyPiecePresent = true;
            }
        }
        enemyPiecePresent = false;
        testcol = col;
        while(!enemyPiecePresent){
            testcol-=1;
            if(testcol>-1) {
                addIfValid(testrow, testcol, isWhitePlaying);
                if (!(logic_board.boardLogic[testrow - 1][testcol] == "")) {
                    enemyPiecePresent = true;
                }
            }else{
                enemyPiecePresent = true;
            }
        }
        return possibleMoves;
    }
}
