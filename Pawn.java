

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
    public ArrayList<String> getPossibleMoves(String originalcoord, boolean isWhitePlaying) {
        possibleMoves.clear();
        int row = Character.getNumericValue(originalcoord.charAt(1));
        int col = Character.getNumericValue(originalcoord.charAt(0));
        int addvalue;
        if(isWhitePlaying) {
            if (row == 7) {
                if (logic_board.boardLogic[row - 3][col] == "") {
                    addIfValid(row - 2, col, isWhitePlaying);
                }
            }
            addvalue = -1;
        }
        else{
            if(row==2){
                if (logic_board.boardLogic[row + 1][col] == "") {
                    addIfValid(row + 2, col, isWhitePlaying);
                }
            }
            addvalue = 1;
        }
        if(row + addvalue < 9 && row + addvalue > 0) {
            if (logic_board.boardLogic[row + addvalue - 1][col].equals("")) {
                addIfValid(row + addvalue, col, isWhitePlaying);
            }
            if(col + 1 < 8) {
                if (!(logic_board.boardLogic[row + addvalue - 1][col + 1].equals(""))) {
                    addIfValid(row + addvalue, col + 1, isWhitePlaying);
                }
            }
            if(col - 1 > -1) {
                if (!(logic_board.boardLogic[row + addvalue - 1][col - 1].equals(""))) {
                    addIfValid(row + addvalue, col - 1, isWhitePlaying);
                }
            }
        }
        /*
        if(isWhitePlaying) {
            if (row == 7) {
                if (logic_board.boardLogic[row - 3][col] == "") {
                    addIfValid(row - 2, col, isWhitePlaying);
                }
            }
            if (row - 1 > 0 && col + 1 < 8) {
                if (logic_board.boardLogic[row - 2][col + 1] != "") {
                    if (Character.isLowerCase(logic_board.boardLogic[row - 2][col + 1].charAt(0))) {
                        addIfValid(row - 1, col + 1, isWhitePlaying);
                    }
                }
            }
            if (row - 1 > 0 && col - 1 >= 0) {
                if (logic_board.boardLogic[row - 2][col - 1] != "") {
                    if (Character.isLowerCase(logic_board.boardLogic[row - 2][col - 1].charAt(0))) {
                        addIfValid(row - 1, col - 1, isWhitePlaying);
                    }
                }
            }
            if (row - 1>0 && logic_board.boardLogic[row - 2][col] == "") {
                addIfValid(row - 1, col, isWhitePlaying);
            }
        }
        else{
            if(row==2){
                if (logic_board.boardLogic[row + 1][col] == "") {
                    addIfValid(row + 2, col, isWhitePlaying);
                }
            }
            if (row+1<9 && logic_board.boardLogic[row][col] == "") {
                addIfValid(row + 1, col, isWhitePlaying);
            }
            if (row+1<9 && col + 1 < 8) {
                if (logic_board.boardLogic[row][col + 1] != "") {
                    if (Character.isUpperCase(logic_board.boardLogic[row][col + 1].charAt(0))) {
                        addIfValid(row+1, col + 1, isWhitePlaying);
                    }
                }
            }
            if (row + 1<9 && col - 1 >= 0) {
                if (logic_board.boardLogic[row][col - 1] != "") {
                    if (Character.isUpperCase(logic_board.boardLogic[row][col - 1].charAt(0))) {
                        addIfValid(row + 1, col - 1, isWhitePlaying);
                    }
                }
            }
        }*/
        return possibleMoves;
    }
}
