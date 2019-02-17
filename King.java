import java.util.ArrayList;

public class King extends Piece {

    //Implemented getPossibleMoves function
    @Override
    public ArrayList<String> getPossibleMoves(String originalcoord, boolean isWhitePlaying) {
        possibleMoves.clear();

        //Extracts row and column values from coordinates of piece position
        int row = Character.getNumericValue(originalcoord.charAt(1));
        int col = Character.getNumericValue(originalcoord.charAt(0));

        //Adds each square adjacent to King piece's position provided square is unoccupied or occupied by piece of opposing side as those are the only validations required, which are performed by addIfValid()
        addIfValid(row+1, col, isWhitePlaying);
        addIfValid(row+1, col+1, isWhitePlaying);
        addIfValid(row, col+1, isWhitePlaying);
        addIfValid(row-1, col+1, isWhitePlaying);
        addIfValid(row-1, col, isWhitePlaying);
        addIfValid(row-1, col-1, isWhitePlaying);
        addIfValid(row, col-1, isWhitePlaying);
        addIfValid(row+1, col-1, isWhitePlaying);

        //Generates all possible castling moves that can be performed in current board state

        if(Main_Display.gui.hasKing1Moved == false && isWhitePlaying && Main_Display.gui.hasRook1Moved == false && logic_board.logicBoard[7][0] == "R1" && !logic_board.isKingInCheck(isWhitePlaying) && row == 8){

            //Checks if squares that will be moved through by king piece are empty, as required for castling
            if(logic_board.logicBoard[row-1][col-1] == "" && logic_board.logicBoard[row-1][col-2] == ""){

                //Tries moving white king piece (king1) to first square piece must move through
                logic_board.tryMove("K", Integer.toString(row-1)+Integer.toString(col-1));

                //If king piece is not in check in that square, as required for castling:
                if(!logic_board.isKingInCheck(isWhitePlaying)){

                    //Move is reverted
                    logic_board.revertLogicBoard();

                    //Tries moving king piece to second square king must move through
                    logic_board.tryMove("K", Integer.toString(row-1)+Integer.toString(col-2));

                    //If king piece is not in check in that square as required:
                    if(!logic_board.isKingInCheck(isWhitePlaying)){

                        //Move is reverted
                        logic_board.revertLogicBoard();

                        //Move is added as it fully complies with castling requirements
                        addIfValid(row, col-2, isWhitePlaying);
                    }
                    else{
                        logic_board.revertLogicBoard();
                    }
                }
                else{
                    logic_board.revertLogicBoard();
                }
            }
        }
        if(Main_Display.gui.hasKing1Moved == false && isWhitePlaying && Main_Display.gui.hasRook2Moved == false && logic_board.logicBoard[7][7] == "R2" && !logic_board.isKingInCheck(isWhitePlaying) && row == 8){

            if(logic_board.logicBoard[row-1][col+1] == "" && logic_board.logicBoard[row-1][col+2] == ""){
                logic_board.tryMove("K", Integer.toString(row-1)+Integer.toString(col+1));

                if(!logic_board.isKingInCheck(isWhitePlaying)){
                    logic_board.revertLogicBoard();
                    logic_board.tryMove("K", Integer.toString(row-1)+Integer.toString(col+2));

                    if(!logic_board.isKingInCheck(isWhitePlaying)){
                        logic_board.revertLogicBoard();
                        addIfValid(row, col+2, isWhitePlaying);
                    }
                    else{
                        logic_board.revertLogicBoard();
                    }
                }
                else {
                    logic_board.revertLogicBoard();
                }
            }
        }
        if(Main_Display.gui.hasKing2Moved == false && !isWhitePlaying && Main_Display.gui.hasRook3Moved == false && logic_board.logicBoard[0][0] == "r1" && !logic_board.isKingInCheck(isWhitePlaying) && row == 1){

            if(logic_board.logicBoard[row-1][col-1] == "" && logic_board.logicBoard[row-1][col-2] == ""){
                logic_board.tryMove("k", Integer.toString(row-1)+Integer.toString(col-1));

                if(!logic_board.isKingInCheck(isWhitePlaying)){
                    logic_board.revertLogicBoard();
                    logic_board.tryMove("k", Integer.toString(row-1)+Integer.toString(col-2));

                    if(!logic_board.isKingInCheck(isWhitePlaying)){
                        logic_board.revertLogicBoard();
                        addIfValid(row, col-2, isWhitePlaying);
                    }
                    else{
                        logic_board.revertLogicBoard();
                    }
                }
                else{
                    logic_board.revertLogicBoard();
                }
            }
        }
        if(Main_Display.gui.hasKing2Moved == false && !isWhitePlaying && Main_Display.gui.hasRook4Moved == false && logic_board.logicBoard[0][7] == "r2" && !logic_board.isKingInCheck(isWhitePlaying) && row == 1){

            if(logic_board.logicBoard[row-1][col+1] == "" && logic_board.logicBoard[row-1][col+2] == ""){
                logic_board.tryMove("k", Integer.toString(row-1)+Integer.toString(col+1));

                if(!logic_board.isKingInCheck(isWhitePlaying)){
                    logic_board.revertLogicBoard();
                    logic_board.tryMove("k", Integer.toString(row-1)+Integer.toString(col+2));

                    if(!logic_board.isKingInCheck(isWhitePlaying)){
                        logic_board.revertLogicBoard();
                        addIfValid(row, col+2, isWhitePlaying);
                    }
                    else{
                        logic_board.revertLogicBoard();
                    }
                }
                else{
                    logic_board.revertLogicBoard();
                }
            }
        }

        //Returns List of moves (coordinates of squares piece can move to)
        return possibleMoves;
    }
}
