package base.farm;/* Alpha Build Version 1.0  */

/*************************************************************************************************************
 This is to certify that this project is our own work, based on our personal efforts in studying and applying
 the concepts learned. We have constructed the functions and their respective algorithms and corresponding
 code by ourselves. The program was run, tested, and debugged by our own efforts. We further certify that
 we have not copied in part or whole or otherwise plagiarized the work of other students and/or persons.

 Baccay, Dominic Luis M.    12108173
 Miranda, Bien Aaron C.     12106773

 This program is made from the improved version of both codes.
 *************************************************************************************************************/

import base.farm.crops.*;
import base.farm.tools.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

/** This is the main class of the game.
 */
public class MyFarm {
    private static final Scanner SCAN = new Scanner(System.in);
    private static Boolean isRunning = true;
    private static Boolean isDay = true;
    private static Farmer FARMER = new Farmer();
    private static Board FARM_BOARD = new Board();
    //asdasdasdasd
    private static final ArrayList<Crop> SEED_LIST = new ArrayList<>();
    static {
        // index 0
        SEED_LIST.add(new Turnip());
        SEED_LIST.add(new Carrot());
        SEED_LIST.add(new Potato());
        SEED_LIST.add(new Rose());
        SEED_LIST.add(new Tulips());
        SEED_LIST.add(new Sunflower());
        SEED_LIST.add(new Mango());
        SEED_LIST.add(new Apple());

        // other crops to follow after MCO1 Phase
    }

    private static final ArrayList<Tool> TOOL_INVENTORY = new ArrayList<>();
    static {
        TOOL_INVENTORY.add(new Plow());           // index 0
        TOOL_INVENTORY.add(new WateringCan());    // index 1
        TOOL_INVENTORY.add(new Fertilizer());
        TOOL_INVENTORY.add(new Pickaxe());
        TOOL_INVENTORY.add(new Shovel());

        // other tools to follow after MCO1 Phase
    }

    public static void main(String[] args) {
        welcomeUser();
        char gameEnd;
        do {
            FARMER = new Farmer();
            FARM_BOARD = new Board();
            isRunning = true;
            isDay = true;

            while (isRunning) {
                while (isDay) {
                    clearScreen();
                    checkConditions();
                    if (isDay == false) {
                        break;
                    }
                    FARMER.printFarmerDetails();
                    FARM_BOARD.printFarmLot();
                    String command = getCommand();
                    executeCommand(command);
                }
                FARM_BOARD.incrementDay();
                isDay = true;
            }

            System.out.println("Game Over: Do you want to replay again?");
            System.out.println("[Y]Yes [N] No");
            gameEnd = SCAN.nextLine().charAt(0);
        } while (gameEnd == 'Y' || gameEnd == 'y');

    }

    /** An introductory method that prints the Welcome message of the game together with the name
     *  of the developers and the goals of the game.
     */
    private static void welcomeUser() {
        clearScreen();
        System.out.println("""
                \n\tWelcome to MyFarm!
                \n\tDevelopers:
                \tDominic Luis Baccay
                \tBien Aaron Miranda
                \n\tGoals:
                \t- Plow a Tile, Water and Harvest a Turnip!
                \t- Earn Object Coins!
                \t- Have Fun!
                """);

        System.out.println("\t[PRESS ENTER TO CONTINUE]");
        SCAN.nextLine();
    }

    /** A method that prints the available commands in the game. This is
     *  a method connected to .executeCommand().
     *  Commands include:
     *  Use Tool, Plant Seed, Harvest Tile, View Tile, End Day, and
     *  Quit Game.
     *
     * @return the user's input
     */
    private static String getCommand() {
        System.out.print("""
                \n\tGame Controls:
                \t[T] Use Tool     [P] Plant Seed    [H] Harvest Tile  [R] Register
                \t[V] View Tile    [E] End Day       [Q] Quit Game
                """);
        System.out.print("\n\tEnter Command: ");
        return SCAN.nextLine();
    }

    /** A method that executes the user's selected command. This is connected to
     *  .getCommand().
     *  This is where all the commands/actions takes place. It calls all the necessary commands
     *  needed for an execution of a certain action.
     *  If the user chose an unknown command, it will tell that it is an unknown command and will reprompt
     *  to .getCommand().
     *
     * @param command The selected command of the user based from their input on .getCommand().
     */
    private static void executeCommand(String command) {
        switch (command.toUpperCase()) {
            case "T" -> { // USE TOOL
                try {
                    System.out.println("\n\tAvailable Tools:");
                    /* Print the available tools and its respective details. */
                    TOOL_INVENTORY.forEach(Tool -> Tool.printToolDetails());

                    /* Ask farmer for the tool & position of the tile they want to manipulate. */
                    System.out.print("\n\tEnter Tool & Position (ex. PLOW, 1): ");
                    String[] toolInput = splitString(SCAN.nextLine());
                    /* toolInput = PLOW, 1
                     * toolInput[0] = PLOW
                     * toolInput[1] = 1
                     */

                    /* Calls the appropriate class in regard to the farmer's toolInput */
                    switch (toolInput[0].toUpperCase()) {
                        /* If plow, calls plowTile() which will turn the unplowed tile into plowed. */
                        case "PLOW" -> FARMER.plowTile(TOOL_INVENTORY.get(0),
                                FARM_BOARD.getPlot(Integer.parseInt(toolInput[1])));
                        /* If water, calls waterCrop() which increments the timesWatered of a crop within a tile. */
                        case "WATER" -> FARMER.waterCrop(TOOL_INVENTORY.get(1),
                                FARM_BOARD.getPlot(Integer.parseInt(toolInput[1])));
                        case "FERTILIZE" -> FARMER.fertilizeCrop(TOOL_INVENTORY.get(1),
                                FARM_BOARD.getPlot(Integer.parseInt(toolInput[1])));
                        case "DIG" -> FARMER.removeRock(TOOL_INVENTORY.get(3),
                                FARM_BOARD.getPlot(Integer.parseInt(toolInput[1])));
                        case "SHOVEL" -> FARMER.shovelWithered(TOOL_INVENTORY.get(4),
                                FARM_BOARD.getPlot(Integer.parseInt(toolInput[1])));
                    }

                    /* Prints the updated tile details after the tool was used. */
                    FARM_BOARD.printTileDetails((Integer.parseInt(toolInput[1])));
                    System.out.println("\t[PRESS ENTER TO CONTINUE]");
                    SCAN.nextLine();
                } catch (Exception e) { /* Error checking, catches any error the code might encounter. */
                    System.out.println("\n\tOops, that's an invalid input!");
                    System.out.println("\n\t[PRESS ENTER TO CONTINUE]");
                    SCAN.nextLine();
                }
            }
            case "V" -> { // VIEW A TILE
                try {
                    /* Ask farmer for the position of the tile they want to view. */
                    System.out.print("\n\tSelect Tile to View (ex. 1): ");
                    int viewKey = SCAN.nextInt();

                    if (viewKey >= 1 && viewKey <= 50)
                        /* Prints the details of a specific tile based on viewKey. */
                        FARM_BOARD.printTileDetails(viewKey);
                    else
                        System.out.print("\n\tOops, that's out of range!");

                    System.out.println("\t[PRESS ENTER TO CONTINUE]");
                    SCAN.nextLine();  SCAN.nextLine();
                } catch (Exception e) { /* Error checking, catches any error the code might encounter. */
                    System.out.print("\n\tOops, that's an invalid input!");
                    System.out.println("\n\t[PRESS ENTER TO CONTINUE]");
                    SCAN.nextLine(); SCAN.nextLine();
                }
            }
            case "P" -> {
                try {
                    /* Ask farmer for the position of the tile they want to plant on, only works on plowed tiles. */
                    System.out.print("\n\tSelect Tile : ");
                    int plantKey = SCAN.nextInt(); SCAN.nextLine();

                    switch (FARM_BOARD.getPlot(plantKey).getTileStatus()) {
                        case PLOWED -> {
                            try {
                                /* Prints on the terminal the crop's detail for the convenience of the farmer */
                                System.out.println("\n\tAvailable Crops:");

                                SEED_LIST.forEach(Crop -> Crop.printCropDetails(FARMER.getFarmerStatus()));


                                /* Ask farmer what crop they would want to buy and immediately plant on the tile. */
                                System.out.print("\n\tSelect Crop to Buy & Plant (ex. TURNIP): ");
                                String toPlant = SCAN.nextLine();

                                /* Assigns a cropKey to the farmer's input, which is used in accessing the inventory */
                                int seedKey = switch (toPlant.toUpperCase()) {
                                    case "TURNIP" -> 0;
                                    case "CARROT" -> 1;
                                    case "POTATO" -> 2;
                                    case "ROSE" -> 3;
                                    case "TULIPS" -> 4;
                                    case "SUNFLOWER" -> 5;
                                    case "MANGO" -> 6;
                                    case "APPLE" -> 7;
                                    default -> -1;
                                };

                                /* Invokes plantSeed() method of farmer */
                                FARMER.plantSeed(SEED_LIST.get(seedKey), FARM_BOARD.getFARM_LOT().get(plantKey), FARM_BOARD.getDayCount());
                            } catch (Exception e) { /* Error checking, catches any error the code might encounter. */
                                System.out.println("\n\tInvalid Crop Input!");
                            }
                        }
                        case SEEDED -> System.out.println("\n\tThis tile is already seeded!");
                        default -> System.out.println("\n\tYou can only plant on a plowed tile!");
                    }

                    FARM_BOARD.printTileDetails(plantKey);
                    System.out.println("\t[PRESS ENTER TO CONTINUE]"); SCAN.nextLine();
                } catch (Exception e) { /* Error checking, catches any error the code might encounter. */
                    System.out.println("\n\tInvalid Tile Input!");
                    System.out.println("\n\t[PRESS ENTER TO CONTINUE]"); SCAN.nextLine(); SCAN.nextLine();
                }
            }
            case "H" -> { // HARVEST A TILE
                if (FARM_BOARD.containsHarvest()) { /* Harvest command is only accessible when there
                                                    * are harvestable crops present on the farm Lot
                                                    */
                    try {
                        /* Ask farmer what tile they want to harvest */
                        System.out.print("\n\tSelect Tile to Harvest (ex. 1): ");
                        int harvestKey = SCAN.nextInt();

                        /* Validations when farmer accesses different tiles, .harvestCrop() only triggered
                        * when a tile and its crop is ready for harvest.
                        */
                        switch (FARM_BOARD.getPlot(harvestKey).getTileStatus()) {
                            case HARVEST -> FARMER.harvestCrop(FARM_BOARD.getPlot(harvestKey));
                            case SEEDED -> System.out.println("\n\tThis seed is not ready for harvest");
                            case UNPLOWED, PLOWED, WITHERED -> System.out.println("\n\tYou cannot harvest a "
                                    + FARM_BOARD.getPlot(harvestKey).getTileStatus().toString().toLowerCase()
                                    + " tile!");
                        }

                        System.out.println("\n\t[PRESS ENTER TO CONTINUE]");
                        SCAN.nextLine(); SCAN.nextLine();
                    } catch (Exception e) { /* Error checking, catches any error the code might encounter. */
                        System.out.print("\n\tOops, that's an invalid input!");
                        System.out.println("\n\t[PRESS ENTER TO CONTINUE]");
                        SCAN.nextLine(); SCAN.nextLine();
                    }
                } else {
                    System.out.println("\n\tThere are no tiles ready for harvest yet!");
                    System.out.println("\n\t[PRESS ENTER TO CONTINUE]");
                    SCAN.nextLine();
                }
            }
            case "R" -> {
                System.out.print("\n\t Do you want to Upgrade? ");
                System.out.println("Your Level: " + FARMER.getFarmerStatus());
                System.out.println("Do you want to Register for a higher rank: ");
                System.out.println("\t[1] Yes   [2]No ");
                int choice = SCAN.nextInt();
                if (choice == 1){
                    FARMER.rankUp();
                }

            }
            case "E" -> endDay();
            case "Q" -> quitGame();
            default -> {
                System.out.println("\n\tOops, that's an unknown command!");
                System.out.println("\n\t[PRESS ENTER TO CONTINUE]");
                SCAN.nextLine();
            }
        }
    }

    /** A method that checks all conditions and validations of the farm Lot before simulating the current day */
    private static void checkConditions() {
        FARM_BOARD.checkForHarvest();
        FARMER.checkFarmerLevel();

        boolean gameOver = true;

        // GAME OVER
        // if all tiles are withered

        if (FARMER.getObjectCoins().compareTo(BigDecimal.valueOf(5).subtract(FARMER.getFarmerStatus().getSeedCostReduction())) < 0){
            for (int key: FARM_BOARD.getFARM_LOT().keySet()){
                if (FARM_BOARD.getFARM_LOT().get(key).getTileStatus() == TileStatus.SEEDED || FARM_BOARD.getFARM_LOT().get(key).getTileStatus() == TileStatus.HARVEST) {
                    gameOver = false;
                    break;
                }
            }
        } else {
            for (int key: FARM_BOARD.getFARM_LOT().keySet()){
                if (FARM_BOARD.getFARM_LOT().get(key).getTileStatus() != TileStatus.WITHERED) {
                    gameOver = false;
                    break;
                }
            }
        }
        if (gameOver == true){
            System.out.println("game over");
            isDay = false;
            isRunning = false;
        }


        // dont have any active growing crops and no money


        // other conditions to-follow after MCO1 Phase
    }

    /** A method that splits the string into two. This splits the string around
     *  the comma and a space ", ".
     * @param toSplit  The String subject for splitting
     * @return  The String array of size 2 that contains the two Strings on its respective index.
     */
    private static String[] splitString(String toSplit) { return toSplit.split(", ", 2); }

    /** A method that ends a simulation of a day and informs the user of such. */
    private static void endDay() {
        isDay = false;
        isRunning = true;

        System.out.println("\n\tEnding day...\n");

        if (FARM_BOARD.getDayCount() > 1)
            System.out.println("\t" + FARM_BOARD.getDayCount() + " days has passed...");
        else
            System.out.println("\t1 day has passed...");

        System.out.println("\n\t[PRESS ENTER TO CONTINUE]");
        SCAN.nextLine();
    }

    /** A method that terminates the game instance and informs the user of such. */
    private static void quitGame() {
        isDay = false;
        isRunning = false;
        System.out.println("\n\tQuitting game...\n");
        System.out.println("\tThank you for playing MyFarm!\n");
        SCAN.close();
    }

    /** A method that clears the terminal.
     *  When invoked, it searches for the top-most line of the command, and clears such,
     *  essentially clearing the terminal screen.
     */
    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void gameOverLog(){

    }
}