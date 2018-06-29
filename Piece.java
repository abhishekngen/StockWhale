import java.util.ArrayList;

public abstract class Piece {
    Logic_Board logic_board = Logic_Board.getInstance();
    public ArrayList<String> possibleMoves = new ArrayList<String>();
    public abstract ArrayList<String> getPossibleWhiteMoves(String originalcoord);
    public abstract ArrayList<String> getPossibleBlackMoves(String originalcoord);

}
