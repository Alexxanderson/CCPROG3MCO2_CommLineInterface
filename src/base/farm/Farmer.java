package base.farm;
import java.math.BigDecimal;

public class Farmer {
    private FarmerStatus farmerStatus = FarmerStatus.DEFAULT;
    private int farmerLevel = 0;
    private double farmerExperience = 0;
    private BigDecimal objectCoins = BigDecimal.valueOf(100);

    public Farmer() {}

    // Getters for Farmer class
    public FarmerStatus getFarmerStatus() { return farmerStatus; }
    public int getFarmerLevel() { return farmerLevel; }
    public double getFarmerExperience() { return farmerExperience; }
    public BigDecimal getObjectCoins() { return objectCoins; }

    public void plowTile(Tool plow, Tile plot) { /* plows a tile */
        switch (plot.getTileStatus()){
            case UNPLOWED -> {
                plot.setTileStatus(TileStatus.PLOWED);
                this.objectCoins = this.objectCoins.subtract(plow.getToolCost());
                this.farmerExperience += plow.getExperienceGain();

                System.out.println("\n\tPlot plowed successfully!");
                System.out.println("\n\t" + plow.getExperienceGain() + " experience gained!");
            }
            case PLOWED -> System.out.println("\n\tThis tile is already plowed!");
            default -> System.out.println("\n\tYou can only plow unplowed tiles!");
        }

//        /* Validation wherein only plowTile only works when there is a unplowed plot */
//        switch (plot.getTileStatus()) {
//            case UNPLOWED -> {
//                /* set unplowed tile status of a specific tile into plowed */
//                plot.setTileStatus(TileStatus.PLOWED);
//                System.out.println("\n\tPlot plowed successfully!");
//                System.out.println("\n\t" + plow.getExperienceGain() + " experience gained!");
//
//                /* Reduce farmer's object coins based on the plow's tool cost */
//                this.objectCoins = this.objectCoins.subtract(plow.getToolCost());
//                /* Increment the farmer's experience gain based on the plow's experience yield */
//                this.farmerExperience = this.farmerExperience + plow.getExperienceGain();
//            } // else, do error checking
//            case PLOWED -> System.out.println("\n\tThis tile is already plowed!");
//            default -> System.out.println("\n\tYou can only plow unplowed tiles!");
//        }
    }

//    // mco1 implementation
//    public void plowTile(Tool plow, Tile plot) { /* plows a tile */
//        /* Validation wherein only plowTile only works when there is a unplowed plot */
//        switch (plot.getTileStatus()) {
//            case UNPLOWED -> {
//                /* set unplowed tile status of a specific tile into plowed */
//                plot.setTileStatus(TileStatus.PLOWED);
//                System.out.println("\n\tPlot plowed successfully!");
//                System.out.println("\n\t" + plow.getExperienceGain() + " experience gained!");
//
//                /* Reduce farmer's object coins based on the plow's tool cost */
//                this.objectCoins = this.objectCoins.subtract(plow.getToolCost());
//                /* Increment the farmer's experience gain based on the plow's experience yield */
//                this.farmerExperience = this.farmerExperience + plow.getExperienceGain();
//            } // else, do error checking
//            case PLOWED -> System.out.println("\n\tThis tile is already plowed!");
//            default -> System.out.println("\n\tYou can only plow unplowed tiles!");
//        }
//    }

    public void waterCrop(Tool water, Tile plot) { // waters a crop in a tile
        if (plot.getTileStatus().equals(TileStatus.SEEDED)) { // if seeded, proceed
            /* If times watered + its bonus limit is reached */
            if (plot.getCrop().getTimesWatered() >= plot.getCrop().getWaterBonus())
                /* No experience gain will be awarded to the farmer to avoid any experience gain exploits */
                System.out.println("\n\tYou have reached the water limit!");
            else {
                /* Water the crop */
                System.out.println("\n\t" + plot.getCrop().getSeedName()
                        + " successfully watered!");
                System.out.println("\n\t" + water.getExperienceGain() + " experience gained!");

                /* Increment the crop's water tally */
                plot.getCrop().incrementTimesWatered();

                /* Reduce farmer's object coins based on the plow's tool cost */
                this.objectCoins = this.objectCoins.subtract(water.getToolCost());
                /* Increment the farmer's experience gain based on the plow's experience yield */
                this.farmerExperience = this.farmerExperience + water.getExperienceGain();
            }
        } else // else do error checking
            System.out.print("\n\tYou can only water seeded tiles!");
    }

    public void plantSeed(Crop newCrop, Tile plot, int day) {
        /* Validation wherein a farmer can only buy & plant seeds when they have enough money */
        if (this.objectCoins.compareTo(newCrop.getSeedCost()) >= 0) {
            /* plants a seed given the crop, plot, and day */
            plot.setTileStatus(TileStatus.SEEDED); // sets the plot's tile status into seeded
            plot.setCrop(Crop.newInstance(newCrop, farmerStatus)); // assigns a newly initialized instance of the crop passed to the tile
            plot.getCrop().setDateForHarvest(day); // sets the date for harvest of the newly planted tile
            System.out.println("\n\t" + plot.getCrop().getSeedCost() + " Object Coins has been deducted from your wallet!");
            this.objectCoins = objectCoins.subtract(newCrop.getSeedCost()); // deduct seed cost from object coin wallet
        } else {
            System.out.println("\n\tOuch! You have insufficient object coins!");
        }
    }

    public void harvestCrop(Tile plot) { // harvest a ready for harvest crop in the tile

        BigDecimal HarvestTotal, WaterBonus, FertilizerBonus;

        // assigns a value to actual produce based on the crop's ability to produce such

        if (plot.getCrop().getMinProduce() == plot.getCrop().getMaxProduce())
            plot.getCrop().setActualProduce(plot.getCrop().getMaxProduce());
        else
            plot.getCrop().setActualProduce( (int) Math.floor(Math.random()*(plot.getCrop().getMaxProduce() - plot.getCrop().getMinProduce() + 1) + plot.getCrop().getMinProduce()) );

        /*
         * HarvestTotal = actualProduce * (basePrice + earningBonus)
         * WaterBonus = HarvestTotal * 0.2 * (timesWatered - 1)
         * FertilizerBonus = HarvestTotal * 0.5 * timesFertilized
         * FinalHarvestBonus = HarvestTotal + WaterBonus + FertilizerBonus
         */

        HarvestTotal = BigDecimal.valueOf(plot.getCrop().getActualProduce()).multiply(plot.getCrop().getBasePrice().add(this.farmerStatus.getEarningBonus()));
        WaterBonus = HarvestTotal.multiply(BigDecimal.valueOf(0.2)).multiply(BigDecimal.valueOf(plot.getCrop().getTimesWatered() - 1));
        FertilizerBonus = HarvestTotal.multiply(BigDecimal.valueOf(0.5)).multiply(BigDecimal.valueOf(plot.getCrop().getTimesFertilized()));

        plot.getCrop().setHarvestPrice(HarvestTotal.add(WaterBonus).add(FertilizerBonus));

        System.out.println("\n\tSuccessfully Harvested!");
        System.out.println("\n\tTurnips Produced: " + plot.getCrop().getActualProduce());
        System.out.println("\n\t" + plot.getCrop().getHarvestPrice()
                + " Object Coins has been incremented to your wallet!");
        System.out.println("\n\t" + plot.getCrop().getExperienceYield() + " experience gained!");

        this.objectCoins = this.objectCoins.add(plot.getCrop().getHarvestPrice()); // increment harvest price to object coin wallet
        this.farmerExperience = this.farmerExperience + plot.getCrop().getExperienceYield(); // increment farmer experience based on crop's expi yield

        plot.setTileStatus(TileStatus.UNPLOWED); // resets to unplowed since crop's produce is now harvested
        plot.setCrop(null); // resets to null since crop's produce is now harvested
    }

    // to be implemented after MCO1 Phase
    public void removeRock(Tool pickaxe, Tile plot) {

        if (plot.getTileStatus().equals(TileStatus.ROCKED)) {
            if (this.objectCoins.compareTo(BigDecimal.valueOf(50)) < 0) {
                System.out.println("\n\tOuch! You have insufficient object coins!");
            } else {
                plot.setTileStatus(TileStatus.UNPLOWED);
                System.out.println("\n\tRock pickaxed successfully!");

                this.objectCoins = this.objectCoins.subtract(pickaxe.getToolCost());
                this.farmerExperience = this.farmerExperience + pickaxe.getExperienceGain();
            }
        } else {
            System.out.println("There are no rocks present in this tile!");
        }
    }

    public void shovelWithered(Tool shovel, Tile plot) {
        switch (plot.getTileStatus()) {
            case WITHERED -> {
                if (this.objectCoins.compareTo(BigDecimal.valueOf(7)) < 0) {
                    System.out.println("\n\tOuch! You have insufficient object coins!");
                } else{
                    plot.setCrop(null);
                    plot.setTileStatus(TileStatus.UNPLOWED);
                    System.out.println("\n\tWithered tile shoveled successfully!");

                    this.objectCoins = this.objectCoins.subtract(shovel.getToolCost());
                    this.farmerExperience = this.farmerExperience + shovel.getExperienceGain();
                }
            }
            case SEEDED -> {
                plot.setCrop(null);
                plot.setTileStatus(TileStatus.UNPLOWED);
                this.objectCoins = this.objectCoins.subtract(shovel.getToolCost());
//                System.out.println("This plant is still alive!");
            }
            case UNPLOWED, ROCKED -> {
                this.objectCoins = this.objectCoins.subtract(shovel.getToolCost());
            }
            default -> System.out.println("This is not a Withered Tile!");
        }
    }

    public void fertilizeCrop(Tool fertilize, Tile plot) {
        switch (plot.getTileStatus()) {
            case SEEDED -> {
                if (plot.getCrop().getTimesFertilized() >= plot.getCrop().getFertilizerBonus()) {
                    System.out.println("\n\tYou have reached the fertilizer limit!");
                } else if (this.objectCoins.compareTo(BigDecimal.valueOf(10)) < 0) {
                    System.out.println("\n\tOuch! You have insufficient object coins!");
                } else {
                    System.out.println("\n\t" + plot.getCrop().getSeedName() + " successfully fertilized!");

                    plot.getCrop().incrementTimesFertilized();

                    this.objectCoins = this.objectCoins.subtract(fertilize.getToolCost()); // placeholder for tools with cost > 0
                    this.farmerExperience = this.farmerExperience + fertilize.getExperienceGain();
                }
            }
            case HARVEST -> System.out.println("You cannot fertilize this tile anymore.");
            default -> System.out.println("\n\tYou can only fertilize seeded tiles!");
        }
    }


    public void checkFarmerLevel(){
        if (farmerExperience % 100.0 == 0 && farmerExperience != 0.0){
            if (farmerLevel != (int) (farmerExperience / 100) ) {
                System.out.println("You Leveled Up!!\n");
            }
            farmerLevel = (int) (farmerExperience / 100);
        }
    }

    public void rankUp(){
        switch (farmerStatus){
            case DEFAULT -> {
                if (this.objectCoins.compareTo(BigDecimal.valueOf(200)) < 0) {
                    System.out.println("\n\tOuch! You have insufficient object coins!");
                } else if(farmerLevel < 5) {
                    System.out.println("\n\tOuch! Your level is not enough!");
                } else  {
                        farmerStatus = FarmerStatus.REGISTERED;
                }
            }
            case REGISTERED -> {
                if (this.objectCoins.compareTo(BigDecimal.valueOf(200)) < 0) {
                    System.out.println("\n\tOuch! You have insufficient object coins!");
                } else if(farmerLevel < 5) {
                    System.out.println("\n\tOuch! Your level is not enough!");
                } else  {
                    farmerStatus = FarmerStatus.DISTINGUISHED;
                }

            }
            case DISTINGUISHED -> {
                if (this.objectCoins.compareTo(BigDecimal.valueOf(200)) < 0) {
                    System.out.println("\n\tOuch! You have insufficient object coins!");
                } else if(farmerLevel < 5) {
                    System.out.println("\n\tOuch! Your level is not enough!");
                } else  {
                    farmerStatus = FarmerStatus.LEGENDARY;
                }

            }
        }
    }


    public BigDecimal getFarmerBonusEarning(){
        return farmerStatus.getEarningBonus();
    }
    public BigDecimal getFarmerSeedReduction(){
        return farmerStatus.getSeedCostReduction();
    }
    public int getFarmerWaterLimitBonus(){
        return farmerStatus.getWaterLimitBonus();
    }

    public void printFarmerDetails() { // prints the farmer details above farmLot
        System.out.printf("""
                \n\tLevel: %3d
                \tExperience: %.2f
                \tFarmer Type: %s
                \tObject Coins: %.2f
                \tEarning Bonus: %.2f
                \n""",
                this.farmerLevel, this.farmerExperience,
                this.farmerStatus.getFarmerType(), this.objectCoins,
                this.farmerStatus.getEarningBonus());
    }
}