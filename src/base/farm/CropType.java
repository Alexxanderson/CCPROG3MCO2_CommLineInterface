package base.farm; /*************************************************************************************************************
 This is to certify that this project is our own work, based on our personal efforts in studying and applying
 the concepts learned. We have constructed the functions and their respective algorithms and corresponding
 code by ourselves. The program was run, tested, and debugged by our own efforts. We further certify that
 we have not copied in part or whole or otherwise plagiarized the work of other students and/or persons.

 Baccay, Dominic Luis M.    12108173
 Miranda, Bien Aaron C.     12106773

 This program is made from the improved version of both codes.
 *************************************************************************************************************/

/** This enum represents the type of the crops available within the game.
 *  This will affect certain behaviors in the game such as bonus Object Coins gained and
 *  Tile usage based from the type of the crop.<p></p>
 *  ROOT_CROP represents certain crops in the game which contains no special characteristics.
 *  <p></p>
 *  FLOWER represents certain crops in the game which contains an additional bonus of 10% to its final harvest price.
 *  <p></p>
 *  FRUIT_TREE represent certain crops in the game which takes up 3x3 Tiles in the game
 */
public enum CropType {
    ROOT_CROP("Root Crop"),
    FLOWER("Flower"),
    FRUIT_TREE("Fruit Tree");

    CropType(String cropType) {
        this.cropType = cropType;
    }

    private final String cropType;
    public String getCropType() { return cropType; }
}