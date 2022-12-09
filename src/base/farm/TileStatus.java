package base.farm; /*************************************************************************************************************
 This is to certify that this project is our own work, based on our personal efforts in studying and applying
 the concepts learned. We have constructed the functions and their respective algorithms and corresponding
 code by ourselves. The program was run, tested, and debugged by our own efforts. We further certify that
 we have not copied in part or whole or otherwise plagiarized the work of other students and/or persons.

 Baccay, Dominic Luis M.    12108173
 Miranda, Bien Aaron C.     12106773

 This program is made from the improved version of both codes.
 *************************************************************************************************************/

/** This enum represents the status that the Tile is currently in. <p>
 *  ROCKED represents a Tile that contains rocks. <p>
 *  UNPLOWED represents a Tile that is currently not plowed. <p>
 *  PLOWED represents a Tile that is currently plowed, this means the Tile is currently available
 *  to be planted. <p>
 *  SEEDED represents a Tile that currently contains a Crop object. <p>
 *  HARVEST represents a Tile which contains a Crop that is subject for harvesting. <p>
 *  WITHERED represents a Tile that contains a withered crop. <p>
 */
public enum TileStatus {
    ROCKED,
    UNPLOWED,
    PLOWED,
    SEEDED,
    HARVEST,
    WITHERED
}