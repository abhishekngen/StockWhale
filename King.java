import java.util.ArrayList;

public class King extends Piece {
    private King(){}
    private static King instance = null;

    public static King getInstance() {
        if(instance == null){
            instance = new King();
        }
        return instance;
    }
    @Override
    public ArrayList<String> getPossibleMoves(String originalcoord, boolean isWhitePlaying) {
        possibleMoves.clear();
        int row = Character.getNumericValue(originalcoord.charAt(1));
        int col = Character.getNumericValue(originalcoord.charAt(0));
        addIfValid(row+1, col, isWhitePlaying);
        addIfValid(row+1, col+1, isWhitePlaying);
        addIfValid(row, col+1, isWhitePlaying);
        addIfValid(row-1, col+1, isWhitePlaying);
        addIfValid(row-1, col, isWhitePlaying);
        addIfValid(row-1, col-1, isWhitePlaying);
        addIfValid(row, col-1, isWhitePlaying);
        addIfValid(row+1, col-1, isWhitePlaying);
        return possibleMoves;
    }
}
