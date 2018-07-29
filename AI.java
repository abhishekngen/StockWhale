import java.util.ArrayList;
import java.util.Random;

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
    public boolean staleMate = false;
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
        /*Random random = new Random();
        if(possibleMoves.size()!=0){
            int randnum = random.nextInt(possibleMoves.size());
            String move = possibleMoves.get(randnum);
            String piece = move.substring(0, 2);
            String coord = move.substring(2);
            boardLogic.makeMove(piece, boardLogic.coordGuiToLogic(coord, true), isWhitePlaying);
        }
        else{
            System.out.println("L");
        }*/
        /*String pos = null;
        Random random = new Random();
        String piece = null;
        int randnum1 = random.nextInt(8);
        if(randnum1 == 0) {
            piece = "p1";
        }
        else if(randnum1 == 1){
            piece = "p2";
        }
        else if(randnum1 == 2){
            piece = "p3";
        }
        else if(randnum1 == 3){
            piece = "p4";
        }
        else if(randnum1 == 4){
            piece = "p5";
        }
        else if(randnum1 == 5){
            piece = "p6";
        }
        else if(randnum1 == 6){
            piece = "p7";
        }
        else if(randnum1 == 7){
            piece = "p8";
        }
        pos = boardLogic.getPos(piece);
        if(pos!=null){
            pos = boardLogic.coordGuiToLogic(pos, false);
            pos = boardLogic.reverse(pos);
            ArrayList<String> possibleMovesPawn = new ArrayList<String>();
            possibleMovesPawn = pawn.getPossibleMoves(pos, isWhitePlaying);
            if(possibleMovesPawn.size()!=0) {
                int randnum = random.nextInt(possibleMovesPawn.size());
                String move = possibleMovesPawn.get(randnum);
                boardLogic.makeMove(piece, boardLogic.coordGuiToLogic(move, true), false);
            }
            else{
                System.out.println("L2");
            }
        }
        else{
            System.out.println("L1");
        }*/
        getAllPossibleMoves(isWhitePlaying);
        Random random = new Random();
        if(size!=0) {
            int randnum = random.nextInt(size);
            String piece = validMoves[randnum][1];
            String coord = validMoves[randnum][0];
            boardLogic.makeMove(piece, coord, false);
        }
    }

    //Mini-Max!
    public double alphaBetaMax(double alpha, double beta, int depth){
        if(depth == 0){
            return boardLogic.evaluateBoard();
        }
        ArrayList<String> possibleMoves = boardLogic.getAllPossibleMoves(boardLogic.boardLogic, true);
        for(int i = 0; i<possibleMoves.size(); i++){
            boardLogic.tryMove(possibleMoves.get(i).substring(0, 2), possibleMoves.get(i).substring(3));
            double score = alphaBetaMin(alpha, beta, depth-1);
            if(score>=beta){
                return beta;
            }
            if(score>alpha){
                alpha = score;
            }
        }
        return alpha;
    }
    public double alphaBetaMin(double alpha, double beta, int depth){
        return alpha;
    }
    public void getAllPossibleMoves(boolean isWhitePlaying)
    {
        for(int i = 0; i<1000; i++){
            validMoves[i][0] = null;
            validMoves[i][1] = null;
        }
        staleMate = true;
        ArrayList<String> possibleMoves = new ArrayList<String>();
        //validMoves = null;
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
