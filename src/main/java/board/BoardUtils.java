package board;

/**
 *  @author Efe Can Tepe
 *  @version 8.14.2021
 *
 *  This class is created for representing the info of the board
 */
public class BoardUtils {

    private BoardUtils(){
        throw new RuntimeException(); // BoardUtils cannot be instantiable.
    }

    // Black Side 3 ROW
    private static final int START_OF_THE_EIGHTH_ROW = 0;
    private static final int START_OF_THE_SEVENTH_ROW = 8;
    private static final int START_OF_THE_SIXTH_ROW = 16;

    // White Side 3 ROW
    private static final int START_OF_THE_THIRD_ROW = 40;
    private static final int START_OF_THE_SECOND_ROW = 48;
    private static final int START_OF_THE_FIRST_ROW = 56;

    // Other row parts
    private static final int START_OF_THE_FOURTH_ROW = 32;
    private static final int START_OF_THE_FIFTH_ROW = 24;


    public static final int TILES_NUMBER_IN_BOARD = 64; // Number of tiles in the board
    public static final int TILES_NUMBER_IN_COLUMN = 8; // Number of tiles in the column


    public static final boolean [] FIRST_COLUMN = createColumn(0);
    public static final boolean [] EIGHTH_COLUMN = createColumn(7);

    public static final boolean [] EIGHTH_ROW = createRow(START_OF_THE_EIGHTH_ROW);
    public static final boolean [] SEVENTH_ROW = createRow(START_OF_THE_SEVENTH_ROW);
    public static final boolean [] SIXTH_ROW = createRow(START_OF_THE_SIXTH_ROW);

    public static final boolean [] THIRD_ROW = createRow(START_OF_THE_THIRD_ROW);
    public static final boolean [] SECOND_ROW = createRow(START_OF_THE_SECOND_ROW);
    public static final boolean [] FIRST_ROW = createRow(START_OF_THE_FIRST_ROW);

    public static final boolean [] FOURTH_ROW = createRow(START_OF_THE_FOURTH_ROW);
    public static final boolean [] FIFTH_ROW = createRow(START_OF_THE_FIFTH_ROW);



    /**
     *  @param columnNumber
     *  @return
     *
     *  This method is created for creating the column
     */
    private static boolean[] createColumn(int columnNumber){

        if(columnNumber > 8 || columnNumber < 0 ){
            throw new RuntimeException();
        }

        boolean [] result = new boolean [TILES_NUMBER_IN_BOARD];

        for(int a = columnNumber; a < TILES_NUMBER_IN_BOARD; a += TILES_NUMBER_IN_COLUMN){
            result[a] = true;
        }

        return result;
    }

    private static boolean[] createRow(int startNumber){

        boolean [] result = new boolean[TILES_NUMBER_IN_BOARD];

        do{

            result [startNumber] = true;
            startNumber++;

        }while(startNumber % 8 != 0);


        return result;
    }

}
