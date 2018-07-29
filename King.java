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
        if(Main_Display.gui.hasKing1Moved == false && isWhitePlaying && Main_Display.gui.hasRook1Moved == false && logic_board.boardLogic[7][0] == "R1" && !logic_board.isKingInCheck(isWhitePlaying)){
            if(logic_board.boardLogic[row-1][col-1] == "" && logic_board.boardLogic[row-1][col-2] == ""){
                logic_board.tryMove("K", Integer.toString(row-1)+Integer.toString(col-1));
                if(!logic_board.isKingInCheck(isWhitePlaying)){
                    logic_board.revertLogicBoard();
                    logic_board.tryMove("K", Integer.toString(row-1)+Integer.toString(col-2));
                    if(!logic_board.isKingInCheck(isWhitePlaying)){
                        logic_board.revertLogicBoard();
                        addIfValid(row, col-2, isWhitePlaying);
                    }
                }
            }
        }
        if(Main_Display.gui.hasKing1Moved == false && isWhitePlaying && Main_Display.gui.hasRook2Moved == false && logic_board.boardLogic[7][7] == "R2" && !logic_board.isKingInCheck(isWhitePlaying)){
            if(logic_board.boardLogic[row-1][col+1] == "" && logic_board.boardLogic[row-1][col+2] == ""){
                logic_board.tryMove("K", Integer.toString(row-1)+Integer.toString(col+1));
                if(!logic_board.isKingInCheck(isWhitePlaying)){
                    logic_board.revertLogicBoard();
                    logic_board.tryMove("K", Integer.toString(row-1)+Integer.toString(col+2));
                    if(!logic_board.isKingInCheck(isWhitePlaying)){
                        logic_board.revertLogicBoard();
                        addIfValid(row, col+2, isWhitePlaying);
                    }
                }
            }
        }
        if(Main_Display.gui.hasKing2Moved == false && !isWhitePlaying && Main_Display.gui.hasRook3Moved == false && logic_board.boardLogic[0][0] == "r1" && !logic_board.isKingInCheck(isWhitePlaying)){
            if(logic_board.boardLogic[row-1][col-1] == "" && logic_board.boardLogic[row-1][col-2] == ""){
                logic_board.tryMove("k", Integer.toString(row-1)+Integer.toString(col-1));
                if(!logic_board.isKingInCheck(isWhitePlaying)){
                    logic_board.revertLogicBoard();
                    logic_board.tryMove("k", Integer.toString(row-1)+Integer.toString(col-2));
                    if(!logic_board.isKingInCheck(isWhitePlaying)){
                        logic_board.revertLogicBoard();
                        addIfValid(row, col-2, isWhitePlaying);
                    }
                }
            }
        }
        if(Main_Display.gui.hasKing2Moved == false && !isWhitePlaying && Main_Display.gui.hasRook4Moved == false && logic_board.boardLogic[0][7] == "r2" && !logic_board.isKingInCheck(isWhitePlaying)){
            if(logic_board.boardLogic[row-1][col+1] == "" && logic_board.boardLogic[row-1][col+2] == ""){
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
        return possibleMoves;
    }
}
