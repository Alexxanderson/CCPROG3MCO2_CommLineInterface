package base.farm;
import java.io.File;
import java.util.HashMap;
import java.util.Scanner;
//asdadsasd
public class Board {

    /* a 10x5 farm Lot represented in a size 50 tile array */
    private final HashMap<Integer, Tile> FARM_LOT = new HashMap<>();
    private int dayCount = 1;

//    public Board() {
//        /* initializing the base values of a Tile, where it will have no crop and is unplowed */
//        for (int position = 1; position <= 50; position++) {
//            Tile plot = new Tile();
//            plot.setBorder(position < 6 || position > 45 || position % 5 == 0 || position % 5 == 1);
//            plot.setTileStatus(TileStatus.UNPLOWED);
//            this.FARM_LOT.put(position, plot);
//        }
//    }

    public Board() {
        // farmLot initialization with ROCKED and UNPLOWED tiles
        int rocks = 0, scOne = 0, scTwo = 0, scThree = 0, scFour = 0, scFive = 0;
        // read "rocks.txt" file
        // assume input file is always in a valid format
        try {
            File rockFile = new File("resources/game_phase/rocks.txt");
            Scanner scanFile = new Scanner(rockFile);

            // assume there are six lines of data within "rocks.txt"
            rocks = Integer.parseInt(scanFile.nextLine());
            scOne = Integer.parseInt(scanFile.nextLine());
            scTwo = Integer.parseInt(scanFile.nextLine());
            scThree = Integer.parseInt(scanFile.nextLine());
            scFour = Integer.parseInt(scanFile.nextLine());
            scFive = Integer.parseInt(scanFile.nextLine());

            scanFile.close();
        } catch (Exception exception) {
            exception.getStackTrace();
        }

        initializeColumn(1, 46, scOne);
        initializeColumn(2, 47, scTwo);
        initializeColumn(3, 48, scThree);
        initializeColumn(4, 49, scFour);
        initializeColumn(5, 50, scFive);
    }

    // (all-private) methods for the generating of ROCKED and UNPLOWED tiles for farmLot

    private void initializeColumn(int min, int max, int scatter) {
        int rockCount;
        do {
            rockCount = scatterRocks(min, max);
        } while (rockCount != scatter);
    }

    private int scatterRocks(int min, int max) {
        int rockCount = 0;
        for (int position = min; position <= max; ) {
            if ((int) Math.floor(Math.random()*(2-1+1)+1) == 1) { // UNPLOWED IF 1
                Tile plot = new Tile();
                plot.setBorder(position < 6 || position > 45 || position % 5 == 0 || position % 5 == 1);
                plot.setTileStatus(TileStatus.UNPLOWED);
                this.FARM_LOT.put(position, plot);
                position += 5;
            } else if ((int) Math.floor(Math.random()*(2-1+1)+1) == 2) { // ROCKED IF 2
                Tile plot = new Tile();
                plot.setBorder(position < 6 || position > 45 || position % 5 == 0 || position % 5 == 1);
                plot.setTileStatus(TileStatus.ROCKED);
                this.FARM_LOT.put(position, plot);
                rockCount++;
                position += 5;
            }
        }
        return rockCount;
    }

    public Tile getPlot(int position) { return this.FARM_LOT.get(position); }

    public int getDayCount() { return dayCount; }

    public HashMap<Integer, Tile> getFARM_LOT() {
        return FARM_LOT;
    }

    public void incrementDay() { this.dayCount++; }

    public void printFarmLot() {
        int position = 1, row, col;

        System.out.printf("\t[DAY #%d]\n\n", this.dayCount);

        while (position <= 50) {
            for (row = 0; row < 10; row++) {
                for (col = 0; col < 5; col++) {
                    /* assigned tileOutput a string value based on it's a tile's tileStatus */
                    String tileOutput = switch (this.FARM_LOT.get(position).getTileStatus()) {
                        case ROCKED -> "ROCKED";
                        case UNPLOWED -> "UNPLOWED";
                        case PLOWED -> "PLOWED";
                        case SEEDED -> this.FARM_LOT.get(position).getCrop().getSeedName();
                        case HARVEST -> "HARVEST";
                        case WITHERED -> "WITHERED";
                    };
                    /* prints the tile in table-like fashion */
                    System.out.printf("\t%-11s %-2d", ("[" + tileOutput + "]"), (position ));
                    position++; // increments position
                }
                System.out.print("\n");
            }
        }
    }

    public boolean containsHarvest() {
        /* checks if there are harvestable tiles in the farmLot */
        boolean found = false;
        for (int position = 1; position <= 50; position++) {
            if (this.FARM_LOT.get(position).getTileStatus().equals(TileStatus.HARVEST)) {
                found = true;
                break;
            }
        }
        return found;
    }

    public boolean containsRocks() {
        /* checks if there are rocked tiles in the farmLot */
        boolean found = false;
        for (int position = 1; position <= 50; position++) {
            if (this.FARM_LOT.get(position).getTileStatus().equals(TileStatus.ROCKED)) {
                found = true;
                break;
            }
        }
        return found;
    }

    public void checkForHarvest() {
        /* iterates through the whole Tile[] farmLot and checks if there are tiles ready for harvesting */
        for (int position = 1; position <= 50; position++) {
            /* if the tile contains a seeded tile */

            if (this.FARM_LOT.get(position).getTileStatus().equals(TileStatus.SEEDED)) {
                /* if the day corresponds to the date for harvest of a crop */
                if (this.dayCount == this.FARM_LOT.get(position).getCrop().getDateForHarvest()) {
                    /* if the water and fertilized conditions are satisfied */
                    if (this.FARM_LOT.get(position).getCrop().getTimesWatered() >= this.FARM_LOT.get(position).getCrop().getWaterNeeded()
                            && this.FARM_LOT.get(position).getCrop().getTimesFertilized() >= this.FARM_LOT.get(position).getCrop().getFertilizerNeeded()) {
                        this.FARM_LOT.get(position).setTileStatus(TileStatus.HARVEST);

                    } else { /* if the water and fertilized conditions are not satisfied */
                        /* the crop within a withered tile remains, and will only reset to null when shoveled */
                        this.FARM_LOT.get(position).setTileStatus(TileStatus.WITHERED);
                    }
                    /* if the day succeeds the crop's date for harvest */
                } else if (this.dayCount > this.FARM_LOT.get(position).getCrop().getDateForHarvest()) {
                    /* the crop within a withered tile remains, and will only reset to null when shoveled */
                    this.FARM_LOT.get(position).setTileStatus(TileStatus.WITHERED);
                }
            }
        }
    }

    public void printTileDetails(int position) {
        /* prints appropriate tile details for each tileStatus */
        switch (this.FARM_LOT.get(position).getTileStatus()) {
            case UNPLOWED, PLOWED, ROCKED -> System.out.printf("\n\t[%s] ~ %2d\n\tTile Status: %s\n\n",
                    this.FARM_LOT.get(position).getTileStatus(), (position ), this.FARM_LOT.get(position).getTileStatus());
            case WITHERED -> System.out.printf("""
                \n\t[%s] ~ %2d
                \tTile Status:   %s
                \tWithered Crop: %s
                \n""", this.FARM_LOT.get(position).getTileStatus(), (position ), this.FARM_LOT.get(position).getTileStatus(),
                    this.FARM_LOT.get(position).getCrop().getSeedName());
            case HARVEST -> System.out.printf("""
                \n\t[%s] ~ %2d
                \tTile Status:       %s
                \tReady For Harvest: %s
                \n""", this.FARM_LOT.get(position).getTileStatus(), (position ), this.FARM_LOT.get(position).getTileStatus(),
                    this.FARM_LOT.get(position).getCrop().getSeedName());
            case SEEDED -> System.out.printf("""
            \n\t[%s] ~ %2d
            \tCrop Type: %-9s
            \tWater Needed:      %-2s\tTimes Watered:   %2d\tWater Bonus Limit:      %2d
            \tFertilizer Needed: %-2s\tTimes Fertilized %2d\tFertilizer Bonus Limit: %2d \t Base Price: %.2f
            \tDate for Harvest Day #%d
            \n""", this.FARM_LOT.get(position).getCrop().getSeedName(), (position),
                    this.FARM_LOT.get(position).getCrop().getCropType(),
                    this.FARM_LOT.get(position).getCrop().getWaterNeeded(), this.FARM_LOT.get(position).getCrop().getTimesWatered(),
                    this.FARM_LOT.get(position).getCrop().getWaterBonus(), this.FARM_LOT.get(position).getCrop().getFertilizerNeeded(),
                    this.FARM_LOT.get(position).getCrop().getTimesFertilized(), this.FARM_LOT.get(position).getCrop().getFertilizerBonus(), this.FARM_LOT.get(position).getCrop().getBasePrice(),
                    this.FARM_LOT.get(position).getCrop().getDateForHarvest());
        }
    }
}

//TODO: Implement Game Over Condition
//TODO: Implement Rocked tiles