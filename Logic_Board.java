import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.Arrays;


public class Logic_Board {

    private Logic_Board(){}
    private static Logic_Board instance = null;
    private String[][] savedBoardLogic = new String[8][8];
    private ArrayList<String> possibleMoves = new ArrayList<String>();
    Pawn pawn;
    Rook rook;
    Knight knight;
    Bishop bishop;
    Queen queen;
    King king;
    public static Logic_Board getInstance() {
        if(instance == null)
        {
            instance = new Logic_Board();
        }
        return instance;
    }
    public void init()
    {
        pawn = Pawn.getInstance();
        rook = Rook.getInstance();
        knight = Knight.getInstance();
        bishop = Bishop.getInstance();
        queen = Queen.getInstance();
        king = King.getInstance();
    }
    public String[][] boardLogic = new String[][]
            {
                    {"r1", "n1", "b1", "q", "k", "b2", "n2", "r2"},
                    {"p1", "p2", "p3", "p4", "p5", "p6", "p7", "p8"},
                    {"", "", "", "", "", "", "", ""},
                    {"", "", "", "", "", "", "", ""},
                    {"", "", "", "", "", "", "", ""},
                    {"", "", "", "", "", "", "", ""},
                    {"P1", "P2", "P3", "P4", "P5", "P6", "P7", "P8"},
                    {"R1", "N1", "B1", "Q", "K", "B2", "N2", "R2"}
            };

    public void makeMove(String piece, String coord, boolean isWhitePlaying)
    {
        for(int i = 0; i<8; i++){
            savedBoardLogic[i] = Arrays.copyOf(boardLogic[i], 8);
        }
        String pieceRemove = "";
        int row = Character.getNumericValue(coord.charAt(0));
        int col = Character.getNumericValue(coord.charAt(1));
        for(int r = 0; r<8; r++)
        {
            for(int c = 0; c<8; c++)
            {
                if(boardLogic[r][c] == piece)
                {
                    boardLogic[r][c] = "";
                }
            }
        }
        if (boardLogic[row][col] != "") {
            pieceRemove = boardLogic[row][col];
        }
        boardLogic[row][col] = piece;
        if(isKingInCheck(isWhitePlaying)){
            System.out.println("Nah");
            revertLogicBoard();
        }
        else {
            System.out.println("ok sure");
            Main_Display.gui.boardGui.getChildren().remove(Main_Display.gui.getKey(pieceRemove));
            Main_Display.gui.makeMove(piece, coord);
        }
    }

    public String getPos(String piece)
    {
        String coord = null;
        for(int row = 0; row<8; row++)
        {
            for(int col = 0; col<8; col++)
            {
                if(boardLogic[row][col] == piece)
                {
                    coord = Integer.toString(row) + Integer.toString(col);
                }
            }
        }

        return coord;

    }

    public void revertLogicBoard()
    {
        for(int i=0; i<8; i++){
            boardLogic[i] = Arrays.copyOf(savedBoardLogic[i], 8);
        }
    }

    public void tryMove(String piece, String coord){
        for(int i = 0; i<8; i++){
            savedBoardLogic[i] = Arrays.copyOf(boardLogic[i], 8);
        }
        int row = Character.getNumericValue(coord.charAt(0));
        int col = Character.getNumericValue(coord.charAt(1));
        for(int r = 0; r<8; r++)
        {
            for(int c = 0; c<8; c++)
            {
                if(boardLogic[r][c] == piece)
                {
                    boardLogic[r][c] = "";
                }
            }
        }
        boardLogic[row][col] = piece;
    }

    public boolean isKingInCheck(boolean isWhitePlaying)
    {
        if(isWhitePlaying){
            String coord = getPos("K");
            int row = Character.getNumericValue(coord.charAt(0));
            int col = Character.getNumericValue(coord.charAt(1));
            int testrow = row;
            int testcol = col;
            for(int dir = 1; dir<5; dir++){
                for(int i = 0; i<8; i++){
                    if(dir==1) {
                        testrow+=1;
                    }
                    else if(dir == 2){
                        testrow-=1;
                    }
                    else if(dir == 3){
                        testcol+=1;
                    }
                    else if(dir == 4){
                        testcol-=1;
                    }
                    if(testrow>=0 && testrow<8 && testcol>=0 && testcol<8){
                        if(boardLogic[testrow][testcol]!=""){
                            if(boardLogic[testrow][testcol].charAt(0) == 'r' || boardLogic[testrow][testcol].charAt(0) == 'q'){
                                return true;
                            }
                            else{
                                i = 8;
                            }
                        }
                    }
                    else{
                        i=8;
                    }
                    if(i>=7){
                        testrow = row;
                        testcol = col;
                    }
                }
            }
            testrow = row;
            testcol = col;
            for(int dir = 1; dir<5; dir++){
                for(int i = 0; i<8; i++){
                    if(dir==1){
                        testrow+=1;
                        testcol+=1;
                    }
                    else if(dir==2){
                        testrow+=1;
                        testcol-=1;
                    }
                    else if(dir==3){
                        testrow-=1;
                        testcol-=1;
                    }
                    else if(dir==4){
                        testrow-=1;
                        testcol+=1;
                    }
                    if(testrow>=0 && testrow<8 && testcol>=0 && testcol<8){
                        if(boardLogic[testrow][testcol]!=""){
                            if(boardLogic[testrow][testcol].charAt(0) == 'b' || boardLogic[testrow][testcol].charAt(0) == 'q'){
                                return true;
                            }
                            else{
                                i = 8;
                            }
                        }
                    }
                    else{
                        i=8;
                    }
                    if(i>=7){
                        testrow = row;
                        testcol = col;
                    }
                }

            }
            if(row-1>=0){
                if(col+1<8){
                    if(boardLogic[row-1][col+1] != "") {
                        if (boardLogic[row - 1][col + 1].charAt(0) == 'p') {
                            return true;
                        }
                    }
                }
                if(col-1>=0){
                    if(boardLogic[row-1][col-1] != "") {
                        if (boardLogic[row - 1][col - 1].charAt(0) == 'p') {
                            return true;
                        }
                    }
                }
            }
            if(row-2>=0 && col+1<8){
                if(boardLogic[row-2][col+1] != "") {
                    if (boardLogic[row - 2][col + 1].charAt(0) == 'n') {
                        return true;
                    }
                }
            }
            if(row-1>=0 && col+2<8){
                if(boardLogic[row-1][col+2] != "") {
                    if (boardLogic[row - 1][col + 2].charAt(0) == 'n') {
                        return true;
                    }
                }
            }
            if(row-2>=0 && col-1>=0){
                if(boardLogic[row-2][col-1] != "") {
                    if (boardLogic[row - 2][col - 1].charAt(0) == 'n') {
                        return true;
                    }
                }
            }
            if(row-1>=0 && col-2>=0){
                if(boardLogic[row-1][col-2] != "") {
                    if (boardLogic[row - 1][col - 2].charAt(0) == 'n') {
                        return true;
                    }
                }
            }
            if(row+1<8 && col+2<8){
                if(boardLogic[row+1][col+2] != "") {
                    if (boardLogic[row + 1][col + 2].charAt(0) == 'n') {
                        return true;
                    }
                }
            }
            if(row+2<8 && col+1<8){
                if(boardLogic[row+2][col+1] != "") {
                    if (boardLogic[row + 2][col + 1].charAt(0) == 'n') {
                        return true;
                    }
                }
            }
            if(row+2<8 && col-1>=0){
                if(boardLogic[row+2][col-1] != "") {
                    if (boardLogic[row + 2][col - 1].charAt(0) == 'n') {
                        return true;
                    }
                }
            }
            if(row-1>=0){
                if(!boardLogic[row-1][col].equals("")) {
                    if (boardLogic[row - 1][col].charAt(0) == 'k') {
                        return true;
                    }
                }
            }
            if(row-1>=0 && col+1<8){
                if(!boardLogic[row-1][col+1].equals("")) {
                    if (boardLogic[row - 1][col+1].charAt(0) == 'k') {
                        return true;
                    }
                }
            }
            if(col+1<8){
                if(!boardLogic[row][col+1].equals("")) {
                    if (boardLogic[row][col+1].charAt(0) == 'k') {
                        return true;
                    }
                }
            }
            if(row+1<8 && col+1<8){
                if(!boardLogic[row+1][col+1].equals("")) {
                    if (boardLogic[row+1][col+1].charAt(0) == 'k') {
                        return true;
                    }
                }
            }
            if(row+1<8){
                if(!boardLogic[row+1][col].equals("")) {
                    if (boardLogic[row+1][col].charAt(0) == 'k') {
                        return true;
                    }
                }
            }
            if(row+1<8 && col-1>=0){
                if(!boardLogic[row+1][col-1].equals("")) {
                    if (boardLogic[row+1][col-1].charAt(0) == 'k') {
                        return true;
                    }
                }
            }
            if(col-1>=0){
                if(!boardLogic[row][col-1].equals("")) {
                    if (boardLogic[row][col-1].charAt(0) == 'k') {
                        return true;
                    }
                }
            }
            if(row-1>=0 && col-1>=0){
                if(!boardLogic[row-1][col-1].equals("")) {
                    if (boardLogic[row-1][col-1].charAt(0) == 'k') {
                        return true;
                    }
                }
            }
            return false;

        }
        else{
            String coord = getPos("k");
            int row = Character.getNumericValue(coord.charAt(0));
            int col = Character.getNumericValue(coord.charAt(1));
            int testrow = row;
            int testcol = col;
            for(int dir = 1; dir<5; dir++){
                for(int i = 0; i<8; i++){
                    if(dir==1) {
                        testrow+=1;
                    }
                    else if(dir == 2){
                        testrow-=1;
                    }
                    else if(dir == 3){
                        testcol+=1;
                    }
                    else if(dir == 4){
                        testcol-=1;
                    }
                    if(testrow>=0 && testrow<8 && testcol>=0 && testcol<8){
                        if(boardLogic[testrow][testcol]!=""){
                            if(boardLogic[testrow][testcol].charAt(0) == 'R' || boardLogic[testrow][testcol].charAt(0) == 'Q'){
                                return true;
                            }
                            else{
                                i = 8;
                            }
                        }
                    }
                    else{
                        i=8;
                    }
                    if(i>=7){
                        testrow = row;
                        testcol = col;
                    }
                }
            }
            testrow = row;
            testcol = col;
            for(int dir = 1; dir<5; dir++){
                for(int i = 0; i<8; i++){
                    if(dir==1){
                        testrow+=1;
                        testcol+=1;
                    }
                    else if(dir==2){
                        testrow+=1;
                        testcol-=1;
                    }
                    else if(dir==3){
                        testrow-=1;
                        testcol-=1;
                    }
                    else if(dir==4){
                        testrow-=1;
                        testcol+=1;
                    }
                    if(testrow>=0 && testrow<8 && testcol>=0 && testcol<8){
                        if(boardLogic[testrow][testcol]!=""){
                            if(boardLogic[testrow][testcol].charAt(0) == 'B' || boardLogic[testrow][testcol].charAt(0) == 'Q'){
                                return true;
                            }
                            else{
                                i = 8;
                            }
                        }
                    }
                    else{
                        i=8;
                    }
                    if(i>=7){
                        testrow = row;
                        testcol = col;
                    }
                }

            }
            if(row+1<8){
                if(col+1<8){
                    if(boardLogic[row+1][col+1] != "") {
                        if (boardLogic[row + 1][col + 1].charAt(0) == 'P') {
                            return true;
                        }
                    }
                }
                if(col-1>=0){
                    if(boardLogic[row+1][col-1] != "") {
                        if (boardLogic[row + 1][col - 1].charAt(0) == 'P') {
                            return true;
                        }
                    }
                }
            }
            if(row-2>=0 && col+1<8){
                if(boardLogic[row-2][col+1] != "") {
                    if (boardLogic[row - 2][col + 1].charAt(0) == 'N') {
                        return true;
                    }
                }
            }
            if(row-1>=0 && col+2<8){
                if(boardLogic[row-1][col+2] != "") {
                    if (boardLogic[row - 1][col + 2].charAt(0) == 'N') {
                        return true;
                    }
                }
            }
            if(row-2>=0 && col-1>=0){
                if(boardLogic[row-2][col-1] != "") {
                    if (boardLogic[row - 2][col - 1].charAt(0) == 'N') {
                        return true;
                    }
                }
            }
            if(row-1>=0 && col-2>=0){
                if(boardLogic[row-1][col-2] != "") {
                    if (boardLogic[row - 1][col - 2].charAt(0) == 'N') {
                        return true;
                    }
                }
            }
            if(row+1<8 && col+2<8){
                if(boardLogic[row+1][col+2] != "") {
                    if (boardLogic[row + 1][col + 2].charAt(0) == 'N') {
                        return true;
                    }
                }
            }
            if(row+2<8 && col+1<8){
                if(boardLogic[row+2][col+1] != "") {
                    if (boardLogic[row + 2][col + 1].charAt(0) == 'N') {
                        return true;
                    }
                }
            }
            if(row+2<8 && col-1>=0){
                if(boardLogic[row+2][col-1] != "") {
                    if (boardLogic[row + 2][col - 1].charAt(0) == 'N') {
                        return true;
                    }
                }
            }
            if(row-1>=0){
                if(!boardLogic[row-1][col].equals("")) {
                    if (boardLogic[row - 1][col].charAt(0) == 'K') {
                        return true;
                    }
                }
            }
            if(row-1>=0 && col+1<8){
                if(!boardLogic[row-1][col+1].equals("")) {
                    if (boardLogic[row - 1][col+1].charAt(0) == 'K') {
                        return true;
                    }
                }
            }
            if(col+1<8){
                if(!boardLogic[row][col+1].equals("")) {
                    if (boardLogic[row][col+1].charAt(0) == 'K') {
                        return true;
                    }
                }
            }
            if(row+1<8 && col+1<8){
                if(!boardLogic[row+1][col+1].equals("")) {
                    if (boardLogic[row+1][col+1].charAt(0) == 'K') {
                        return true;
                    }
                }
            }
            if(row+1<8){
                if(!boardLogic[row+1][col].equals("")) {
                    if (boardLogic[row+1][col].charAt(0) == 'K') {
                        return true;
                    }
                }
            }
            if(row+1<8 && col-1>=0){
                if(!boardLogic[row+1][col-1].equals("")) {
                    if (boardLogic[row+1][col-1].charAt(0) == 'K') {
                        return true;
                    }
                }
            }
            if(col-1>=0){
                if(!boardLogic[row][col-1].equals("")) {
                    if (boardLogic[row][col-1].charAt(0) == 'K') {
                        return true;
                    }
                }
            }
            if(row-1>=0 && col-1>=0){
                if(!boardLogic[row-1][col-1].equals("")) {
                    if (boardLogic[row-1][col-1].charAt(0) == 'K') {
                        return true;
                    }
                }
            }
            return false;
        }

    }
    public boolean isKingInCheckMate(boolean isWhitePlaying){
        String coord = null;
        String piece = null;
        for(int r = 0; r<8; r++){
            for(int c = 0; c<8; c++){
                if(boardLogic[r][c] != ""){
                    if(isWhitePlaying){
                        if(Character.isLowerCase(boardLogic[r][c].charAt(0))){
                            piece = boardLogic[r][c];
                            coord = Integer.toString(r) + Integer.toString(c);
                        }
                        else{
                            piece = null;
                        }
                    }
                    else{
                        if(Character.isUpperCase(boardLogic[r][c].charAt(0))){
                            piece = boardLogic[r][c];
                            coord = Integer.toString(r) + Integer.toString(c);
                        }
                        else{
                            piece = null;
                        }
                    }

                    if(coord!=null && piece!=null) {
                        System.out.println(piece);
                        coord = coordGuiToLogic(coord, false);
                        coord = reverse(coord);
                        System.out.println(coord);
                        if(Character.toLowerCase(piece.charAt(0)) == 'k') {
                            possibleMoves = king.getPossibleMoves(coord, !isWhitePlaying);
                        }
                        if(Character.toLowerCase(piece.charAt(0)) == 'q'){
                            possibleMoves = queen.getPossibleMoves(coord, !isWhitePlaying);
                        }
                        if(Character.toLowerCase(piece.charAt(0)) == 'b'){
                            possibleMoves = bishop.getPossibleMoves(coord, !isWhitePlaying);
                        }
                        if(Character.toLowerCase(piece.charAt(0)) == 'n'){
                            possibleMoves = knight.getPossibleMoves(coord, !isWhitePlaying);
                        }
                        if(Character.toLowerCase(piece.charAt(0)) == 'r'){
                            possibleMoves = rook.getPossibleMoves(coord, !isWhitePlaying);
                        }
                        if(Character.toLowerCase(piece.charAt(0)) == 'p'){
                            possibleMoves = pawn.getPossibleMoves(coord, !isWhitePlaying);
                        }
                        for(String move: possibleMoves){
                            move = coordGuiToLogic(move, true);
                            tryMove(piece, move);
                            if(isKingInCheck(!isWhitePlaying) == false){
                                revertLogicBoard();
                                return false;
                            }
                            revertLogicBoard();
                        }
                        possibleMoves.clear();
                    }
                }
            }
        }

        return true;



    }
    public ArrayList<String> arrayGuiToLogic(ArrayList<String> array, boolean guiToLogic){
        for (int i = 0; i < array.size(); i++) {
            String newcoord = array.get(i);
            int row = Character.getNumericValue(newcoord.charAt(0));
            int col = Character.getNumericValue(newcoord.charAt(1));
            if(guiToLogic) {
                row--;
            }
            else{
                row++;
            }
            newcoord = Integer.toString(row) + Integer.toString(col);
            array.set(i, newcoord);
        }

        return array;

    }
    public String coordGuiToLogic(String coord, boolean guiToLogic){
        int row = Character.getNumericValue(coord.charAt(0));
        int col = Character.getNumericValue(coord.charAt(1));
        if(guiToLogic){
            row--;
        }
        else{
            row++;
        }
        coord = Integer.toString(row) + Integer.toString(col);
        return coord;
    }
    public String reverse(String coord){
        StringBuilder sb = new StringBuilder(coord);
        return (sb.reverse()).toString();
    }
}
