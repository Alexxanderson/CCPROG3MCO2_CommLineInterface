package base.farm;

/************************************************************************************************************
 This is to certify that this project is our own work, based on our personal efforts in studying and applying
 the concepts learned. We have constructed the functions and their respective algorithms and corresponding
 code by ourselves. The program was run, tested, and debugged by our own efforts. We further certify that
 we have not copied in part or whole or otherwise plagiarized the work of other students and/or persons.

 Baccay, Dominic Luis M.    12108173
 Miranda, Bien Aaron C.     12106773

 This program is made from the improved version of both codes.
 *************************************************************************************************************/

import java.math.BigDecimal;

/** This class represents a tool by its name, cost, and experience gain after each use. toolName refers
 *  to the name of the tool. toolCost refers to the cost of the tool in BigDecimal. experienceGain refers
 *  to the experience the farmer can gain after each use of the tool. <p>
 *  Note that some tools doesn't have a cost, meaning that it can be used by the user immediately.
 */
public class Tool {
    protected String toolName;
    protected BigDecimal toolCost;
    protected double experienceGain;

    /** Creates a Tool object by supplying the toolName, toolCost, and experienceGain.
     *
     * @param toolName          The name of the tool
     * @param toolCost          The cost of the tool
     * @param experienceGain    The experience the Farmer can gain from the tool after each use
     */
    public Tool(String toolName, BigDecimal toolCost, double experienceGain) {
        this.toolName = toolName;
        this.toolCost = toolCost;
        this.experienceGain = experienceGain;
    }

    public Tool() {

    }

    /** A method that returns the name of the tool.
     *
     * @return the name of the tool in String representation
     */
    public String getToolName() { return toolName; }

    /** A method that returns the cost of the tool before it can be used by the player.
     *
     * @return the cost of the tool in BigDecimal representation
     */
    public BigDecimal getToolCost() { return toolCost; }

    /** A method that returns the experience the Farmer can gain from the tool after each use.
     *
     * @return the experience the Farmer can gain from the tool in double representation
     */
    public double getExperienceGain() { return experienceGain; }

    /** A method that prints the basic information of the tool.
     *  It prints the tool's name, cost, and experience gain after each use.
     */
    public void printToolDetails() {
        System.out.printf("\t%12s   Cost: %2.0f   Experience Gain: %.2f\n", ("[" + this.toolName + "]"), this.toolCost, this.experienceGain);
    }
}