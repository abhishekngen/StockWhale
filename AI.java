import java.util.ArrayList;
import java.util.Arrays;

public class AI {

    private AI(){} //Private constructor
    public static AI instance; //Singleton instance
    public static AI getInstance(){
        if(instance == null){
            instance = new AI(); //Instantiates singleton instance only ever once
        }
        return instance;
    }

    //Object references
    Logic_Board logic_board;
    Pawn pawn;
    Rook rook;
    Bishop bishop;
    Knight knight;
    Queen queen;
    King king;

    //Private local variables (used throughout class)
    private String[][] validMoves = new String[1000][2];
    private int size = 0;
    private String pieceToMove = "";
    private String coordToMove = "";
    private boolean staleMate = false;
    private String difficulty ="Medium";
    private int depthSet = 3;

    //Instantiates object references
    public void init(){
        logic_board = Logic_Board.getInstance();
        pawn = new Pawn();
        rook = new Rook();
        bishop = new Bishop();
        knight = new Knight();
        queen = new Queen();
        king = new King();
    }

    //Used for the AI to perform a move
    public void AIMakeMove(boolean isWhitePlaying){

        //Checks the difficulty being played at and sets the depth at which the optimised minimax tree search will be carried out, depthSet, accordingly, with a higher difficulty resulting in a higher depth search to make the AI play at a harder level and play more optimal moves
        if(difficulty == "Easy") {
            depthSet = 2; //Depth at which search will be carried out
        }
        else if(difficulty == "Medium") {
            depthSet = 3;
        }
        else if(difficulty == "Hard") {
            depthSet = 4;
        }

        //Calls minimising function first as AI plays black, which is the minimiser
        alphaBetaMin(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, depthSet);

        //Upon completion of the alpha-beta optimised minimax tree traversal, the variables pieceToMove and coordToMove would have been updated with the piece to move and the coordinates of the square to move it to, which are then inputted as parameters into the makeMove() method of the Logic_Board class to perform the move
        logic_board.makeMove(pieceToMove, coordToMove, isWhitePlaying);
    }

    //Maximising alpha-beta optimised minimax function
    private double alphaBetaMax(double alpha, double beta, int depth){

        double score = 0;

        if(depth == 0){ //At leaf node layer
            return logic_board.evaluateBoard(); //Evaluates leaf node
        }

        //Aggregates all possible moves maximising side can do in current board state held in the node the algorithm is currently at to produce child nodes that are part of the next layer in the tree
        getAllPossibleMoves(true);

        String[][] copyValidMoves = new String[1000][2];
        String[][] boardLogicCopy = new String[8][8];

        //Copies board state held in current node to a temporary array
        for(int i = 0; i<8; i++){
            boardLogicCopy[i] = Arrays.copyOf(logic_board.logicBoard[i], 8);
        }

        //Copies all valid moves aggregated from local validMoves array to a temporary array, and copies size of this instance of the validMoves array
        int sizeCopy = size;
        for(int i = 0; i<sizeCopy; i++){
            copyValidMoves[i][0] = validMoves[i][0];
            copyValidMoves[i][1] = validMoves[i][1];
        }

        //Iterates through temporary array containing copy of validMoves
        for(int i=0; i<sizeCopy; i++){

            //Extracts piece that can be moved and one of the corresponding moves that can be made from array
            String piece = copyValidMoves[i][1];
            String coord = copyValidMoves[i][0];

            //Makes the move using tryMove() in the logicBoard array, which creates an arc to a child node in the next lower layer
            logic_board.tryMove(piece, coord);

            //Obtains value (score) of child node that corresponds with the arc (the move) that the current iteration is on, where the value of the child node itself is obtained through a call to alphaBetaMax, and so on using recursion where the values are established through back-tracking the values of the evaluated leaf nodes
            score = alphaBetaMin(alpha, beta, depth-1);
            //System.out.println("Maximising back-tracked node value: " + score);
            //If score (value of the current child node being assessed) is greater than or equal to the lowest value that the minimiser is currently able to obtain at that point in the tree or above (beta)
            if(score>=beta){

                //Reverts move made in logicBoard in preparation to make next move and the corresponding next arc leading to the next child node
                for(int j = 0; j<8; j++){
                    logic_board.logicBoard[j] = Arrays.copyOf(boardLogicCopy[j], 8);
                }

                if(depth == depthSet) {
                    pieceToMove = piece;
                    coordToMove = coord;
                }
                //As there is a minimising node above this node which wants the lowest evaluation value possible and has a value of beta less than or equal to the value of the child node just checked, beta is immediately returned as the minimising node above this node will NEVER choose this node, as it already has a node that will give it a lower value due to its beta value being lower, as beta represents the lowest value the minimiser can get at that point in the tree and above. Furthermore, it is aware this node's value will be equal to the score generated or higher as it is a maximising node. Hence this node, and ALL BRANCHES ATTACHED to it, are pruned off.
                //HOWEVER, if node is a root node then obviously there is no minimising layer above it, thus this if statement is never called as beta is set at +infinity initially which is greater than everything and the beta value associated with this node does not change as only its alpha value changes from backtracking, and there are no nodes above it to feed it a new beta value
                return beta;
            }

            //If score (value of the current child node being assessed) is greater than the highest value that the maximiser is currently able to obtain at that point in the tree or above
            if(score>alpha){

                //Updates alpha value to this score to correctly represent highest value obtainable
                alpha = score;
                System.out.println("New alpha value: " + alpha);
                //If at root node, and alpha is updated indicating highest score obtained so far, copies piece and coordinates piece is moved to to obtain this evaluation value, as this piece and coordinates combination may be the optimal move
                if(depth == depthSet) {
                    pieceToMove = piece;
                    coordToMove = coord;
                }
            }

            //Reverts move made in logicBoard in preparation to test next move (held in the next arc leading to next child node)
            for(int j = 0; j<8; j++){
                logic_board.logicBoard[j] = Arrays.copyOf(boardLogicCopy[j], 8);
            }

        }

        //Returns highest value obtainable at that point in tree or above as maximising node (if beta was not returned due to pruning)
        return alpha;
    }

    //Minimising alpha-beta optimised minimax function
    private double alphaBetaMin(double alpha, double beta, int depth){
        double score = 0;

        if(depth == 0){ //At leaf node layer
            return logic_board.evaluateBoard(); //Evaluates leaf node
        }

        //Aggregates all possible moves minimising side can do in current board state held in the node the algorithm is currently at to produce child nodes that are part of the next layer in the tree
        getAllPossibleMoves(false);

        String[][] copyValidMoves = new String[1000][2];
        String[][] boardLogicCopy = new String[8][8];

        //Copies board state held in current node to a temporary array
        for(int i = 0; i<8; i++){
            boardLogicCopy[i] = Arrays.copyOf(logic_board.logicBoard[i], 8);
        }

        //Copies all valid moves aggregated from local validMoves array to a temporary array, and copies size of this instance of the validMoves array
        int sizeCopy = size;
        for(int i = 0; i<sizeCopy; i++){
            copyValidMoves[i][0] = validMoves[i][0];
            copyValidMoves[i][1] = validMoves[i][1];
        }

        //Iterates through temporary array containing copy of validMoves
        for(int i=0; i<sizeCopy; i++){

            //Extracts piece that can be moved and one of the corresponding moves that can be made from array
            String piece = copyValidMoves[i][1];
            String coord = copyValidMoves[i][0];

            //Makes the move using tryMove() in the logicBoard array, which creates an arc to a child node in the next lower layer
            logic_board.tryMove(piece, coord);

            //Obtains value (score) of child node that corresponds with the arc (the move) that the current iteration is on, where the value of the child node itself is obtained through a call to alphaBetaMax, and so on using recursion where the values are established through back-tracking the values of the evaluated leaf nodes
            score = alphaBetaMax(alpha, beta, depth-1);
            //System.out.println("Minimising back-tracked node value: " + score);

            //If score (value of the current child node being assessed) is less than or equal to the highest value that the maximiser is currently able to obtain at that point in the tree or above (alpha)
            if(score<=alpha){

                //Reverts move made in logicBoard in preparation to make next move and the corresponding next arc leading to the next child node
                for(int j = 0; j<8; j++){
                    logic_board.logicBoard[j] = Arrays.copyOf(boardLogicCopy[j], 8);
                }
                if(depth == depthSet) {
                    pieceToMove = piece;
                    coordToMove = coord;
                }

                //As there is a maximising node above this node which wants the highest evaluation value possible and has a value of alpha greater than or equal to the value of the child node just checked, alpha is immediately returned as the maximising node above this node will NEVER choose this node, as it already has a node that will give it a higher value or the same due to its alpha value being higher, as alpha represents the highest value the maximiser can get at that point in the tree or above. Furthermore, it is aware this node's value will be equal to the score generated or lower as it is a minimising node. Hence ALL BRANCHES ATTACHED to the node are pruned off.
                //HOWEVER, if node is a root node then obviously there is no maximising layer above it, thus this if statement is never called as alpha is set at -infinity initially which is lower than everything and the alpha value associated with this node does not change as only its beta value changes from backtracking, and there are no nodes above it to feed it a new alpha value
                return alpha;
            }

            //If score (value of the current child node being assessed) is lower than the lowest value that the minimiser is currently able to obtain at that point in the tree or above
            if(score<beta){

                //Updates beta value to this score to correctly represent lowest value obtainable
                beta = score;
                System.out.println("New beta value: " + beta);
                //If at root node, and beta is updated indicating lowest score obtained so far, copies piece and coordinates the piece is moved to to obtain this evaluation value, as this piece and coordinates combination may be the optimal move
                if(depth == depthSet) {
                    pieceToMove = piece;
                    coordToMove = coord;
                }
            }

            //Reverts move made in logicBoard in preparation to test next move (held in the next arc leading to next child node)
            for(int j = 0; j<8; j++){
                logic_board.logicBoard[j] = Arrays.copyOf(boardLogicCopy[j], 8);
            }
        }

        //Returns highest value obtainable at that point in tree or above as maximising node (if beta was not returned due to pruning)
        return beta;
    }

    //Aggregates all possible valid moves a certain side can perform in the current board state
    public void getAllPossibleMoves(boolean isWhitePlaying)
        {

        //Clears validMoves two-dimensional array, which stores each move by having one column represent the piece being moved, and the other column representing the coordinates that can be moved to
        for(int i = 0; i<1000; i++){
            validMoves[i][0] = null;
            validMoves[i][1] = null;
        }

        staleMate = true; //Stalemate boolean property
        ArrayList<String> possibleMoves = new ArrayList<String>();
        String coord = "";
        String piece = "";
        int counter;
        size = 0;

        //Iterates through logicBoard array
        for(int r = 0; r<8; r++){
            for(int c = 0; c<8; c++){

                if(logic_board.logicBoard[r][c]!="") { //If square is not empty

                    //Checks what type the piece in square and if it is on the same side that is playing, then obtains all possible moves that piece can do in the current board state by using getPossibleMoves() function of appropriate piece type class and calling filterMoves() on List returned
                    if ((logic_board.logicBoard[r][c].charAt(0) == 'p' && !isWhitePlaying) || (logic_board.logicBoard[r][c].charAt(0) == 'P' && isWhitePlaying)) {
                        coord = Integer.toString(c) + Integer.toString(r + 1); //Concatenates coordinates of current square to input into getPossibleMoves()

                        piece = logic_board.logicBoard[r][c]; //Gets specific piece string value

                        possibleMoves = logic_board.filterMoves(piece, pawn.getPossibleMoves(coord, isWhitePlaying), isWhitePlaying);
                    }
                    if ((logic_board.logicBoard[r][c].charAt(0) == 'r' && !isWhitePlaying) || (logic_board.logicBoard[r][c].charAt(0) == 'R' && isWhitePlaying)) {
                        coord = Integer.toString(c) + Integer.toString(r + 1);
                        piece = logic_board.logicBoard[r][c];
                        possibleMoves = logic_board.filterMoves(piece, rook.getPossibleMoves(coord, isWhitePlaying), isWhitePlaying);
                    }
                    if ((logic_board.logicBoard[r][c].charAt(0) == 'n' && !isWhitePlaying) || (logic_board.logicBoard[r][c].charAt(0) == 'N' && isWhitePlaying)) {
                        coord = Integer.toString(c) + Integer.toString(r + 1);
                        piece = logic_board.logicBoard[r][c];
                        possibleMoves = logic_board.filterMoves(piece, knight.getPossibleMoves(coord, isWhitePlaying), isWhitePlaying);
                    }
                    if ((logic_board.logicBoard[r][c].charAt(0) == 'b' && !isWhitePlaying) || (logic_board.logicBoard[r][c].charAt(0) == 'B' && isWhitePlaying)) {
                        coord = Integer.toString(c) + Integer.toString(r + 1);
                        piece = logic_board.logicBoard[r][c];
                        possibleMoves = logic_board.filterMoves(piece, bishop.getPossibleMoves(coord, isWhitePlaying), isWhitePlaying);
                    }
                    if ((logic_board.logicBoard[r][c].charAt(0) == 'q' && !isWhitePlaying) || (logic_board.logicBoard[r][c].charAt(0) == 'Q' && isWhitePlaying)) {
                        coord = Integer.toString(c) + Integer.toString(r + 1);
                        piece = logic_board.logicBoard[r][c];
                        possibleMoves = logic_board.filterMoves(piece, queen.getPossibleMoves(coord, isWhitePlaying), isWhitePlaying);
                    }
                    if ((logic_board.logicBoard[r][c].charAt(0) == 'k' && !isWhitePlaying) || (logic_board.logicBoard[r][c].charAt(0) == 'K' && isWhitePlaying)) {
                        coord = Integer.toString(c) + Integer.toString(r + 1);
                        piece = logic_board.logicBoard[r][c];
                        possibleMoves = logic_board.filterMoves(piece, king.getPossibleMoves(coord, isWhitePlaying), isWhitePlaying);
                    }


                        counter = 0;
                        if(possibleMoves.size()!=0) {
                            staleMate = false; //If there are any possible moves that can be played by the playing side whatsoever, stalemate has not occurred

                            //Appends possible moves generated by current piece in current square to validMoves array
                            for (int i = size; i < possibleMoves.size() + size; i++) { //Starts at index equal to size of validMoves array so far so that moves are appended and do not override already added moves

                                //Adds coordinates of square piece can move to in GridPane format to first column of array
                                validMoves[i][0] = logic_board.convertCoord(possibleMoves.get(counter), true);

                                //Adds piece that is moving to second column of array
                                validMoves[i][1] = piece;
                                counter++;
                            }
                            size += possibleMoves.size(); //Incremented such that size always points to the next available index in array so that moves are only appended
                        }

                }
                possibleMoves.clear();
            }
        }
    }

    //Used in tandem with recommendMove() method
    public stack calculateMove(boolean isWhitePlaying){
        stack recommendedMove = new stack(); //Stores recommended move in stack

        depthSet = 2; //Depth at which minimax tree will be searched at to get recommended move

        //Calls maximising or minimising function dependent on whether white (maximiser) or black (minimiser) is playing
        if(isWhitePlaying){
            alphaBetaMax(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, depthSet);
        }
        else{
            alphaBetaMin(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, depthSet);
        }

        //Pushes the piece to move for recommended move onto stack
        recommendedMove.push(pieceToMove);

        //Extracts row and column values from coordToMove generated from minimax
        String row = coordToMove.substring(0,1);
        String col = coordToMove.substring(1);

        //Pushes row and column values onto stack
        recommendedMove.push(row);
        recommendedMove.push(col);

        //Returns stack
        return recommendedMove;
    }

    //Getters and setters
    public boolean getStalemateValue(){
        return staleMate;
    }

    public String getDifficulty(){
        return difficulty;
    }

    public void setDifficulty(String difficultyInput){
        difficulty = difficultyInput;
    }

    public boolean anyValidMoves(){
        if(validMoves[0][0] == null){
            return false;
        }
        else{
            return true;
        }
    }
}
