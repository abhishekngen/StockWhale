import java.util.ArrayList;

public class Knight extends Piece {

    //Implemented getPossibleMoves function
    @Override
    public ArrayList<String> getPossibleMoves(String originalcoord, boolean isWhitePlaying) {
        possibleMoves.clear();

        //Extracts row and column values from coordinates of piece position
        int row = Character.getNumericValue(originalcoord.charAt(1));
        int col = Character.getNumericValue(originalcoord.charAt(0));

        //Adds each of the ending squares of the L shape move the knight can perform using addIfValid() method, which only adds if square is empty or occupied by piece of opposing side - the only validation required for adding the knight moves
        addIfValid(row-2, col+1, isWhitePlaying);
        addIfValid(row-1, col+2, isWhitePlaying);
        addIfValid(row+1, col+2, isWhitePlaying);
        addIfValid(row+2, col+1, isWhitePlaying);
        addIfValid(row-2, col-1, isWhitePlaying);
        addIfValid(row-1, col-2, isWhitePlaying);
        addIfValid(row+1, col-2, isWhitePlaying);
        addIfValid(row+2, col-1, isWhitePlaying);

        //Returns List of moves (coordinates of squares piece can move to)
        return possibleMoves;
    }
}
