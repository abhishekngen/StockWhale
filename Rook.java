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
        int testRow = row;
        int testCol = col;
        for(int i = 0; i<4; i++){
            while(!enemyPiecePresent){
                if(i == 0){
                    testRow += 1;
                }
                else if(i == 1){
                    testRow -= 1;
                }
                else if(i == 2){
                    testCol += 1;
                }
                else{
                    testCol -= 1;
                }
                if(testRow > 0 && testRow < 9 && testCol > -1 && testCol < 8){
                    addIfValid(testRow, testCol, isWhitePlaying);
                    if(!(logic_board.boardLogic[testRow - 1][testCol].equals(""))){
                        enemyPiecePresent = true;
                    }
                }
                else{
                    enemyPiecePresent = true;
                }
            }
            testCol = col;
            testRow = row;
            enemyPiecePresent = false;
        }/*
        while(!enemyPiecePresent){
            testRow+=1;
            if(testRow<9) {
                addIfValid(testRow, testCol, isWhitePlaying);
                if (!(logic_board.boardLogic[testRow - 1][testCol] == "")) {
                    enemyPiecePresent = true;
                }
            }
            else{
                enemyPiecePresent = true;
            }
        }
        enemyPiecePresent = false;
        testRow = row;
        while(!enemyPiecePresent){
            testRow-=1;
            if(testRow>0) {
                addIfValid(testRow, testCol, isWhitePlaying);
                if (!(logic_board.boardLogic[testRow - 1][testCol] == "")) {
                    enemyPiecePresent = true;
                }
            }
            else{
                enemyPiecePresent = true;
            }
        }
        enemyPiecePresent = false;
        testRow = row;
        while(!enemyPiecePresent){
            testCol+=1;
            if(testCol<8) {
                addIfValid(testRow, testCol, isWhitePlaying);
                if (!(logic_board.boardLogic[testRow - 1][testCol] == "")) {
                    enemyPiecePresent = true;
                }
            }else{
                enemyPiecePresent = true;
            }
        }
        enemyPiecePresent = false;
        testCol = col;
        while(!enemyPiecePresent){
            testCol-=1;
            if(testCol>-1) {
                addIfValid(testRow, testCol, isWhitePlaying);
                if (!(logic_board.boardLogic[testRow - 1][testCol] == "")) {
                    enemyPiecePresent = true;
                }
            }else{
                enemyPiecePresent = true;
            }
        }*/
        return possibleMoves;
    }
}
