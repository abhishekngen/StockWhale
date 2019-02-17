import java.util.ArrayList;
import java.util.Arrays;

public class Logic_Board {

    //Private constructor to prevent producing object instances apart from singleton instance
    private Logic_Board() {}

    private static Logic_Board instance = null; //Static instance of class that is the singleton instance

    //Private Local Variables (used throughout class)
    private String[][] savedBoardLogic = new String[8][8];
    private Pawn pawn;
    private Rook rook;
    private Knight knight;
    private Bishop bishop;
    private Queen queen;
    private King king;
    private AI ai;

    //Singleton getInstance() function to return singleton instance of class
    public static Logic_Board getInstance() {
        if (instance == null) {
            instance = new Logic_Board(); //Instantiates class as object only once
        }
        return instance;
    }

    //Instantiates all object references
    public void init() {
        ai = AI.getInstance();
        pawn = new Pawn();
        rook = new Rook();
        knight = new Knight();
        bishop = new Bishop();
        queen = new Queen();
        king = new King();
    }

    //Two-dimensional string array containing logical storage of board state of chessboard
    public String[][] logicBoard = new String[][]
            {
                    //Starting board state array values
                    {"r1", "n1", "b1", "q", "k", "b2", "n2", "r2"},
                    {"p1", "p2", "p3", "p4", "p5", "p6", "p7", "p8"},
                    {"", "", "", "", "", "", "", ""},
                    {"", "", "", "", "", "", "", ""},
                    {"", "", "", "", "", "", "", ""},
                    {"", "", "", "", "", "", "", ""},
                    {"P1", "P2", "P3", "P4", "P5", "P6", "P7", "P8"},
                    {"R1", "N1", "B1", "Q", "K", "B2", "N2", "R2"}
            };

    public void makeMove(String piece, String coord, boolean isWhitePlaying) {
        String pieceRemove = "";

        //Gets row and column values of square piece is moving to
        int row = Character.getNumericValue(coord.charAt(0));
        int col = Character.getNumericValue(coord.charAt(1));

        //Iterates through board array to find piece being moved, and then removes it from the array
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                if (logicBoard[r][c] == piece) {
                    logicBoard[r][c] = "";
                }
            }
        }

        //If square being moved to is not empty, copy the piece string value that is there
        if (logicBoard[row][col] != "") {
            pieceRemove = logicBoard[row][col];
        }

        //Place piece in square it is moving to (which also removes any piece that was there)
        logicBoard[row][col] = piece;

        //Checks if move is a castle by checking if piece being moved is a king piece in its initial position and the square being moved to is a square that would be moved to in a castling move. Also checks if corresponding rook dependent on the king and the square it is moving to is in its initial position, and if neither piece has moved and a castle has not yet occurred. It then also moves the rook piece (as the king has already been moved).
        if(piece.equals("k") && row == 0 && col == 2 && !Main_Display.gui.hasBlackCastled && !Main_Display.gui.hasKing2Moved && !Main_Display.gui.hasRook3Moved){
            tryMove("r1", "03"); //Moves rook piece
            //Updates rook movement on GUI GridPane
            Main_Display.gui.boardGui.getChildren().remove(Main_Display.gui.getKey("r1"));
            Main_Display.gui.boardGui.add(Main_Display.gui.getKey("r1"), 3, 1);
            Main_Display.gui.hasBlackCastled = true; //Toggles appropriate boolean to true
        }
        if(piece.equals("k") && row == 0 && col == 6 && !Main_Display.gui.hasBlackCastled && !Main_Display.gui.hasKing2Moved && !Main_Display.gui.hasRook4Moved){
            tryMove("r2", "05");
            Main_Display.gui.boardGui.getChildren().remove(Main_Display.gui.getKey("r2"));
            Main_Display.gui.boardGui.add(Main_Display.gui.getKey("r2"), 5, 1);
            Main_Display.gui.hasBlackCastled = true;
        }
        if(piece.equals("K") && row == 7 && col == 2 && !Main_Display.gui.hasWhiteCastled && !Main_Display.gui.hasKing1Moved && !Main_Display.gui.hasRook1Moved){
            tryMove("R1", "73");
            Main_Display.gui.boardGui.getChildren().remove(Main_Display.gui.getKey("R1"));
            Main_Display.gui.boardGui.add(Main_Display.gui.getKey("R1"), 3, 8);
            Main_Display.gui.hasWhiteCastled = true;
        }
        if(piece.equals("K") && row == 7 && col == 6 && !Main_Display.gui.hasWhiteCastled && !Main_Display.gui.hasKing1Moved && !Main_Display.gui.hasRook2Moved){
            tryMove("R2", "75");
            Main_Display.gui.boardGui.getChildren().remove(Main_Display.gui.getKey("R2"));
            Main_Display.gui.boardGui.add(Main_Display.gui.getKey("R2"), 5, 8);
            Main_Display.gui.hasWhiteCastled = true;
        }

        //Toggles appropriate booleans regarding whether a certain king or rook has moved yet
        if(piece.equals("R1")){
            Main_Display.gui.hasRook1Moved = true;
        }
        if(piece.equals("R2")){
            Main_Display.gui.hasRook2Moved = true;
        }
        if(piece.equals("r1")){
            Main_Display.gui.hasRook3Moved = true;
        }
        if(piece.equals("r2")){
            Main_Display.gui.hasRook4Moved = true;
        }
        if(piece.equals("K")){
            Main_Display.gui.hasKing1Moved = true;
        }
        if(piece.equals("k")){
            Main_Display.gui.hasKing2Moved = true;
        }

        //If a piece was in the square the piece was moving to (a capture) that piece is removed from GUI GridPane
        Main_Display.gui.boardGui.getChildren().remove(Main_Display.gui.getKey(pieceRemove));

        //Calls makeMove() of Main_Controller
        Main_Display.gui.makeMove(piece, coord);
    }

    //Gets position of a piece as coordinates in the logical board array
    public String getPos(String piece) {
        String coord = null;
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (logicBoard[row][col] == piece) {

                    //If piece is found at certain position in board array, concatenates coordinates of row and column of piece into string
                    coord = Integer.toString(row) + Integer.toString(col);

                    break;
                }
            }
        }
        return coord;
    }

    public void revertLogicBoard() {
        for (int i = 0; i < 8; i++) {
            logicBoard[i] = Arrays.copyOf(savedBoardLogic[i], 8);
        }
    }

    //Used to move a piece on the logical board array only - mainly used for "testing" moves by AI
    public void tryMove(String piece, String coord) {

        //Saves current board state to another array to allow move tried to be reverted
        for (int i = 0; i < 8; i++) {
            savedBoardLogic[i] = Arrays.copyOf(logicBoard[i], 8);
        }

        //Gets row and column of coordinates piece is being moved
        int row = Character.getNumericValue(coord.charAt(0));
        int col = Character.getNumericValue(coord.charAt(1));

        //Removes piece from logical board array
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                if (logicBoard[r][c] == piece) {
                    logicBoard[r][c] = "";
                    break;
                }
            }
        }

        //Places piece in square piece is moving to
        logicBoard[row][col] = piece;
    }

    public boolean isKingInCheck(boolean isWhitePlaying) {
        char Pawn;
        char Knight;
        char Bishop;
        char Queen;
        char King;
        char Rook;
        String coord;

        //Uses appropriate string identifiers dependent on side that is playing
        if(isWhitePlaying){
            Pawn = 'p';
            Knight = 'n';
            Bishop = 'b';
            Queen = 'q';
            King = 'k';
            Rook = 'r';
            coord = getPos("K"); //Gets and stores position of king piece for that side
        }
        else{
            Pawn = 'P';
            Knight = 'N';
            Bishop = 'B';
            Queen = 'Q';
            King = 'K';
            Rook = 'R';
            coord = getPos("k"); //Gets and stores position of king piece for that side
        }

        //Gets row and column values of king piece coordinates and copies them to test variables that can be changed while still retaining original row and column values
        int row = Character.getNumericValue(coord.charAt(0));
        int col = Character.getNumericValue(coord.charAt(1));
        int testRow = row;
        int testCol = col;

        //Iterates and scans through each horizontal and vertical line of movement originating from king's position (to the right, left, up and down), and checks if a queen or rook piece is present which is targeting king
        for (int dir = 1; dir < 5; dir++) { //Iterates through 4 lines of movement

            //Resets column and row values to original king position on each iteration
            testRow = row;
            testCol = col;

            for (int i = 0; i < 7; i++) { //Iterates maximum through all the squares in that line

                //Dependent on direction of current line of movement being scanned, changes row or column value of square to be checked that conforms with the direction vector
                if (dir == 1) {
                    testRow += 1;
                } else if (dir == 2) {
                    testRow -= 1;
                } else if (dir == 3) {
                    testCol += 1;
                } else if (dir == 4) {
                    testCol -= 1;
                }

                //Checks if square is in board array, and if square is not empty and containing a rook or queen piece
                if (testRow >= 0 && testRow < 8 && testCol >= 0 && testCol < 8) {
                    if (logicBoard[testRow][testCol] != "") {
                        if (logicBoard[testRow][testCol].charAt(0) == Rook || logicBoard[testRow][testCol].charAt(0) == Queen) {
                            return true; //If square has a rook or queen, return true to show king is in check (also exits function)
                        } else {
                            i = 8; //If square contains a piece but is not a rook or queen, stop scanning that line of movement - move on to next line of movement and thus the next iteration by setting i to 8

                        }
                    }
                } else {
                    i = 8; //If square being checked is not in board array, move on to next line of movement as there will be no more squares in that line which are on the board

                }
            }
        }

        //Resets row and column values to original king position
        testRow = row;
        testCol = col;

        //Performs the same task again but this time iterating and scanning through all the diagonal lines of movement originating from the king's position, and checking if a queen or bishop piece is present and targeting king
        for (int dir = 1; dir < 5; dir++) {
            for (int i = 0; i < 7; i++) {
                if (dir == 1) {
                    testRow += 1;
                    testCol += 1;
                } else if (dir == 2) {
                    testRow += 1;
                    testCol -= 1;
                } else if (dir == 3) {
                    testRow -= 1;
                    testCol -= 1;
                } else if (dir == 4) {
                    testRow -= 1;
                    testCol += 1;
                }
                if (testRow >= 0 && testRow < 8 && testCol >= 0 && testCol < 8) {
                    if (logicBoard[testRow][testCol] != "") {
                        if (logicBoard[testRow][testCol].charAt(0) == Bishop || logicBoard[testRow][testCol].charAt(0) == Queen) {
                            return true;
                        } else {
                            i = 8;
                        }
                    }
                } else {
                    i = 8;
                }
                if (i >= 7) {
                    testRow = row;
                    testCol = col;
                }
            }

        }

        //Checks if a pawn is targeting king by checking two squares diagonally adjacent to king, where either the two squares in front of the king or behind the king are checked dependent on side playing
        //If white is playing, two adjacent squares diagonally above king are checked, where black is on the top of the board - and if black is playing. two adjacent squares diagonally below king are checked
        if(isWhitePlaying) {
            if (row - 1 >= 0) {
                if (col + 1 < 8) {
                    if (logicBoard[row - 1][col + 1] != "") {
                        if (logicBoard[row - 1][col + 1].charAt(0) == Pawn) {
                            return true;
                        }
                    }
                }
                if (col - 1 >= 0) {
                    if (logicBoard[row - 1][col - 1] != "") {
                        if (logicBoard[row - 1][col - 1].charAt(0) == Pawn) {
                            return true;
                        }
                    }
                }
            }
        }
        else{
            if (row + 1 < 8) {
                if (col + 1 < 8) {
                    if (logicBoard[row + 1][col + 1] != "") {
                        if (logicBoard[row + 1][col + 1].charAt(0) == Pawn) {
                            return true;
                        }
                    }
                }
                if (col - 1 >= 0) {
                    if (logicBoard[row + 1][col - 1] != "") {
                        if (logicBoard[row + 1][col - 1].charAt(0) == Pawn) {
                            return true;
                        }
                    }
                }
            }
        }

        //Checks all the squares a knight piece could be in that could be threatening the king piece, and if a knight is present in one of them true is returned
        if (row - 2 >= 0 && col + 1 < 8) {
            if (logicBoard[row - 2][col + 1] != "") {
                if (logicBoard[row - 2][col + 1].charAt(0) == Knight) {
                    return true;
                }
            }
        }
        if (row - 1 >= 0 && col + 2 < 8) {
            if (logicBoard[row - 1][col + 2] != "") {
                if (logicBoard[row - 1][col + 2].charAt(0) == Knight) {
                    return true;
                }
            }
        }
        if (row - 2 >= 0 && col - 1 >= 0) {
            if (logicBoard[row - 2][col - 1] != "") {
                if (logicBoard[row - 2][col - 1].charAt(0) == Knight) {
                    return true;
                }
            }
        }
        if (row - 1 >= 0 && col - 2 >= 0) {
            if (logicBoard[row - 1][col - 2] != "") {
                if (logicBoard[row - 1][col - 2].charAt(0) == Knight) {
                    return true;
                }
            }
        }
        if (row + 1 < 8 && col + 2 < 8) {
            if (logicBoard[row + 1][col + 2] != "") {
                if (logicBoard[row + 1][col + 2].charAt(0) == Knight) {
                    return true;
                }
            }
        }
        if (row + 2 < 8 && col + 1 < 8) {
            if (logicBoard[row + 2][col + 1] != "") {
                if (logicBoard[row + 2][col + 1].charAt(0) == Knight) {
                    return true;
                }
            }
        }
        if (row + 2 < 8 && col - 1 >= 0) {
            if (logicBoard[row + 2][col - 1] != "") {
                if (logicBoard[row + 2][col - 1].charAt(0) == Knight) {
                    return true;
                }
            }
        }
        if(row+1<8 && col-2>=0){
            if(logicBoard[row+1][col-2] != ""){
                if(logicBoard[row+1][col-2].charAt(0) == Knight){
                    return true;
                }
            }
        }

        //Checks all the squares a king of the opposing side could be in which would threaten the king - which is all the adjacent squares to the king - and if a king is detected true is returned
        if (row - 1 >= 0) {
            if (!logicBoard[row - 1][col].equals("")) {
                if (logicBoard[row - 1][col].charAt(0) == King) {
                    return true;
                }
            }
        }
        if (row - 1 >= 0 && col + 1 < 8) {
            if (!logicBoard[row - 1][col + 1].equals("")) {
                if (logicBoard[row - 1][col + 1].charAt(0) == King) {
                    return true;
                }
            }
        }
        if (col + 1 < 8) {
            if (!logicBoard[row][col + 1].equals("")) {
                if (logicBoard[row][col + 1].charAt(0) == King) {
                    return true;
                }
            }
        }
        if (row + 1 < 8 && col + 1 < 8) {
            if (!logicBoard[row + 1][col + 1].equals("")) {
                if (logicBoard[row + 1][col + 1].charAt(0) == King) {
                    return true;
                }
            }
        }
        if (row + 1 < 8) {
            if (!logicBoard[row + 1][col].equals("")) {
                if (logicBoard[row + 1][col].charAt(0) == King) {
                    return true;
                }
            }
        }
        if (row + 1 < 8 && col - 1 >= 0) {
            if (!logicBoard[row + 1][col - 1].equals("")) {
                if (logicBoard[row + 1][col - 1].charAt(0) == King) {
                    return true;
                }
            }
        }
        if (col - 1 >= 0) {
            if (!logicBoard[row][col - 1].equals("")) {
                if (logicBoard[row][col - 1].charAt(0) == King) {
                    return true;
                }
            }
        }
        if (row - 1 >= 0 && col - 1 >= 0) {
            if (!logicBoard[row - 1][col - 1].equals("")) {
                if (logicBoard[row - 1][col - 1].charAt(0) == King) {
                    return true;
                }
            }
        }

        return false; //Returns false if no threatening piece found
    }

    public boolean isKingInCheckMate(boolean isWhitePlaying) {
        ai.getAllPossibleMoves(!isWhitePlaying); //Gets all the valid legal moves the opposing side can do
        
        if(!ai.anyValidMoves()){
            return true; //If there are no valid legal moves opposing side can do, true is returned
        }
        return false;
    }
    
    //Converts coordinates in terms of GUI GridPane to coordinates in terms of logical board array or vice versa dependent on argument
    public String convertCoord(String coord, boolean guiToLogic) {
        
        //Gets row and column value of coordinates
        int row = Character.getNumericValue(coord.charAt(0));
        int col = Character.getNumericValue(coord.charAt(1));
        
        //If value of parameter guiToLogic is true, subtracts the value of the row by 1 to convert to logical array coordinates, otherwise increments by 1
        if (guiToLogic) {
            row--;
        } else {
            row++;
        }

        //Concatenates values of row and column back into a string and returns it
        coord = Integer.toString(row) + Integer.toString(col);
        return coord;
    }

    public String reverse(String coord) {
        StringBuilder sb = new StringBuilder(coord);
        return (sb.reverse()).toString();
    }

    //Heuristic Evaluation Function
    public double evaluateBoard() {
        //Higher the value returned, more advantageous white is, lower the value, more advantageous black is

        double value = 0; //Contains evaluation value
        int sideMultiplier;
        double mobility;
        boolean isWhite;
        String difficulty = ai.getDifficulty();

        //Checkmate is ultimate goal of both sides hence value is +/-100 - a very high value
        if(isKingInCheckMate(true)){
            value = 100;
        }
        else if(isKingInCheckMate(false)){
            value = -100;
        }

        //Iterates through board array and all squares on chess board
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (logicBoard[row][col] != "") {
                    String piece = logicBoard[row][col];

                    //Sets boolean isWhite according to if piece is white or black, and sets sideMultiplier to -1 if black or 1 if white, where the sideMultiplier is multiplied with evaluation values produced to make them either negative or positive to reflect which side is benefiting from that certain analysis
                    if (Character.isLowerCase(piece.charAt(0))) {
                        sideMultiplier = -1;
                        isWhite = false;
                    } else {
                        sideMultiplier = 1;
                        isWhite = true;
                    }

                    //Adds on weight value of piece - 1 for pawn, 3 for bishop/knight, 5 for rook, 9 for queen - multiplied by the sideMultiplier to the value variable holding the total evaluation
                    //Also adds on the mobility value of the piece to the total evaluation, obtained by the total number of valid moves the piece can do in the current board state is multiplied by the sideMultiplier and then multiplied by a constant according to what piece it is
                    if (Character.toLowerCase(piece.charAt(0)) == 'p') {
                        value += sideMultiplier;
                        if(difficulty == "Medium" || difficulty == "Hard") { //Mobility value only added if medium or hard difficulty

                            mobility = filterMoves(piece, pawn.getPossibleMoves(Integer.toString(col) + Integer.toString(row + 1), isWhite), isWhite).size() * 0.1 * sideMultiplier;
                            value += mobility;
                        }
                    }
                    if (Character.toLowerCase(piece.charAt(0)) == 'n' || Character.toLowerCase(piece.charAt(0)) == 'b') {
                        value += 3 * sideMultiplier;
                        if(difficulty == "Medium" || difficulty == "Hard") {
                            if (Character.toLowerCase(piece.charAt(0)) == 'n') {
                                mobility = filterMoves(piece, knight.getPossibleMoves(Integer.toString(col) + Integer.toString(row + 1), isWhite), isWhite).size() * 0.18 * sideMultiplier;
                                value += mobility;
                            } else {
                                mobility = filterMoves(piece, bishop.getPossibleMoves(Integer.toString(col) + Integer.toString(row + 1), isWhite), isWhite).size() * 0.18 * sideMultiplier;
                                value += mobility;
                            }
                        }
                    }
                    if (Character.toLowerCase(piece.charAt(0)) == 'r') {
                        value += 5 * sideMultiplier;
                        if(difficulty == "Medium" || difficulty == "Hard") {
                            mobility = filterMoves(piece, rook.getPossibleMoves(Integer.toString(col) + Integer.toString(row + 1), isWhite), isWhite).size() * 0.15 * sideMultiplier;
                            value += mobility;
                        }
                    }
                    if (Character.toLowerCase(piece.charAt(0)) == 'q') {
                        value += 9 * sideMultiplier;
                        if(difficulty == "Medium" || difficulty == "Hard") {
                            mobility = filterMoves(piece, queen.getPossibleMoves(Integer.toString(col) + Integer.toString(row + 1), isWhite), isWhite).size() * 0.17 * sideMultiplier;
                            value += mobility;
                        }
                    }
                }
            }
        }

        //Adds castling bonuses if a side has castled - only if difficulty on hard
        if(difficulty == "Hard") {
            if (Main_Display.gui.hasBlackCastled) {
                value -= 2;
            }
            if (Main_Display.gui.hasWhiteCastled) {
                value += 2;
            }
        }
        return value;
    }


    public ArrayList<String> filterMoves(String piece, ArrayList<String> possibleMoves, boolean isWhitePlaying) {
        ArrayList<String> movesToRemove = new ArrayList<String>();
        movesToRemove.clear();

        //Iterates through each move, which are just the coordinates of the square a piece is moving to, in a List of all the possible moves a certain piece can do
        for (String move : possibleMoves) {
            move = convertCoord(move, true); //Converts coordinates in GridPane format to logical board array format (row decremented by 1)

            tryMove(piece, move); //Tries move in board array - not shown on GUI

            //If king of side playing is in check after move, adds move to remove to another List of moves to remove
            if (isKingInCheck(isWhitePlaying)) {
                movesToRemove.add(convertCoord(move, false));
            }

            revertLogicBoard(); //Undoes move tried in board array
        }

        //Iterates through list of moves to remove and removes each move from the original inputted list, thus filtering it
        for(String moveRemove: movesToRemove){
            possibleMoves.remove(moveRemove);
        }
        return possibleMoves;
    }
}
