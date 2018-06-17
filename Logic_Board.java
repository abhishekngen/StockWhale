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
    /*<Rectangle fx:id="rect1" arcHeight="5.0" arcWidth="5.0" fill="WHITE" height="51.0" stroke="WHITE" strokeType="INSIDE" width="51.0" GridPane.columnIndex="1" GridPane.rowIndex="4">
               <GridPane.margin>
                  <Insets left="3.0" />
               </GridPane.margin></Rectangle>
            <Rectangle fx:id="rect12" arcHeight="5.0" arcWidth="5.0" fill="WHITE" height="51.0" stroke="WHITE" strokeType="INSIDE" width="51.0" GridPane.columnIndex="2" GridPane.rowIndex="1">
               <GridPane.margin>
                  <Insets left="3.0" />
               </GridPane.margin>
            </Rectangle>
            <Rectangle fx:id="rect13" arcHeight="5.0" arcWidth="5.0" fill="WHITE" height="51.0" stroke="WHITE" strokeType="INSIDE" width="51.0" GridPane.columnIndex="4" GridPane.rowIndex="1">
               <GridPane.margin>
                  <Insets left="3.0" />
               </GridPane.margin>
            </Rectangle>
            <Rectangle fx:id="rect14" arcHeight="5.0" arcWidth="5.0" fill="WHITE" height="51.0" stroke="WHITE" strokeType="INSIDE" width="51.0" GridPane.columnIndex="6" GridPane.rowIndex="1">
               <GridPane.margin>
                  <Insets left="3.0" />
               </GridPane.margin>
            </Rectangle>
            <Rectangle fx:id="rect15" arcHeight="5.0" arcWidth="5.0" fill="WHITE" height="51.0" stroke="WHITE" strokeType="INSIDE" width="51.0" GridPane.columnIndex="1" GridPane.rowIndex="2">
               <GridPane.margin>
                  <Insets left="3.0" />
               </GridPane.margin>
            </Rectangle>
            <Rectangle fx:id="rect16" arcHeight="5.0" arcWidth="5.0" fill="WHITE" height="51.0" stroke="WHITE" strokeType="INSIDE" width="51.0" GridPane.columnIndex="3" GridPane.rowIndex="2">
               <GridPane.margin>
                  <Insets left="3.0" />
               </GridPane.margin>
            </Rectangle>
            <Rectangle fx:id="rect17" arcHeight="5.0" arcWidth="5.0" fill="WHITE" height="51.0" stroke="WHITE" strokeType="INSIDE" width="51.0" GridPane.columnIndex="5" GridPane.rowIndex="2">
               <GridPane.margin>
                  <Insets left="3.0" />
               </GridPane.margin>
            </Rectangle>
            <Rectangle fx:id="rect18" arcHeight="5.0" arcWidth="5.0" fill="WHITE" height="51.0" stroke="WHITE" strokeType="INSIDE" width="51.0" GridPane.columnIndex="7" GridPane.rowIndex="2">
               <GridPane.margin>
                  <Insets left="3.0" />
               </GridPane.margin>
            </Rectangle>
            <Rectangle fx:id="rect110" arcHeight="5.0" arcWidth="5.0" fill="WHITE" height="51.0" stroke="WHITE" strokeType="INSIDE" width="51.0" GridPane.columnIndex="2" GridPane.rowIndex="3">
               <GridPane.margin>
                  <Insets left="3.0" />
               </GridPane.margin>
            </Rectangle>
            <Rectangle fx:id="rect111" arcHeight="5.0" arcWidth="5.0" fill="WHITE" height="51.0" stroke="WHITE" strokeType="INSIDE" width="51.0" GridPane.columnIndex="4" GridPane.rowIndex="3">
               <GridPane.margin>
                  <Insets left="3.0" />
               </GridPane.margin>
            </Rectangle>
            <Rectangle fx:id="rect112" arcHeight="5.0" arcWidth="5.0" fill="WHITE" height="51.0" stroke="WHITE" strokeType="INSIDE" width="51.0" GridPane.columnIndex="6" GridPane.rowIndex="3">
               <GridPane.margin>
                  <Insets left="3.0" />
               </GridPane.margin>
            </Rectangle>
            <Rectangle fx:id="rect113" arcHeight="5.0" arcWidth="5.0" fill="WHITE" height="51.0" stroke="WHITE" strokeType="INSIDE" width="51.0" GridPane.columnIndex="3" GridPane.rowIndex="4">
               <GridPane.margin>
                  <Insets left="3.0" />
               </GridPane.margin>
            </Rectangle>
            <Rectangle fx:id="rect114" arcHeight="5.0" arcWidth="5.0" fill="WHITE" height="51.0" stroke="WHITE" strokeType="INSIDE" width="51.0" GridPane.columnIndex="5" GridPane.rowIndex="4">
               <GridPane.margin>
                  <Insets left="3.0" />
               </GridPane.margin>
            </Rectangle>
            <Rectangle fx:id="rect115" arcHeight="5.0" arcWidth="5.0" fill="WHITE" height="51.0" stroke="WHITE" strokeType="INSIDE" width="51.0" GridPane.columnIndex="7" GridPane.rowIndex="4">
               <GridPane.margin>
                  <Insets left="3.0" />
               </GridPane.margin>
            </Rectangle>
            <Rectangle fx:id="rect117" arcHeight="5.0" arcWidth="5.0" fill="WHITE" height="51.0" stroke="WHITE" strokeType="INSIDE" width="51.0" GridPane.columnIndex="2" GridPane.rowIndex="5">
               <GridPane.margin>
                  <Insets left="3.0" />
               </GridPane.margin>
            </Rectangle>
            <Rectangle fx:id="rect118" arcHeight="5.0" arcWidth="5.0" fill="WHITE" height="51.0" stroke="WHITE" strokeType="INSIDE" width="51.0" GridPane.columnIndex="4" GridPane.rowIndex="5">
               <GridPane.margin>
                  <Insets left="3.0" />
               </GridPane.margin>
            </Rectangle>
            <Rectangle fx:id="rect119" arcHeight="5.0" arcWidth="5.0" fill="WHITE" height="51.0" stroke="WHITE" strokeType="INSIDE" width="51.0" GridPane.columnIndex="6" GridPane.rowIndex="5">
               <GridPane.margin>
                  <Insets left="3.0" />
               </GridPane.margin>
            </Rectangle>
            <Rectangle fx:id="rect120" arcHeight="5.0" arcWidth="5.0" fill="WHITE" height="51.0" stroke="WHITE" strokeType="INSIDE" width="51.0" GridPane.columnIndex="1" GridPane.rowIndex="6">
               <GridPane.margin>
                  <Insets left="3.0" />
               </GridPane.margin>
            </Rectangle>
            <Rectangle fx:id="rect121" arcHeight="5.0" arcWidth="5.0" fill="WHITE" height="51.0" stroke="WHITE" strokeType="INSIDE" width="51.0" GridPane.columnIndex="3" GridPane.rowIndex="6">
               <GridPane.margin>
                  <Insets left="3.0" />
               </GridPane.margin>
            </Rectangle>
            <Rectangle fx:id="rect122" arcHeight="5.0" arcWidth="5.0" fill="WHITE" height="51.0" stroke="WHITE" strokeType="INSIDE" width="51.0" GridPane.columnIndex="5" GridPane.rowIndex="6">
               <GridPane.margin>
                  <Insets left="3.0" />
               </GridPane.margin>
            </Rectangle>
            <Rectangle fx:id="rect123" arcHeight="5.0" arcWidth="5.0" fill="WHITE" height="51.0" stroke="WHITE" strokeType="INSIDE" width="51.0" GridPane.columnIndex="7" GridPane.rowIndex="6">
               <GridPane.margin>
                  <Insets left="3.0" />
               </GridPane.margin>
            </Rectangle>
            <Rectangle fx:id="rect125" arcHeight="5.0" arcWidth="5.0" fill="WHITE" height="51.0" stroke="WHITE" strokeType="INSIDE" width="51.0" GridPane.columnIndex="2" GridPane.rowIndex="7">
               <GridPane.margin>
                  <Insets left="3.0" />
               </GridPane.margin>
            </Rectangle>
            <Rectangle fx:id="rect126" arcHeight="5.0" arcWidth="5.0" fill="WHITE" height="51.0" stroke="WHITE" strokeType="INSIDE" width="51.0" GridPane.columnIndex="4" GridPane.rowIndex="7">
               <GridPane.margin>
                  <Insets left="3.0" />
               </GridPane.margin>
            </Rectangle>
            <Rectangle fx:id="rect127" arcHeight="5.0" arcWidth="5.0" fill="WHITE" height="51.0" stroke="WHITE" strokeType="INSIDE" width="51.0" GridPane.columnIndex="6" GridPane.rowIndex="7">
               <GridPane.margin>
                  <Insets left="3.0" />
               </GridPane.margin>
            </Rectangle>
            <Rectangle fx:id="rect128" arcHeight="5.0" arcWidth="5.0" fill="WHITE" height="51.0" stroke="WHITE" strokeType="INSIDE" width="51.0" GridPane.columnIndex="1" GridPane.rowIndex="8">
               <GridPane.margin>
                  <Insets left="3.0" />
               </GridPane.margin>
            </Rectangle>
            <Rectangle fx:id="rect129" arcHeight="5.0" arcWidth="5.0" fill="WHITE" height="51.0" stroke="WHITE" strokeType="INSIDE" width="51.0" GridPane.columnIndex="3" GridPane.rowIndex="8">
               <GridPane.margin>
                  <Insets left="3.0" />
               </GridPane.margin>
            </Rectangle>
            <Rectangle fx:id="rect130" arcHeight="5.0" arcWidth="5.0" fill="WHITE" height="51.0" stroke="WHITE" strokeType="INSIDE" width="51.0" GridPane.columnIndex="5" GridPane.rowIndex="8">
               <GridPane.margin>
                  <Insets left="3.0" />
               </GridPane.margin>
            </Rectangle>
            <Rectangle fx:id="rect131" arcHeight="5.0" arcWidth="5.0" fill="WHITE" height="51.0" stroke="WHITE" strokeType="INSIDE" width="51.0" GridPane.columnIndex="7" GridPane.rowIndex="8">
               <GridPane.margin>
                  <Insets left="3.0" />
               </GridPane.margin>
            </Rectangle>*/



}
