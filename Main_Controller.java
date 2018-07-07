
import java.net.URL;
import java.util.*;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.image.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Main_Controller
    implements Initializable
{
    //GUI Elements
    public GridPane boardGui;
    //Rectangles
    public Rectangle rect1;
    public Rectangle rect2;
    //HashMaps
    public HashMap<ImageView, String> imgToString = new HashMap<ImageView, String>();

    //Misc
    private String coord = "0";
    private String coordgui = "0";
    private String originalcoord = "0";
    private boolean guiMove = false;
    private ImageView pieceMoved = new ImageView();
    private int dragDetect = 0;
    private ArrayList<String> possibleMoves = new ArrayList<String>();
    private boolean isWhitePlaying = true;

    //Piece Object References
    Pawn pawn;
    Rook rook;
    Bishop bishop;
    Knight knight;
    Queen queen;
    King king;
    //ImageView Initialisation
    ImageView pawnwhite1 = new ImageView(new Image("/Images/PawnWhite.png"));
    ImageView pawnwhite2 = new ImageView(new Image("/Images/PawnWhite.png"));
    ImageView pawnwhite3 = new ImageView(new Image("/Images/PawnWhite.png"));
    ImageView pawnwhite4 = new ImageView(new Image("/Images/PawnWhite.png"));
    ImageView pawnwhite5 = new ImageView(new Image("/Images/PawnWhite.png"));
    ImageView pawnwhite6 = new ImageView(new Image("/Images/PawnWhite.png"));
    ImageView pawnwhite7 = new ImageView(new Image("/Images/PawnWhite.png"));
    ImageView pawnwhite8 = new ImageView(new Image("/Images/PawnWhite.png"));

    ImageView pawnblack1 = new ImageView(new Image("/Images/PawnBlack.png"));
    ImageView pawnblack2 = new ImageView(new Image("/Images/PawnBlack.png"));
    ImageView pawnblack3 = new ImageView(new Image("/Images/PawnBlack.png"));
    ImageView pawnblack4 = new ImageView(new Image("/Images/PawnBlack.png"));
    ImageView pawnblack5 = new ImageView(new Image("/Images/PawnBlack.png"));
    ImageView pawnblack6 = new ImageView(new Image("/Images/PawnBlack.png"));
    ImageView pawnblack7 = new ImageView(new Image("/Images/PawnBlack.png"));
    ImageView pawnblack8 = new ImageView(new Image("/Images/PawnBlack.png"));

    ImageView rookwhite1 = new ImageView(new Image("/Images/RookWhite.png"));
    ImageView rookwhite2 = new ImageView(new Image("/Images/RookWhite.png"));

    ImageView rookblack1 = new ImageView(new Image("/Images/RookBlack.png"));
    ImageView rookblack2 = new ImageView(new Image("/Images/RookBlack.png"));

    ImageView knightwhite1 = new ImageView(new Image("/Images/KnightWhite.png"));
    ImageView knightwhite2 = new ImageView(new Image("/Images/KnightWhite.png"));

    ImageView knightblack1 = new ImageView(new Image("/Images/KnightBlack.png"));
    ImageView knightblack2 = new ImageView(new Image("/Images/KnightBlack.png"));

    ImageView bishopwhite1 = new ImageView(new Image("/Images/BishopWhite.png"));
    ImageView bishopwhite2 = new ImageView(new Image("/Images/BishopWhite.png"));

    ImageView bishopblack1 = new ImageView(new Image("/Images/BishopBlack.png"));
    ImageView bishopblack2 = new ImageView(new Image("/Images/BishopBlack.png"));

    ImageView queenwhite = new ImageView(new Image("/Images/QueenWhite.png"));

    ImageView queenblack = new ImageView(new Image("/Images/QueenBlack.png"));

    ImageView kingwhite = new ImageView(new Image("/Images/KingWhite.png"));

    ImageView kingblack = new ImageView(new Image("/Images/KingBlack.png"));

    //Object References
    Logic_Board logic_board = Logic_Board.getInstance();


    public void initialize(URL location, ResourceBundle resources)
    {
        pawn = Pawn.getInstance();
        rook = Rook.getInstance();
        knight = Knight.getInstance();
        bishop = Bishop.getInstance();
        queen = Queen.getInstance();
        king = King.getInstance();
        //Image Sizing
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

        //Arranging pieces


        //HashMap Generation
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
        logic_board.init();
        EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ImageView source = (ImageView)event.getSource();
                Dragboard db = source.startDragAndDrop(TransferMode.ANY);
                ClipboardContent cb = new ClipboardContent();
                cb.putImage(source.getImage());
                db.setContent(cb);
                pieceMoved = source;
                originalcoord = Integer.toString(GridPane.getColumnIndex(source)) + Integer.toString(GridPane.getRowIndex(source));
                event.consume();
            }
        };
        EventHandler<DragEvent> dragOver = new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                if(db.hasImage()){
                    event.acceptTransferModes(TransferMode.MOVE);
                }

                event.consume();
            }
        };
        EventHandler<DragEvent> dragDrop = new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                if(db.hasImage()){
                    String row = Integer.toString(GridPane.getRowIndex((Node) event.getSource())-1);
                    String guirow = Integer.toString(GridPane.getRowIndex((Node) event.getSource()));
                    String col = Integer.toString(GridPane.getColumnIndex((Node)event.getSource()));
                    coord = row+col;
                    coordgui = guirow + col;
                    event.setDropCompleted(true);
                }
                else {
                    event.setDropCompleted(false);
                }
                possibleMoves.clear();
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
                processMove(pieceMoved, coordgui);
                event.consume();
            }
        };

        //Transpose logic board with initial setup
        for(int row = 1; row<9; row++){
            for(int c = 0; c<8; c++){
                Rectangle r = new Rectangle();
                r.setFill(Color.TRANSPARENT);
                r.setHeight(58);
                r.setWidth(57);
                boardGui.add(r, c, row);
                r.setOnDragOver(dragOver);
                r.setOnDragDropped(dragDrop);
            }
        }
        transposeToGui(logic_board.boardLogic);
        ObservableList<Node> children = boardGui.getChildren();
        for(Node node: children)
        {
            if(node instanceof ImageView)
            {
                node.setOnDragDetected(eventHandler);
                node.setOnDragOver(dragOver);
                node.setOnDragDropped(dragDrop);
            }
        }
    }
    public void transposeToGui(String[][] board)
    {
        for(int row = 0; row<8; row++)
        {
            for(int col = 0; col<8; col++)
            {
                if(board[row][col] != "")
                {
                    boardGui.add(getKey(board[row][col]), col, row+1);
                }
            }
        }
    }
    public ImageView getKey(String logicPiece)
    {
        ImageView key = new ImageView();
        for(Map.Entry entry: imgToString.entrySet())
        {
            if(logicPiece.equals(entry.getValue()))
            {
                key = (ImageView) entry.getKey();
                break;
            }
        }
        return key;
    }
    public void processMove(ImageView piece, String coordgui){
        //Checks validity of move then converts onto logicboard
        boolean validMove = false;
        for(String move: possibleMoves){
            if(move.equals(coordgui)){
                validMove = true;
                break;
            }
        }
        if(validMove) {
            logic_board.makeMove(imgToString.get(piece), coord, isWhitePlaying);
        }
    }
    public void makeMove(String pieceString, String coord) {

        int row = Character.getNumericValue(coord.charAt(0));
        int col = Character.getNumericValue(coord.charAt(1));
        boardGui.getChildren().remove(getKey(pieceString));
        row++;
        for(int r = 1; r<9; r++)
        {
            for(int c = 0; c<8; c++)
            {
                if(r == row && c == col)
                {
                    boardGui.add(getKey(pieceString), col, row);
                }
            }
        }
        if(logic_board.isKingInCheck(!isWhitePlaying)){
            if(logic_board.isKingInCheckMate(isWhitePlaying)){
                if(isWhitePlaying){
                    System.out.println("Black is in checkmate. White wins!");
                }
                else{
                    System.out.println("White is in checkmate. Black wins!");
                }
            }
        }
        isWhitePlaying = !isWhitePlaying;
    }
}
