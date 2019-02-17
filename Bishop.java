import java.util.ArrayList;

public class Bishop extends Piece {

    //Implemented getPossibleMoves function
    @Override
    public ArrayList<String> getPossibleMoves(String originalcoord, boolean isWhitePlaying) {
        possibleMoves.clear();

        //Extracts row and column values from coordinates of piece position
        int row = Character.getNumericValue(originalcoord.charAt(1));
        int col = Character.getNumericValue(originalcoord.charAt(0));


        boolean enemyPiecePresent = false;
        int testRow = row;
        int testCol = col;

        //Iterates through 4 possible lines of movement of bishop piece - diagonally upwards to the right and left and diagonally downwards to right and left
        //Value of i determines which line of movement is being scanned
        for(int i = 0; i<4; i++) {

            //Iterates through line of movement until piece is detected
            while (!enemyPiecePresent) {

                //Different direction vectors for line of movement
                if (i == 0) {
                    testCol += 1;
                    testRow += 1;
                } else if (i == 1) {
                    testCol += 1;
                    testRow -= 1;
                } else if (i == 2) {
                    testCol -= 1;
                    testRow += 1;
                } else {
                    testCol -= 1;
                    testRow -= 1;
                }

                //Checks if row and column of square being checked is within GridPane, as coordinates returned are in GridPane format
                if (testRow > 0 && testRow < 9 && testCol > -1 && testCol < 8) {

                    //If square is not empty and contains a piece
                    if (!(logic_board.logicBoard[testRow - 1][testCol].equals(""))) {

                        //Set true to break out of while loop and onto next iteration of for loop to scan next line of movement
                        enemyPiecePresent = true;
                    }

                    //Adds coordinates of square as possible move (provided addIfValid base validation is passed)
                    addIfValid(testRow, testCol, isWhitePlaying);
                }

                //If row and column not within GridPane, sets enemyPiecePresent true to break out of while loop and move to next for loop iteration to scan next line of movement
                else {
                    enemyPiecePresent = true;
                }
            }

            //Resets column and row values to original column and row values to scan next line of movement, and resets boolean
            enemyPiecePresent = false;
            testCol = col;
            testRow = row;
        }

        //Returns List of moves (coordinates of squares piece can move to)
        return possibleMoves;
    }
}
