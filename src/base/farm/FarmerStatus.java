package base.farm; /*************************************************************************************************************
 This is to certify that this project is our own work, based on our personal efforts in studying and applying
 the concepts learned. We have constructed the functions and their respective algorithms and corresponding
 code by ourselves. The program was run, tested, and debugged by our own efforts. We further certify that
 we have not copied in part or whole or otherwise plagiarized the work of other students and/or persons.

 Baccay, Dominic Luis M.    12108173
 Miranda, Bien Aaron C.     12106773

 This program is made from the improved version of both codes.
 *************************************************************************************************************/

import java.math.BigDecimal;

/** This is an enum that represents the rank/type of the Farmer that will determine
 *  the benefits of the Farmer.
 *  Each type has a degree of benefits, LEGENDARY having the most amount of benefits and DEFAULT being the starting
 *  rank/type with having no benefits.<p>
 *
 */
public enum FarmerStatus {

    DEFAULT("DEFAULT", 0, BigDecimal.valueOf(0), BigDecimal.valueOf(0),
            0, 0, BigDecimal.valueOf(0)),


    REGISTERED("REGISTERED", 5, BigDecimal.valueOf(1), BigDecimal.valueOf(1),
            0, 0, BigDecimal.valueOf(200)),


    DISTINGUISHED("DISTINGUISHED", 10, BigDecimal.valueOf(2), BigDecimal.valueOf(2),
            1, 0, BigDecimal.valueOf(300)),


    LEGENDARY("LEGENDARY", 15, BigDecimal.valueOf(4), BigDecimal.valueOf(3),
            2, 1, BigDecimal.valueOf(400));

    private String farmerType;
    private int levelRequirement;
    private BigDecimal earningBonus;
    private BigDecimal seedCostReduction;
    private int waterLimitBonus;
    private int fertilizerLimitBonus;
    private BigDecimal registrationFee;

    /** Constructor class that creates/modifies the status of the Farmer and its benefits
     *
     * @param farmerType            can be of DEFAULT, REGISTERED, DISTINGUISHED, or LEGENDARY
     * @param levelRequirement      level requirement needed to register for above values
     * @param earningBonus          earning bonus gained from respective farmer types
     * @param seedCostReduction     seed cost discounts gained from respective farmer types
     * @param waterLimitBonus       water limit bonus gained from respective farmer types
     * @param fertilizerLimitBonus  fertilizer limit bonus gained from respective farmer types
     * @param registrationFee       the amount needed to register for respective farmer types
     */
    FarmerStatus(String farmerType, int levelRequirement,
                 BigDecimal earningBonus, BigDecimal seedCostReduction,
                 int waterLimitBonus, int fertilizerLimitBonus, BigDecimal registrationFee) {
        this.farmerType = farmerType;
        this. levelRequirement = levelRequirement;
        this.earningBonus = earningBonus;
        this.seedCostReduction = seedCostReduction;
        this.waterLimitBonus = waterLimitBonus;
        this.fertilizerLimitBonus = fertilizerLimitBonus;
        this.registrationFee = registrationFee;
    }

    /** A getter method that returns the current type of the Farmer.
     *
     * @return the current type of the Farmer in String representation
     */
    public String getFarmerType() { return farmerType; }

    /** A getter method that returns the level requirement of a farmer type.
     *
     * @return the level requirement needed for a specific farmer type
     */
    public int getLevelRequirement() { return levelRequirement; }

    /** Returns the current bonus earnings of the Farmer for harvesting
     *
     * @return The bonus earnings the Farmer can get when harvesting in BigDecimal representation
     */
    public BigDecimal getEarningBonus() { return earningBonus; }

    /** Returns the value that will be reduced to the seed cost of a Crop
     *
     * @return The value that will be deducted to the seed cost of a Crop in BigDecimal representation
     */
    public BigDecimal getSeedCostReduction() { return seedCostReduction; }

    /** Returns the value that will increment the water bonus limit of a Crop
     *
     * @return The value that will be added to the water bonus limit of a Crop in 'int' representation
     */
    public int getWaterLimitBonus() { return waterLimitBonus; }

    /** Returns the value that will be used to increment the fertilizer bonus limit of a Crop
     *
     * @return The value that will be added to the fertilizer limit bonus of a Crop in 'int' representation
     */
    public int getFertilizerLimitBonus() { return fertilizerLimitBonus; }

    /** A getter method that returns the registration fee of a farmer type.
     *
     * @return the registration fee needed for a specific farmer type
     */
    public BigDecimal getRegistrationFee() { return registrationFee; }
}