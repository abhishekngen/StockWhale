

import java.util.ArrayList;

public class Pawn extends Piece{

    //Implemented getPossibleMoves function
    @Override
    public ArrayList<String> getPossibleMoves(String originalcoord, boolean isWhitePlaying) {
        possibleMoves.clear();

        //Extracts row and column values from coordinates of piece position
        int row = Character.getNumericValue(originalcoord.charAt(1));
        int col = Character.getNumericValue(originalcoord.charAt(0));

        int addValue; //Used to generate moves in forward direction of pawn, which is either up or down the board dependent on whether pawn is white or black

        //Adds move of pawn moving forward two spaces ONLY if piece is in its initial row
        if(isWhitePlaying) {
            if (row == 7) {

                //Checks if square in front of pawn and square two spaces in front of pawn is empty as required before adding move
                if (logic_board.logicBoard[row - 3][col] == "" && logic_board.logicBoard[row-2][col] == "") {
                    addIfValid(row - 2, col, isWhitePlaying);
                }
            }

            //As pawn is white it moves up the board, where the row value is decreased by 1 on each move, hence addValue set to -1
            addValue = -1;
        }
        else{
            if(row==2){

                //Checks if square in front of pawn and square two spaces in front of pawn is empty as required before adding move
                if (logic_board.logicBoard[row + 1][col] == "" && logic_board.logicBoard[row][col] == "") {
                    addIfValid(row + 2, col, isWhitePlaying);
                }
            }

            //As pawn is black it moves down the board, where the row value is increased by 1 on each move, hence addValue set to +1
            addValue = 1;
        }

        //Checks if row of pawn after pawn moves forward one space, where that row is calculated by adding addValue to the current row of pawn, is within GridPane
        if(row + addValue < 9 && row + addValue > 0) {

            //Checks if square directly in front and adjacent to pawn is empty, and if it is it adds it as a valid move
            if (logic_board.logicBoard[row + addValue - 1][col].equals("")) {
                addIfValid(row + addValue, col, isWhitePlaying);
            }

            //Checks if square in front and diagonally to the right is within GridPane and contains a piece of opposing side, and if it does it is a valid capture move and is added
            if(col + 1 < 8) {
                if (!(logic_board.logicBoard[row + addValue - 1][col + 1].equals(""))) {
                    addIfValid(row + addValue, col + 1, isWhitePlaying);
                }
            }

            //Checks if square in front and diagonally to the left is within GridPane and contains a piece of opposing side, and if it does it is a valid capture move and is added
            if(col - 1 > -1) {
                if (!(logic_board.logicBoard[row + addValue - 1][col - 1].equals(""))) {
                    addIfValid(row + addValue, col - 1, isWhitePlaying);
                }
            }
        }

        //Returns List of moves (coordinates of squares piece can move to)
        return possibleMoves;
    }
}
