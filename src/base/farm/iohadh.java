//package base.farm;
//import java.util.HashMap;
//
//public class Board {
//
//    /* a 10x5 farm Lot represented in a size 50 tile array */
//    private final HashMap<Integer, Tile> FARM_LOT = new HashMap<>();
//    private int dayCount = 1;
//
//    public Board() {
//        /* initializing the base values of a Tile, where it will have no crop and is unplowed */
//        for (int position = 1; position <= 50; position++) {
//            Tile plot = new Tile();
//            plot.setTileStatus(TileStatus.UNPLOWED);
//            this.FARM_LOT.put(position, plot);
//        }
//    }
//
//    public Tile getPlot(int position) { return this.FARM_LOT.get(position); }
//
//    public int getDayCount() { return dayCount; }
//
//    public void incrementDay() { this.dayCount++; }
//
//    public void printFarmLot() {
//        int position = 1, row, col;
//
//        System.out.printf("\t[DAY #%d]\n\n", this.dayCount);
//
//        while (position <= 50) {
//            for (row = 0; row < 10; row++) {
//                for (col = 0; col < 5; col++) {
//                    /* assigned tileOutput a string value based on its a tile's tileStatus */
//                    String tileOutput = switch (this.FARM_LOT.get(position).getTileStatus()) {
//                        case ROCKED -> "ROCKED";
//                        case UNPLOWED -> "UNPLOWED";
//                        case PLOWED -> "PLOWED";
//                        case SEEDED -> this.FARM_LOT.get(position).getCrop().getSeedName();
//                        case HARVEST -> "HARVEST";
//                        case WITHERED -> "WITHERED";
//                    };
//                    /* prints the tile in table-like fashion */
//                    System.out.printf("\t%-11s %-2d", ("[" + tileOutput + "]"), (position + 1));
//                    position++; // increments position
//                }
//                System.out.print("\n");
//            }
//        }
//    }
//
//    public boolean containsHarvest() {
//        /* checks if there are harvestable tiles in the farmLot */
//        boolean found = false;
//        for (int position = 1; position <= 50; position++) {
//            if (this.FARM_LOT.get(position).getTileStatus().equals(TileStatus.HARVEST)) {
//                found = true;
//                break;
//            }
//        }
//        return found;
//    }
//
////    public void checkForHarvest() {
////        /* iterates through the whole Tile[] farmLot and checks if there are tiles ready for harvesting */
////        for (int position = 0; position < 50; position++) {
////            /* if the tile contains a seeded tile */
////            if (this.FARMLOT[position].getTileStatus().equals(TileStatus.SEEDED)) {
////                /* if the day corresponds to the date for harvest of a crop */
////                if (this.dayCount == FARMLOT[position].getCrop().getDateForHarvest()) {
////                    /* if the water and fertilized conditions are satisfied */
////                    if (this.FARMLOT[position].getCrop().getTimesWatered() >= this.FARMLOT[position].getCrop().getWaterNeeded()
////                            && this.FARMLOT[position].getCrop().getTimesFertilized() >= this.FARMLOT[position].getCrop().getFertilizerNeeded()) {
////                        this.FARMLOT[position].setTileStatus(TileStatus.HARVEST);
////
////                    } else { /* if the water and fertilized conditions are not satisfied */
////                        /* the crop within a withered tile remains, and will only reset to null when shoveled */
////                        this.FARMLOT[position].setTileStatus(TileStatus.WITHERED);
////                    }
////                    /* if the day succeeds the crop's date for harvest */
////                } else if (this.dayCount > this.FARMLOT[position].getCrop().getDateForHarvest()) {
////                    /* the crop within a withered tile remains, and will only reset to null when shoveled */
////                    this.FARMLOT[position].setTileStatus(TileStatus.WITHERED);
////                }
////            }
////        }
////    }
//
////    public void printTileDetails(int position) {
////        /* prints appropriate tile details for each tileStatus */
////        switch (this.FARMLOT[position].getTileStatus()) {
////            case UNPLOWED, PLOWED, ROCKED -> System.out.printf("\n\t[%s] ~ %2d\n\tTile Status: %s\n\n",
////                    this.FARMLOT[position].getTileStatus(), (position + 1), this.FARMLOT[position].getTileStatus());
////            case WITHERED -> System.out.printf("""
////                \n\t[%s] ~ %2d
////                \tTile Status:   %s
////                \tWithered Crop: %s
////                \n""", this.FARMLOT[position].getTileStatus(), (position + 1), this.FARMLOT[position].getTileStatus(),
////                    this.FARMLOT[position].getCrop().getSeedName());
////            case HARVEST -> System.out.printf("""
////                \n\t[%s] ~ %2d
////                \tTile Status:       %s
////                \tReady For Harvest: %s
////                \n""", this.FARMLOT[position].getTileStatus(), (position + 1), this.FARMLOT[position].getTileStatus(),
////                    this.FARMLOT[position].getCrop().getSeedName());
////            case SEEDED -> System.out.printf("""
////            \n\t[%s] ~ %2d
////            \tCrop Type: %-9s
////            \tWater Needed:      %-2s\tTimes Watered:   %2d\tWater Bonus Limit:      %2d
////            \tFertilizer Needed: %-2s\tTimes Fertilized %2d\tFertilizer Bonus Limit: %2d \t Base Price: %.2f
////            \tDate for Harvest Day #%d
////            \n""", this.FARMLOT[position].getCrop().getSeedName(), (position + 1),
////                    this.FARMLOT[position].getCrop().getCropType(),
////                    this.FARMLOT[position].getCrop().getWaterNeeded(), this.FARMLOT[position].getCrop().getTimesWatered(),
////                    this.FARMLOT[position].getCrop().getWaterBonus(), this.FARMLOT[position].getCrop().getFertilizerNeeded(),
////                    this.FARMLOT[position].getCrop().getTimesFertilized(), this.FARMLOT[position].getCrop().getFertilizerBonus(), this.FARMLOT[position].getCrop().getBasePrice(),
////                    this.FARMLOT[position].getCrop().getDateForHarvest());
////        }
////    }
//}