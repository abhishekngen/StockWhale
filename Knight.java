import java.util.ArrayList;

public class Knight extends Piece {
    private Knight(){}
    private static Knight instance = null;

    public static Knight getInstance() {
        if(instance == null){
            instance = new Knight();
        }
        return instance;
    }
    @Override
    public ArrayList<String> getPossibleMoves(String originalcoord, boolean isWhitePlaying) {
        possibleMoves.clear();
        int row = Character.getNumericValue(originalcoord.charAt(1));
        int col = Character.getNumericValue(originalcoord.charAt(0));
        addIfValid(row-2, col+1, isWhitePlaying);
        addIfValid(row-1, col+2, isWhitePlaying);
        addIfValid(row+1, col+2, isWhitePlaying);
        addIfValid(row+2, col+1, isWhitePlaying);
        addIfValid(row-2, col-1, isWhitePlaying);
        addIfValid(row-1, col-2, isWhitePlaying);
        addIfValid(row+1, col-2, isWhitePlaying);
        addIfValid(row+2, col-1, isWhitePlaying);
        return possibleMoves;
    }
}
