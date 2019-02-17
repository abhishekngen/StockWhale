import java.util.ArrayList;

public abstract class Piece {
    protected Logic_Board logic_board = Logic_Board.getInstance(); //Used so that each piece type class has an inherited reference to the Logic_Board class

    protected ArrayList<String> possibleMoves = new ArrayList<String>(); //Used so that each piece type class an inherited possibleMoves List, which is used to hold all the possible moves the certain piece can do

    protected abstract ArrayList<String> getPossibleMoves(String originalcoord, boolean isWhitePlaying); //Abstract function inherited by each piece type class that is used to generate and return a List containing all the possible moves the certain piece can do. As it is abstract, it is DEFINED INDIVIDUALLY BY EACH CLASS

    //Inherited method used by piece type classes to perform basic validations on moves that are added to the List by each individually defined getPossibleMoves() function
    protected void addIfValid(int row, int col, boolean isWhitePlaying){
        try {

            //If piece is white, checks if square being moved to for move being tested is empty or contains a piece of the opposing side - if so, the move is valid and added to possibleMoves list
            if(isWhitePlaying) {
                if (logic_board.logicBoard[row - 1][col] == "" || Character.isLowerCase(logic_board.logicBoard[row - 1][col].charAt(0))) {
                    String move = Integer.toString(row) + Integer.toString(col); //Move held as coordinates containing concatenated string of row and column

                    possibleMoves.add(move);
                }
            }

            //If piece is black, checks if square being moved to for move being tested is empty or contains a piece of the opposing side - if so, the move is valid and added to possibleMoves list
            else if(!isWhitePlaying){
                if (logic_board.logicBoard[row - 1][col] == "" || Character.isUpperCase(logic_board.logicBoard[row - 1][col].charAt(0))) {
                    String move = Integer.toString(row) + Integer.toString(col); //Move held as coordinates containing concatenated string of row and column

                    possibleMoves.add(move);
                }
            }
        }catch (Exception e){

        }
    }
}
