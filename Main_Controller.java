
import java.net.URL;
import java.util.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.image.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Main_Controller
    implements Initializable
{
    //Global Movement Variables
    private String coord;
    private String coordgui;
    private String originalcoord;
    private ImageView pieceMoved = new ImageView();
    private ArrayList<String> possibleMoves = new ArrayList<String>();
    private boolean isWhitePlaying = true;

    //Castling Booleans
    public boolean hasRook1Moved = false;
    public boolean hasRook2Moved = false;
    public boolean hasRook3Moved = false;
    public boolean hasRook4Moved = false;
    public boolean hasKing1Moved = false;
    public boolean hasKing2Moved = false;
    public boolean hasBlackCastled = false;
    public boolean hasWhiteCastled = false;

    //Game Mode + State Booleans
    private boolean AIInit = true;
    private boolean isAIPlaying = false;
    private boolean isCheckMate = false;
    private boolean isStaleMate = false;
    private boolean gameStarted = false;

    //Global Data Structures
    private stack boardStatesStack = new stack();
    private Hashtable<ImageView, String> imgToString = new Hashtable<ImageView, String>();

    //Object References
    private Logic_Board logic_board;
    private AI ai;
    private Pawn pawn;
    private Rook rook;
    private Bishop bishop;
    private Knight knight;
    private Queen queen;
    private King king;

    //ImageView Initialisation
    private ImageView pawnwhite1 = new ImageView(new Image("/Images/PawnWhite.png"));
    private ImageView pawnwhite2 = new ImageView(new Image("/Images/PawnWhite.png"));
    private ImageView pawnwhite3 = new ImageView(new Image("/Images/PawnWhite.png"));
    private ImageView pawnwhite4 = new ImageView(new Image("/Images/PawnWhite.png"));
    private ImageView pawnwhite5 = new ImageView(new Image("/Images/PawnWhite.png"));
    private ImageView pawnwhite6 = new ImageView(new Image("/Images/PawnWhite.png"));
    private ImageView pawnwhite7 = new ImageView(new Image("/Images/PawnWhite.png"));
    private ImageView pawnwhite8 = new ImageView(new Image("/Images/PawnWhite.png"));
    private ImageView pawnblack1 = new ImageView(new Image("/Images/PawnBlack.png"));
    private ImageView pawnblack2 = new ImageView(new Image("/Images/PawnBlack.png"));
    private ImageView pawnblack3 = new ImageView(new Image("/Images/PawnBlack.png"));
    private ImageView pawnblack4 = new ImageView(new Image("/Images/PawnBlack.png"));
    private ImageView pawnblack5 = new ImageView(new Image("/Images/PawnBlack.png"));
    private ImageView pawnblack6 = new ImageView(new Image("/Images/PawnBlack.png"));
    private ImageView pawnblack7 = new ImageView(new Image("/Images/PawnBlack.png"));
    private ImageView pawnblack8 = new ImageView(new Image("/Images/PawnBlack.png"));
    private ImageView rookwhite1 = new ImageView(new Image("/Images/RookWhite.png"));
    private ImageView rookwhite2 = new ImageView(new Image("/Images/RookWhite.png"));
    private ImageView rookblack1 = new ImageView(new Image("/Images/RookBlack.png"));
    private ImageView rookblack2 = new ImageView(new Image("/Images/RookBlack.png"));
    private ImageView knightwhite1 = new ImageView(new Image("/Images/KnightWhite.png"));
    private ImageView knightwhite2 = new ImageView(new Image("/Images/KnightWhite.png"));
    private ImageView knightblack1 = new ImageView(new Image("/Images/KnightBlack.png"));
    private ImageView knightblack2 = new ImageView(new Image("/Images/KnightBlack.png"));
    private ImageView bishopwhite1 = new ImageView(new Image("/Images/BishopWhite.png"));
    private ImageView bishopwhite2 = new ImageView(new Image("/Images/BishopWhite.png"));
    private ImageView bishopblack1 = new ImageView(new Image("/Images/BishopBlack.png"));
    private ImageView bishopblack2 = new ImageView(new Image("/Images/BishopBlack.png"));
    private ImageView queenwhite = new ImageView(new Image("/Images/QueenWhite.png"));
    private ImageView queenblack = new ImageView(new Image("/Images/QueenBlack.png"));
    private ImageView kingwhite = new ImageView(new Image("/Images/KingWhite.png"));
    private ImageView kingblack = new ImageView(new Image("/Images/KingBlack.png"));

    //FXML References - must be public
    @FXML
    public Button undoBtn;
    public Button easyBtn;
    public Button mediumBtn;
    public Button hardBtn;
    public Text evalWhiteText;
    public Text evalBlackText;
    public Text evalWinningText;
    public Button recommendMoveBtn;
    public Text pieceToMoveText;
    public Text rowToMoveToText;
    public Text columnToMoveToText;
    public GridPane boardGui;
    public Button gameModeBtn;

    public void initialize(URL location, ResourceBundle resources) //Implemented from Initialization class
    {
        //FXML Action Calls - sets function called upon button press of each button
        undoBtn.setOnAction(this::Undo);
        easyBtn.setOnAction(this::Easy);
        mediumBtn.setOnAction(this::Medium);
        hardBtn.setOnAction(this::Hard);
        gameModeBtn.setOnAction(this::changeGameMode);
        recommendMoveBtn.setOnAction(this::recommendMove);

        //Object Instantiations
        pawn = new Pawn();
        bishop = new Bishop();
        knight = new Knight();
        rook = new Rook();
        queen = new Queen();
        king = new King();
        logic_board = Logic_Board.getInstance(); //Singleton
        ai = AI.getInstance(); //Singleton

        //Image Sizing for GUI Chess Board
        pawnblack1.setFitWidth(60);
        pawnblack1.setFitHeight(58);
        pawnblack2.setFitHeight(58);
        pawnblack2.setFitWidth(60);
        pawnblack3.setFitHeight(58);
        pawnblack3.setFitWidth(60);
        pawnblack4.setFitHeight(58);
        pawnblack4.setFitWidth(60);
        pawnblack5.setFitHeight(58);
        pawnblack5.setFitWidth(60);
        pawnblack6.setFitHeight(58);
        pawnblack6.setFitWidth(60);
        pawnblack7.setFitHeight(58);
        pawnblack7.setFitWidth(60);
        pawnblack8.setFitHeight(58);
        pawnblack8.setFitWidth(60);
        pawnwhite1.setFitWidth(60);
        pawnwhite1.setFitHeight(58);
        pawnwhite2.setFitWidth(60);
        pawnwhite2.setFitHeight(58);
        pawnwhite3.setFitWidth(60);
        pawnwhite3.setFitHeight(58);
        pawnwhite4.setFitWidth(60);
        pawnwhite4.setFitHeight(58);
        pawnwhite5.setFitWidth(60);
        pawnwhite5.setFitHeight(58);
        pawnwhite6.setFitWidth(60);
        pawnwhite6.setFitHeight(58);
        pawnwhite7.setFitWidth(60);
        pawnwhite7.setFitHeight(58);
        pawnwhite8.setFitWidth(60);
        pawnwhite8.setFitHeight(58);
        rookblack1.setFitHeight(58);
        rookblack2.setFitWidth(60);
        rookwhite1.setFitHeight(58);
        rookwhite2.setFitWidth(60);
        bishopblack1.setFitHeight(58);
        bishopblack2.setFitWidth(60);
        bishopwhite1.setFitHeight(58);
        bishopwhite2.setFitWidth(60);
        knightblack1.setFitHeight(58);
        knightblack2.setFitWidth(60);
        knightwhite1.setFitHeight(58);
        knightwhite2.setFitWidth(60);
        queenblack.setFitHeight(60);
        queenblack.setFitWidth(58);
        queenwhite.setFitHeight(60);
        queenwhite.setFitWidth(58);
        kingblack.setFitHeight(60);
        kingblack.setFitWidth(58);
        kingwhite.setFitHeight(60);
        kingwhite.setFitWidth(58);

        //HashTable Generation
        imgToString.put(pawnblack1, "p1");
        imgToString.put(pawnblack2, "p2");
        imgToString.put(pawnblack3, "p3");
        imgToString.put(pawnblack4, "p4");
        imgToString.put(pawnblack5, "p5");
        imgToString.put(pawnblack6, "p6");
        imgToString.put(pawnblack7, "p7");
        imgToString.put(pawnblack8, "p8");
        imgToString.put(pawnwhite1, "P1");
        imgToString.put(pawnwhite2, "P2");
        imgToString.put(pawnwhite3, "P3");
        imgToString.put(pawnwhite4, "P4");
        imgToString.put(pawnwhite5, "P5");
        imgToString.put(pawnwhite6, "P6");
        imgToString.put(pawnwhite7, "P7");
        imgToString.put(pawnwhite8, "P8");
        imgToString.put(rookblack1, "r1");
        imgToString.put(rookblack2, "r2");
        imgToString.put(rookwhite1, "R1");
        imgToString.put(rookwhite2, "R2");
        imgToString.put(knightblack1, "n1");
        imgToString.put(knightblack2, "n2");
        imgToString.put(knightwhite1, "N1");
        imgToString.put(knightwhite2, "N2");
        imgToString.put(bishopblack1, "b1");
        imgToString.put(bishopblack2, "b2");
        imgToString.put(bishopwhite1, "B1");
        imgToString.put(bishopwhite2, "B2");
        imgToString.put(queenblack, "q");
        imgToString.put(queenwhite, "Q");
        imgToString.put(kingblack, "k");
        imgToString.put(kingwhite, "K");

        //Calling methods to instantiate objects in AI and Logic_Board classes
        ai.init();
        logic_board.init();

        //Drag and Drop code
        EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() { //Set to each ImageView piece sprite and called upon when sprite is clicked upon by mouse

            @Override
            public void handle(MouseEvent event) {
                ImageView source = (ImageView)event.getSource(); //Calls upon source that triggered Mouse-event - the ImageView sprite

                if(!isAIPlaying) { //Prevents player making a move while AI is calculating and playing move
                    Dragboard db = source.startDragAndDrop(TransferMode.ANY); //Starts a new Dragboard instance for control of this drag-and-drop
                    ClipboardContent cb = new ClipboardContent();
                    cb.putImage(source.getImage()); //Carries GUI Object that is being dragged and dropped
                    db.setContent(cb); //Clipboard instance copied into Dragboard
                }
                pieceMoved = source; //Holds ImageView copy of piece
                originalcoord = Integer.toString(GridPane.getColumnIndex(source)) + Integer.toString(GridPane.getRowIndex(source)); //Holds concatenated string form of piece's original coordinates
                event.consume();
            }
        };

        EventHandler<DragEvent> dragOver = new EventHandler<DragEvent>() { //Set to each square on GUI chess board and called upon when an object is dragged over it

            @Override
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard(); //Gets Dragboard instance of object being dragged over
                if(db.hasImage()){
                    event.acceptTransferModes(TransferMode.MOVE); //Allows Dragboard instance to be dragged over - required for dropping object
                }
                event.consume();
            }
        };

        EventHandler<DragEvent> dragDrop = new EventHandler<DragEvent>() { //Set to each square on GUI chess board and called upon when a dragged object is dropped on it

            @Override
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard(); //Gets Dragboard instance of object being dropped
                if(db.hasImage()){
                    String row = Integer.toString(GridPane.getRowIndex((Node) event.getSource())-1); //Gets logical array row of square object being dropped into - decremented by 1 as GUI GridPane row = array row + 1
                    String guirow = Integer.toString(GridPane.getRowIndex((Node) event.getSource())); //Gets GUI row of square object is being dropped into
                    String col = Integer.toString(GridPane.getColumnIndex((Node)event.getSource())); //Gets column of square object is being dropped into (same for array and gridpane)
                    coord = row+col; //concatenated coordinates for logical array
                    coordgui = guirow + col; //concatenated coordinates for GUI gridpane
                    event.setDropCompleted(true);
                }
                else {
                    event.setDropCompleted(false);
                }
                possibleMoves.clear();
                //Checks first character of piece being moved by obtaining string value from ImageView using the imgToString hashtable, then obtains list of possible moves by calling upon the appropriate piece type class
                if(isWhitePlaying) {
                    if (imgToString.get(pieceMoved).charAt(0) == 'P') {
                        possibleMoves = pawn.getPossibleMoves(originalcoord, isWhitePlaying);
                    }
                    if (imgToString.get(pieceMoved).charAt(0) == 'R') {
                        possibleMoves = rook.getPossibleMoves(originalcoord, isWhitePlaying);
                    }
                    if (imgToString.get(pieceMoved).charAt(0) == 'N') {
                        possibleMoves = knight.getPossibleMoves(originalcoord, isWhitePlaying);
                    }
                    if (imgToString.get(pieceMoved).charAt(0) == 'B') {
                        possibleMoves = bishop.getPossibleMoves(originalcoord, isWhitePlaying);
                    }
                    if (imgToString.get(pieceMoved).charAt(0) == 'Q') {
                        possibleMoves = queen.getPossibleMoves(originalcoord, isWhitePlaying);
                    }
                    if (imgToString.get(pieceMoved).charAt(0) == 'K') {
                        possibleMoves = king.getPossibleMoves(originalcoord, isWhitePlaying);
                    }
                }
                else{
                    if (imgToString.get(pieceMoved).charAt(0) == 'p') {
                        possibleMoves = pawn.getPossibleMoves(originalcoord, isWhitePlaying);
                    }
                    if (imgToString.get(pieceMoved).charAt(0) == 'r') {
                        possibleMoves = rook.getPossibleMoves(originalcoord, isWhitePlaying);
                    }
                    if (imgToString.get(pieceMoved).charAt(0) == 'n') {
                        possibleMoves = knight.getPossibleMoves(originalcoord, isWhitePlaying);
                    }
                    if (imgToString.get(pieceMoved).charAt(0) == 'b') {
                        possibleMoves = bishop.getPossibleMoves(originalcoord, isWhitePlaying);
                    }
                    if (imgToString.get(pieceMoved).charAt(0) == 'q') {
                        possibleMoves = queen.getPossibleMoves(originalcoord, isWhitePlaying);
                    }
                    if (imgToString.get(pieceMoved).charAt(0) == 'k') {
                        possibleMoves = king.getPossibleMoves(originalcoord, isWhitePlaying);
                    }
                }
                possibleMoves = logic_board.filterMoves(imgToString.get(pieceMoved), possibleMoves, isWhitePlaying); //filterMoves() removes all moves that result in the side's king entering check, which is invalid
                processMove(pieceMoved, coordgui);
                event.consume();
            }
        };

        //Generate GUI Chess Board
        for(int row = 1; row<9; row++){
            for(int c = 0; c<8; c++){
                Rectangle r = new Rectangle(); //Dynamically generates each Rectangle object for the board squares/tiles
                r.setFill(Color.TRANSPARENT);
                r.setHeight(58);
                r.setWidth(57);
                boardGui.add(r, c, row);
                r.setOnDragOver(dragOver); //Adds dragOver event handler functionality
                r.setOnDragDropped(dragDrop); //Adds dragDrop event handler functionality
            }
        }
        transposeToGui(logic_board.logicBoard); //Calls upon method to transpose piece positions in logical board array to GUI gridpane
        ObservableList<Node> children = boardGui.getChildren(); //Gets Node List of all nodes (data items) in gridpane, including ImageViews and Rectangles
        for(Node node: children)
        {
            if(node instanceof ImageView) //If node is an ImageView (piece sprite)
            {
                node.setOnDragDetected(eventHandler); //Adds mouse click detection for detecting start of drag (as defined above)
                node.setOnDragOver(dragOver); //Adds dragOver event handler functionality
                node.setOnDragDropped(dragDrop); //Adds dragDrop event handler functionality
            }
        }
        String[][] initialBoardState = new String[8][8];
        for (int i = 0; i < 8; i++) {
            initialBoardState[i] = Arrays.copyOf(logic_board.logicBoard[i], 8); //Copies starting state of board array
        }
        boardStatesStack.push(initialBoardState); //Pushes starting board state onto stack (for undo functionality)
    }

    private void transposeToGui(String[][] board)
    {
        for(int row = 0; row<8; row++)
        {
            for(int col = 0; col<8; col++)
            {
                if(board[row][col] != "") //If array element is not empty and hence contains a piece
                {
                    boardGui.add(getKey(board[row][col]), col, row+1); //Adds ImageView of piece string to GUI GridPane
                }
            }
        }
    }

    public ImageView getKey(String pieceStringValue)
    {
        ImageView key = new ImageView();
        //Generates a set of all the entries in the imgToString HashTable, and then iterates through each entry
        for(Map.Entry entry: imgToString.entrySet())
        {
            if(pieceStringValue.equals(entry.getValue())) //If associated data item stored in HashTable matches the piece string value
            {
                key = (ImageView) entry.getKey(); //Obtain the key object from the entry and cast as an ImageView
                break;
            }
        }
        return key;
    }

    private void processMove(ImageView piece, String coordgui){
        //Checks validity of move then converts onto logicboard
        boolean validMove = false;
        for(String move: possibleMoves){ //Iterates through list of all the valid possible squares the piece being moved can move to

            //If coordinates of square matches the coordinates of the square the user is attempting to move the piece to, the move is declared valid
            if(move.equals(coordgui)){
                validMove = true;
                break; //Breaks loop once move is found to be valid
            }
        }
        if(validMove) {
            gameStarted = true; //Declares game to have started, which is used to prevent game mode being changed after game has started

            logic_board.makeMove(imgToString.get(piece), coord, isWhitePlaying);
        }
    }

    public void makeMove(String pieceString, String coord) {

        //Coordinates are held as concatenated string with first character being the row, and second being the column, hence these are obtained first:
        int row = Character.getNumericValue(coord.charAt(0));
        int col = Character.getNumericValue(coord.charAt(1));

        boardGui.getChildren().remove(getKey(pieceString)); //Removes ImageView of piece being moved from GUI GridPane
        row++; //Row values in GridPane are 1 higher than array row values
        boardGui.add(getKey(pieceString), col, row); //Places ImageView of piece being moved into new cell (that represents the square)

        if(logic_board.isKingInCheck(!isWhitePlaying)){ //Checks if opposing side is in check
            if(logic_board.isKingInCheckMate(isWhitePlaying)){ //Checks if opposing side is in checkmate
                if(isWhitePlaying){
                    evalWinningText.setText("Black is in checkmate. White wins!"); //Declares on GUI White wins
                }
                else{
                    evalWinningText.setText("White is in checkmate. Black wins!"); //Declares on GUI Black wins

                }
                //Resets GUI board state evaluation text if checkmate occurs
                evalWhiteText.setText("Evaluation after last move by White: ");
                evalBlackText.setText("Evaluation after last move by Black: ");
                isCheckMate = true;
            }
        }
        if(!isCheckMate){
            ai.getAllPossibleMoves(!isWhitePlaying); //Generates all possible moves in current board state, and if there are none and checkmate is false, stalemate variable in AI class becomes true as there is stalemate
            isStaleMate = ai.getStalemateValue();
            if(isStaleMate){
                evalWinningText.setText("Stalemate - it is a tie!"); //Declares stalemate on GUI
            }
        }

        //If no checkmate or stalemate has occurred, evaluation of board state can be calculated and displayed
        if(!isCheckMate && !isStaleMate) {
            double currBoardEvaluation = logic_board.evaluateBoard(); //Obtains evaluation value of current board state
            currBoardEvaluation = Math.round(currBoardEvaluation*10)/10.0; //Rounds evaluation value to 1d.p. for GUI display

            //Displays two evaluation values - one for last move of white, and one for last move of black
            if(isWhitePlaying){
                evalWhiteText.setText("Evaluation after last move by White: " + Double.toString(currBoardEvaluation));
            }
            else{
                evalBlackText.setText("Evaluation after last move by Black: " + Double.toString(currBoardEvaluation));
            }

            //Evaluation value is positive if white is advantageous and negative if black is advantageous so that it works for the minimax algorithm - hence the sign of the value is checked to display to the user which side is currently winning

            if (currBoardEvaluation > 0) {
                evalWinningText.setText("White is currently winning");
            } else if (currBoardEvaluation < 0) {
                evalWinningText.setText("Black is currently winning");
            } else if(currBoardEvaluation == 0){
                evalWinningText.setText("Both sides are currently tied");
            }
        }

        String[][] boardSave = new String[8][8];
        //Copies current board state into another array
        for (int i = 0; i < 8; i++) {
            boardSave[i] = Arrays.copyOf(logic_board.logicBoard[i], 8);
        }

        //Pushes board state onto boardStatesStack for use with undo operation
        if(!isWhitePlaying && AIInit) { //If AI is playing, board state pushed onto stack only after AI moves
            boardStatesStack.push(boardSave);
        }

        if(!AIInit){ //If AI is not playing, board state pushed onto stack after every move done by both players
            boardStatesStack.push(boardSave);
        }

        isWhitePlaying = !isWhitePlaying; //Toggles isWhitePlaying to reflect the new side that is playing on the next move

        if(AIInit) { //If playing in AI
            isAIPlaying = !isAIPlaying; //Allows user and AI to take turns
            if (isAIPlaying) { //If AI's turn
                ai.getAllPossibleMoves(isWhitePlaying);
                ai.AIMakeMove(isWhitePlaying); //AI makes move
            }
        }
    }

    //Additional GUI Functions
    private void Undo(ActionEvent event){
        try {
            if (boardStatesStack.sizeStringArray() > 1) { //Cannot undo starting board state
                boardGui.getChildren().removeIf(o -> o instanceof ImageView); //Removes all ImageViews from GUI GridPane

                String[][] newBoard;
                boardStatesStack.popStringArray(); //Pops off the current board state from the top of the stack

                newBoard = boardStatesStack.popStringArray(); //Pops off the previous board state from top of stack and copies it to an array

                boardStatesStack.push(newBoard); //Pushes board state back onto stack as top of stack should always contain the current board state (which is now the previous board state)

                transposeToGui(newBoard); //Copies contents of previous board state onto GUI - undoing the move on the GUI

                for (int i = 0; i < 8; i++) {
                    logic_board.logicBoard[i] = Arrays.copyOf(newBoard[i], 8); //Copies previous board state into logical board array
                }

                if (AIInit) {
                    isWhitePlaying = true; //White always plays after undo if AI is playing
                } else {
                    isWhitePlaying = !isWhitePlaying; //Otherwise side that is playing after undo
                }

                isCheckMate = false; //Checkmate circumstance nullified after undo
                isStaleMate = false;

                //Removes evaluation values from display after undo
                evalWhiteText.setText("Evaluation after last move by White: ");
                evalBlackText.setText("Evaluation after last move by Black: ");
                evalWinningText.setText("");
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage() + "; stack error");
        }
    }

    public void Easy(ActionEvent event){
        ai.setDifficulty("Easy");
    }
    public void Medium(ActionEvent event){
        ai.setDifficulty("Medium");
    }
    public void Hard(ActionEvent event){
        ai.setDifficulty("Hard");
    }

    public void changeGameMode(ActionEvent event){
        if(!gameStarted) {
            AIInit = !AIInit; //Toggles value of AIInit

            //Displays appropriate message for game mode selected
            if (!AIInit) {
                gameModeBtn.setText("Two-Player Mode");
            } else {
                gameModeBtn.setText("AI Mode");
            }
        }
    }

    public void recommendMove(ActionEvent event){

        stack recommendedMove = new stack();
        recommendedMove = ai.calculateMove(isWhitePlaying); //AI calculates an optimal move to play for the side playing using the minimax algorithm and returns it

        //Displays piece to move, and the column and row to move it to to perform the optimal move calculated by the AI - the recommended move
        columnToMoveToText.setText("Column: " + recommendedMove.popString());
        rowToMoveToText.setText("Row: " + recommendedMove.popString());
        pieceToMoveText.setText("Piece to move: " + recommendedMove.popString());
    }
}
