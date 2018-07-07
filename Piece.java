import java.util.ArrayList;

public abstract class Piece {
    protected Logic_Board logic_board = Logic_Board.getInstance();
    protected ArrayList<String> possibleMoves = new ArrayList<String>();
    protected abstract ArrayList<String> getPossibleMoves(String originalcoord, boolean isWhitePlaying);
    protected void addIfValid(int row, int col, boolean isWhitePlaying){
        try {
            if(isWhitePlaying) {
                if (logic_board.boardLogic[row - 1][col] == "" || Character.isLowerCase(logic_board.boardLogic[row - 1][col].charAt(0))) {
                    String move = Integer.toString(row) + Integer.toString(col);
                    possibleMoves.add(move);
                }
            }
            else if(!isWhitePlaying){
                if (logic_board.boardLogic[row - 1][col] == "" || Character.isUpperCase(logic_board.boardLogic[row - 1][col].charAt(0))) {
                    String move = Integer.toString(row) + Integer.toString(col);
                    possibleMoves.add(move);
                }
            }
        }catch (Exception e){
            System.out.println("oof");
        }
    }
}
