import java.util.ArrayList;

public class Rook extends Piece {
    private Rook(){}
    private static Rook instance = null;

    public static Rook getInstance() {
        if(instance == null){
            instance = new Rook();
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
