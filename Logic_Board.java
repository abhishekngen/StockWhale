import javafx.scene.image.ImageView;


public class Logic_Board {

    private Logic_Board(){}

    private static Logic_Board instance = null;

    public static Logic_Board getInstance() {
        if(instance == null)
        {
            instance = new Logic_Board();
        }
        return instance;
    }

    public String[][] boardLogic = new String[][]
            {
                    {"r1", "b1", "n1", "q", "k", "n2", "b2", "r2"},
                    {"p1", "p2", "p3", "p4", "p5", "p6", "p7", "p8"},
                    {"", "", "", "", "", "", "", ""},
                    {"", "", "", "", "", "", "", ""},
                    {"", "", "", "", "", "", "", ""},
                    {"", "", "", "", "", "", "", ""},
                    {"P1", "P2", "P3", "P4", "P5", "P6", "P7", "P8"},
                    {"R1", "B1", "N1", "Q", "K", "N2", "B2", "R2"}
            };

    public void makeMove(String piece, String coord)
    {
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
        Main_Display.gui.makeMove(piece, coord);

    }

    public int getPos(ImageView pieceImg)
    {
        String piece = Main_Display.gui.imgToString.get(pieceImg);
        int coord = 0;
        for(int row = 0; row<8; row++)
        {
            for(int col = 0; col<8; col++)
            {
                if(boardLogic[row][col] == piece)
                {
                    coord = Integer.parseInt(Integer.toString(row) + Integer.toString(col));
                }
            }
        }

        return coord;

    }
}
