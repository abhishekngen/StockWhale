

import java.util.ArrayList;

public class Pawn extends Piece{
    private Pawn(){}
    private static Pawn instance = null;

    public static Pawn getInstance() {
        if(instance == null){
            instance = new Pawn();
        }
        return instance;
    }
    @Override
    public ArrayList<String> getPossibleWhiteMoves(String originalcoord) {
        int row = Character.getNumericValue(originalcoord.charAt(1));
        int col = Character.getNumericValue(originalcoord.charAt(0));
        if(row == 7){
            possibleMoves.add(Integer.toString(row-2) + Integer.toString(col));
        }
        if(logic_board.boardLogic[row-2][col].equals("")){
            possibleMoves.add(Integer.toString(row-1)+Integer.toString(col));
        }
        if(!logic_board.boardLogic[row-2][col+1].equals("")){
            possibleMoves.add(Integer.toString(row-1) + Integer.toString(col+1));
        }
        if(!logic_board.boardLogic[row-2][col-1].equals("")){
            possibleMoves.add(Integer.toString(row-1) + Integer.toString(col-1));
        }
        return possibleMoves;
    }

    @Override
    public ArrayList<String> getPossibleBlackMoves(String originalcoord) {
        return null;
    }
}
