package base.farm; /*************************************************************************************************************
 This is to certify that this project is our own work, based on our personal efforts in studying and applying
 the concepts learned. We have constructed the functions and their respective algorithms and corresponding
 code by ourselves. The program was run, tested, and debugged by our own efforts. We further certify that
 we have not copied in part or whole or otherwise plagiarized the work of other students and/or persons.

 Baccay, Dominic Luis M.    12108173
 Miranda, Bien Aaron C.     12106773

 This program is made from the improved version of both codes.
 *************************************************************************************************************/

/** This class represents a block of land that can be used for planting Crops. <p>
 *  crop refers to the crop planted in the Tile object. <p>
 *  tileStatus refers to the current status of the Tile.
 */
public class Tile {

    private Crop crop = null;
    private TileStatus tileStatus;

    private boolean border;

    /** Creates a default Tile object. This initially creates
     *  a Crop object, crop, that initially contains null and tileStatus
     *  that will hold the status of the Tile.
     */
    public Tile() {}

    /** A method that returns the Crop contained by the Tile.
     *  This will return null if the Tile doesn't contain a Crop.
     *
     * @return the crop inside the Tile object in Crop representation.
     */
    public Crop getCrop() { return crop; }

    /** A method that returns the current status of the tile.
     *
     * @return The current status of the tile
     */
    public TileStatus getTileStatus() { return tileStatus; }

    /** A method that modifies the Crop inside the Tile object.
     *
     * @param crop      The Crop inside the Tile object
     */
    public void setCrop(Crop crop) { this.crop = crop; }

    /** A method that modifies the current status of the Tile object.
     *
     * @param tileStatus    The current status of the Tile
     */
    public void setTileStatus(TileStatus tileStatus) { this.tileStatus = tileStatus; }

    public boolean isBorder() {
        return border;
    }

    public void setBorder(boolean border) {
        this.border = border;
    }
}