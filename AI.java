import java.util.ArrayList;
import java.util.Arrays;
public class AI {

    public static AI instance;
    public static AI getInstance(){
        if(instance == null){
            instance = new AI();
        }
        return instance;
    }
    Logic_Board boardLogic;
    Pawn pawn;
    Rook rook;
    Bishop bishop;
    Knight knight;
    Queen queen;
    King king;
    public String[][] validMoves = new String[1000][2];
    int size = 0;
    String pieceToMove = "";
    String coordToMove = "";
    public boolean staleMate = false;
    public String difficulty ="Medium";
    private int depthset = 3;
    public void init(){
        boardLogic = Logic_Board.getInstance();
        pawn = Pawn.getInstance();
        rook = Rook.getInstance();
        bishop = Bishop.getInstance();
        knight = Knight.getInstance();
        queen = Queen.getInstance();
        king = King.getInstance();
    }

    public void AIMakeMove(boolean isWhitePlaying){
        pieceToMove = "";
        coordToMove = "";
        if(difficulty == "Easy") {
            depthset = 2;
        }
        else if(difficulty == "Medium") {
            depthset = 3;
        }
        else if(difficulty == "Hard") {
            depthset = 4;
        }
        double moveScore = alphaBetaMin(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, depthset);
        boardLogic.makeMove(pieceToMove, coordToMove, isWhitePlaying);
    }

    //Mini-Max!
    private double alphaBetaMax(double alpha, double beta, int depth){
        double score = 0;
        if(depth == 0){
            return boardLogic.evaluateBoard();
        }
        getAllPossibleMoves(true);
        String[][] copyValidMoves = new String[1000][2];
        String[][] boardLogicCopy = new String[8][8];
        for(int i = 0; i<8; i++){
            boardLogicCopy[i] = Arrays.copyOf(boardLogic.boardLogic[i], 8);
        }
        int sizeCopy = size;
        for(int i = 0; i<sizeCopy; i++){
            copyValidMoves[i][0] = validMoves[i][0];
            copyValidMoves[i][1] = validMoves[i][1];
        }
        for(int i=0; i<sizeCopy; i++){
            String piece = copyValidMoves[i][1];
            String coord = copyValidMoves[i][0];
            boardLogic.tryMove(piece, coord);
            score = alphaBetaMin(alpha, beta, depth-1);
            if(score>=beta){
                for(int j = 0; j<8; j++){
                    boardLogic.boardLogic[j] = Arrays.copyOf(boardLogicCopy[j], 8);
                }
                if(depth == depthset) {
                    pieceToMove = piece;
                    coordToMove = coord;
                }
                return beta;
            }
            if(score>alpha){
                alpha = score;
                if(depth == depthset) {
                    pieceToMove = piece;
                    coordToMove = coord;
                }
            }
            for(int j = 0; j<8; j++){
                boardLogic.boardLogic[j] = Arrays.copyOf(boardLogicCopy[j], 8);
            }

        }
        return alpha;
    }
    public double alphaBetaMin(double alpha, double beta, int depth){
        double score = 0;
        if(depth == 0){
            return boardLogic.evaluateBoard();
        }
        getAllPossibleMoves(false);
        String[][] copyValidMoves = new String[1000][2];
        String[][] boardLogicCopy = new String[8][8];
        for(int i = 0; i<8; i++){
            boardLogicCopy[i] = Arrays.copyOf(boardLogic.boardLogic[i], 8);
        }
        int sizeCopy = size;
        for(int i = 0; i<sizeCopy; i++){
            copyValidMoves[i][0] = validMoves[i][0];
            copyValidMoves[i][1] = validMoves[i][1];
        }

        for(int i=0; i<sizeCopy; i++){
            String piece = copyValidMoves[i][1];
            String coord = copyValidMoves[i][0];
            boardLogic.tryMove(piece, coord);
            score = alphaBetaMax(alpha, beta, depth-1);
            if(score<=alpha){
                for(int j = 0; j<8; j++){
                    boardLogic.boardLogic[j] = Arrays.copyOf(boardLogicCopy[j], 8);
                }
                if(depth == depthset) {
                    pieceToMove = piece;
                    coordToMove = coord;
                }
                return alpha;
            }
            if(score<beta){
                beta = score;
                if(depth == depthset) {
                    pieceToMove = piece;
                    coordToMove = coord;
                }
            }
            for(int j = 0; j<8; j++){
                boardLogic.boardLogic[j] = Arrays.copyOf(boardLogicCopy[j], 8);
            }
        }
        return beta;
    }
    public void getAllPossibleMoves(boolean isWhitePlaying)
    {
        for(int i = 0; i<1000; i++){
            validMoves[i][0] = null;
            validMoves[i][1] = null;
        }
        staleMate = true;
        ArrayList<String> possibleMoves = new ArrayList<String>();
        String coord = "";
        String piece = "";
        String[][] moves = new String[1000][2];
        int count = 0;
        size = 0;
        for(int r = 0; r<8; r++){
            for(int c = 0; c<8; c++){
                if(boardLogic.boardLogic[r][c]!="") {
                    if ((boardLogic.boardLogic[r][c].charAt(0) == 'p' && !isWhitePlaying) || (boardLogic.boardLogic[r][c].charAt(0) == 'P' && isWhitePlaying)) {
                        coord = Integer.toString(c) + Integer.toString(r + 1);
                        piece = boardLogic.boardLogic[r][c];
                        possibleMoves = boardLogic.filterMoves(piece, pawn.getPossibleMoves(coord, isWhitePlaying), isWhitePlaying);
                    }
                    if ((boardLogic.boardLogic[r][c].charAt(0) == 'r' && !isWhitePlaying) || (boardLogic.boardLogic[r][c].charAt(0) == 'R' && isWhitePlaying)) {
                        coord = Integer.toString(c) + Integer.toString(r + 1);
                        piece = boardLogic.boardLogic[r][c];
                        possibleMoves = boardLogic.filterMoves(piece, rook.getPossibleMoves(coord, isWhitePlaying), isWhitePlaying);
                    }
                    if ((boardLogic.boardLogic[r][c].charAt(0) == 'n' && !isWhitePlaying) || (boardLogic.boardLogic[r][c].charAt(0) == 'N' && isWhitePlaying)) {
                        coord = Integer.toString(c) + Integer.toString(r + 1);
                        piece = boardLogic.boardLogic[r][c];
                        possibleMoves = boardLogic.filterMoves(piece, knight.getPossibleMoves(coord, isWhitePlaying), isWhitePlaying);
                    }
                    if ((boardLogic.boardLogic[r][c].charAt(0) == 'b' && !isWhitePlaying) || (boardLogic.boardLogic[r][c].charAt(0) == 'B' && isWhitePlaying)) {
                        coord = Integer.toString(c) + Integer.toString(r + 1);
                        piece = boardLogic.boardLogic[r][c];
                        possibleMoves = boardLogic.filterMoves(piece, bishop.getPossibleMoves(coord, isWhitePlaying), isWhitePlaying);
                    }
                    if ((boardLogic.boardLogic[r][c].charAt(0) == 'q' && !isWhitePlaying) || (boardLogic.boardLogic[r][c].charAt(0) == 'Q' && isWhitePlaying)) {
                        coord = Integer.toString(c) + Integer.toString(r + 1);
                        piece = boardLogic.boardLogic[r][c];
                        possibleMoves = boardLogic.filterMoves(piece, queen.getPossibleMoves(coord, isWhitePlaying), isWhitePlaying);
                    }
                    if ((boardLogic.boardLogic[r][c].charAt(0) == 'k' && !isWhitePlaying) || (boardLogic.boardLogic[r][c].charAt(0) == 'K' && isWhitePlaying)) {
                        coord = Integer.toString(c) + Integer.toString(r + 1);
                        piece = boardLogic.boardLogic[r][c];
                        possibleMoves = boardLogic.filterMoves(piece, king.getPossibleMoves(coord, isWhitePlaying), isWhitePlaying);
                    }
                        count = 0;
                        if(possibleMoves.size()!=0) {
                            staleMate = false;
                            for (int i = size; i < possibleMoves.size() + size; i++) {
                                validMoves[i][0] = boardLogic.coordGuiToLogic(possibleMoves.get(count), true);
                                validMoves[i][1] = piece;
                                count++;
                            }
                            count = possibleMoves.size();
                            size += possibleMoves.size();
                        }

                }
                possibleMoves.clear();
            }
        }

    }


}
