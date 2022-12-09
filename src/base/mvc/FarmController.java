package base.mvc;

import javax.swing.*;
import java.awt.event.*;

public class FarmController implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent ae) {
        String actionCommand = ae.getActionCommand();

        switch (actionCommand) {

            // tool buttons
            case "Plow_Tile" -> plowTile();
            case "Water_Tile" -> waterTile();
            case "Fertilize_Tile" -> fertilizeTile();
            case "Pickaxe_Rock" -> pickaxeRock();
            case "Shovel_Tile" -> shovelWithered();

            // action buttons
            case "Harvest_Crop" -> harvestCrop();
            case "Register_Button" -> {}
            case "End_Day" -> endDay();
            case "Quit_Game" -> quitGame();

            // seed buttons
            case "Plant_Turnip" -> plantTile(0);
            case "Plant_Carrot" -> plantTile(1);
            case "Plant_Potato" -> plantTile(2);
            case "Plant_Rose" -> plantTile(3);
            case "Plant_Tulips" -> plantTile(4);
            case "Plant_Sunflower" -> plantTile(5);
            case "Plant_Mango" -> plantTile(6);
            case "Plant_Apple" -> plantTile(7);
        }
    }

    private final FarmView farmView;
    private final FarmModel farmModel;

    public FarmController(FarmView farmView, FarmModel farmModel) {
        this.farmView = farmView;
        this.farmModel = farmModel;
    }

    public void launch() {
        farmView.startPhase();

        farmView.getStartButton().addActionListener(e -> {
            System.out.println("start button clicked...");
            System.out.println("disposing start phase...");
            System.out.println("launching game phase...");

            farmView.remove(farmView.getStartPanel());
            farmView.getContentPane().invalidate();
            farmView.getContentPane().validate();
            farmView.getContentPane().repaint();

            farmView.gamePhase();
            runFarm();
        });
    }

    private void runFarm() {
        action();
        updateLabels();
        updateFarmTiles();
    }

    private void action() {

        mouseListeners();

        // tool buttons
        farmView.getPlowButton().addActionListener(this);
        farmView.getPlowButton().setActionCommand("Plow_Tile");

        farmView.getWaterButton().addActionListener(this);
        farmView.getWaterButton().setActionCommand("Water_Tile");

        farmView.getFertilizeButton().addActionListener(this);
        farmView.getFertilizeButton().setActionCommand("Fertilize_Tile");

        farmView.getPickaxeButton().addActionListener(this);
        farmView.getPickaxeButton().setActionCommand("Pickaxe_Rock");

        farmView.getShovelButton().addActionListener(this);
        farmView.getShovelButton().setActionCommand("Shovel_Tile");

        // action buttons
        farmView.getHarvestButton().addActionListener(this);
        farmView.getHarvestButton().setActionCommand("Harvest_Crop");

        farmView.getRegisterButton().addActionListener(this);
        farmView.getRegisterButton().setActionCommand("Register_Benefits");

        farmView.getEndDayButton().addActionListener(this);
        farmView.getEndDayButton().setActionCommand("End_Day");

        farmView.getQuitButton().addActionListener(this);
        farmView.getQuitButton().setActionCommand("Quit_Game");

        // seed buttons
        farmView.getTurnipButton().addActionListener(this);
        farmView.getTurnipButton().setActionCommand("Plant_Turnip");

        farmView.getCarrotButton().addActionListener(this);
        farmView.getCarrotButton().setActionCommand("Plant_Carrot");

        farmView.getPotatoButton().addActionListener(this);
        farmView.getPotatoButton().setActionCommand("Plant_Potato");

        farmView.getRoseButton().addActionListener(this);
        farmView.getRoseButton().setActionCommand("Plant_Rose");

        farmView.getTulipsButton().addActionListener(this);
        farmView.getTulipsButton().setActionCommand("Plant_Tulips");

        farmView.getSunflowerButton().addActionListener(this);
        farmView.getSunflowerButton().setActionCommand("Plant_Sunflower");

        farmView.getMangoButton().addActionListener(this);
        farmView.getMangoButton().setActionCommand("Plant_Mango");

        farmView.getAppleButton().addActionListener(this);
        farmView.getAppleButton().setActionCommand("Plant_Apple");
    }

    private void mouseListeners() {

        // farmTilesMouseListener
        for (int position = 1; position <= 50; position++) {
            int finalPosition = position;
            farmView.getFarmTiles().get(finalPosition).addMouseListener(new MouseAdapter() {
                @Override public void mouseEntered(MouseEvent e) { showTileDetails(finalPosition); }
                @Override public void mouseExited(MouseEvent e) { resetDetailLabel(); }
            });
        }

        // toolMouseListener
        for (int index = 0; index < farmView.getToolButtons().size(); index++) {
            int finalIndex = index;
            farmView.getToolButtons().get(index).addMouseListener(new MouseAdapter() {
                @Override public void mouseEntered(MouseEvent e) {
                    super.mouseEntered(e);
                    farmView.getDetailLabel().setText(farmModel.getTOOL_INVENTORY().get(finalIndex).toString());
                }

                @Override public void mouseExited(MouseEvent e) {
                    super.mouseExited(e);
                    resetDetailLabel();
                }
            });
        }

        // actionMouseListener
        for (int index = 0; index < farmView.getActionButtons().size(); index++) {
            int finalIndex = index;
            farmView.getActionButtons().get(index).addMouseListener(new MouseAdapter() {
                @Override public void mouseEntered(MouseEvent e) {
                    super.mouseEntered(e);

                    String details = switch (finalIndex) {
                        case 0 -> "<html>Harvestable crops will appear as their product on the tile!<p>" +
                                "Harvesting costs nothing and you will earn object coins<p>" +
                                "depending on the produce your harvest will make.</html>";
                        case 1 -> "<html>Register to get farmer benefits!<p>" +
                                "When attained a certain level, you shall get <p>" +
                                "bonus earnings per produce, <p>" +
                                "seed cost reduction, <p>" +
                                "water bonus limit increase, <p>" +
                                "fertilizer bonus limit increase, <p>" +
                                "with a registration fee.</html>";
                        case 2 -> "<html>Ends a day and lets the farmer sleep for a bit! </html>";
                        case 3 -> "<html>Let's the farmer quit the game and call it a game!";
                        default -> "";
                    };
                    farmView.getDetailLabel().setText(details);
                }

                @Override public void mouseExited(MouseEvent e) {
                    super.mouseExited(e);
                    resetDetailLabel();
                }
            });
        }

        // seedMouseListener
        for (int index = 0; index < farmView.getSeedButtons().size(); index++) {
            int finalIndex = index;
            farmView.getSeedButtons().get(index).addMouseListener(new MouseAdapter() {
                @Override public void mouseEntered(MouseEvent e) {
                    super.mouseEntered(e);
                    farmView.getDetailLabel().setText(farmModel.getSEED_LIST().get(finalIndex).toString());
                }

                @Override public void mouseExited(MouseEvent e) {
                    super.mouseExited(e);
                    resetDetailLabel();
                }
            });
        }
    }

    private void updateLabels() {
        farmView.getDayLabel().setText(String.valueOf(farmModel.getBOARD().getDayCount()));

        farmView.getLevelLabel().setText(String.valueOf(farmModel.getFARMER().getFarmerLevel()));
        farmView.getExperienceLabel().setText(String.valueOf(farmModel.getFARMER().getFarmerExperience()));
        farmView.getStatusLabel().setText(String.valueOf(farmModel.getFARMER().getFarmerStatus()));
        farmView.getObjectCoinsLabel().setText(String.valueOf(farmModel.getFARMER().getObjectCoins()));
    }

    private void updateFarmTiles() {
        for (int position = 1; position <= 50; position++) {

            ImageIcon tileIcon = switch (farmModel.getBOARD().getPlot(position).getTileStatus()) {
                case ROCKED -> new ImageIcon("resources/game_phase/board_tiles/rocked-80.png");
                case UNPLOWED -> new ImageIcon("resources/game_phase/board_tiles/unplowed-80.png");
                case PLOWED -> new ImageIcon("resources/game_phase/board_tiles/plowed-80.png");
                case SEEDED -> switch (farmModel.getBOARD().getPlot(position).getCrop().getSeedName()) {
                    case "Turnip" -> new ImageIcon("resources/game_phase/crop_tiles/seeded-turnip-80.png");
                    case "Carrot" -> new ImageIcon("resources/game_phase/crop_tiles/seeded-carrot-80.png");
                    case "Potato" -> new ImageIcon("resources/game_phase/crop_tiles/seeded-potato-80.png");
                    case "Rose" -> new ImageIcon("resources/game_phase/crop_tiles/seeded-rose-80.png");
                    case "Tulips" -> new ImageIcon("resources/game_phase/crop_tiles/seeded-tulip-80.png");
                    case "Sunflower" -> new ImageIcon("resources/game_phase/crop_tiles/seeded-sunflower-80.png");
                    case "Mango" -> new ImageIcon("resources/game_phase/crop_tiles/seeded-mango-tree-80.png");
                    case "Apple" -> new ImageIcon("resources/game_phase/crop_tiles/seeded-apple-tree-80.png");
                    default -> null;
                };
                case HARVEST -> switch (farmModel.getBOARD().getPlot(position).getCrop().getSeedName()) {
                    case "Turnip" -> new ImageIcon("resources/game_phase/crop_tiles/harvestable-turnip-80.png");
                    case "Carrot" -> new ImageIcon("resources/game_phase/crop_tiles/harvestable-carrot-80.png");
                    case "Potato" -> new ImageIcon("resources/game_phase/crop_tiles/harvestable-potato-80.png");
                    case "Rose" -> new ImageIcon("resources/game_phase/crop_tiles/harvestable-rose-80.png");
                    case "Tulips" -> new ImageIcon("resources/game_phase/crop_tiles/harvestable-tulip-80.png");
                    case "Sunflower" -> new ImageIcon("resources/game_phase/crop_tiles/harvestable-sunflower-80.png");
                    case "Mango" -> new ImageIcon("resources/game_phase/crop_tiles/harvestable-mango-tree-80.png");
                    case "Apple" -> new ImageIcon("resources/game_phase/crop_tiles/harvestable-apple-tree-80.png");
                    default -> null;
                };
                case WITHERED -> new ImageIcon("resources/game_phase/board_tiles/withered-80.png");
            };

            farmView.getFarmTiles().get(position).setIcon(tileIcon); // sets visual representations
            farmView.getFarmTiles().get(position).setDisabledIcon(tileIcon); // sets visual representations
        }
    }

    private void plantTile(int seedIndex) {
        System.out.println(farmModel.getSEED_LIST().get(seedIndex) + " seed button clicked...");
        try {
            int selectedPosition = Integer.parseInt(
                    JOptionPane.showInputDialog(
                            null,
                            "Select a Tile to Plant this " + farmModel.getSEED_LIST().get(seedIndex).getSeedName() + " on!",
                            "Plant Seed Message",
                            JOptionPane.QUESTION_MESSAGE));

            switch (farmModel.getBOARD().getPlot(selectedPosition).getTileStatus()) {
                case PLOWED -> {
                    if (farmModel.getFARMER().getObjectCoins().
                            compareTo(farmModel.getSEED_LIST().get(seedIndex).getSeedCost()) >= 0) {
                        farmModel.getFARMER().plantSeed(
                                farmModel.getSEED_LIST().get(seedIndex),
                                farmModel.getBOARD().getPlot(selectedPosition),
                                farmModel.getBOARD().getDayCount());
                        updateLabels();
                        updateFarmTiles();
                        JOptionPane.showMessageDialog(
                                null,
                                farmModel.getSEED_LIST().get(seedIndex).getSeedName() + " Successfully Planted!",
                                "Plant Seed Message", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Insufficient Object Coins!",
                                "Plant Seed Message", JOptionPane.ERROR_MESSAGE);
                    }
                }
                case SEEDED ->
                        JOptionPane.showMessageDialog(null,
                                "This tile is already seeded!",
                                "Plant Seed Message", JOptionPane.ERROR_MESSAGE);
                case ROCKED, UNPLOWED, HARVEST, WITHERED -> JOptionPane.showMessageDialog(null,
                        "You can only plant on a plowed tile!",
                        "Plant Seed Message", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) { showErrorMessage(); }
    }

    private void plowTile() {
        System.out.println("plow button clicked...");
        try {
            int selectedPosition = Integer.parseInt(
                    JOptionPane.showInputDialog(
                            null,
                            "Select a Tile to Plow On!",
                            "Plow Message",
                            JOptionPane.QUESTION_MESSAGE));

            switch (farmModel.getBOARD().getPlot(selectedPosition).getTileStatus()) {
                case UNPLOWED -> {
                    farmModel.getFARMER().plowTile(farmModel.getTOOL_INVENTORY().get(0), farmModel.getBOARD().getPlot(selectedPosition));
                    updateLabels();
                    updateFarmTiles();

                    JOptionPane.showMessageDialog(null, "Successfully Plowed!", "Plow Message", JOptionPane.INFORMATION_MESSAGE);
                }
                case PLOWED ->
                        JOptionPane.showMessageDialog(null, "This tile is already plowed!", "Plow Message", JOptionPane.ERROR_MESSAGE);
                case ROCKED ->
                        JOptionPane.showMessageDialog(null, "This is a rocked tile! Remove it first before unplowing...", "Plow Message", JOptionPane.ERROR_MESSAGE);
                case SEEDED, HARVEST ->
                        JOptionPane.showMessageDialog(null, "This tile is occupied!", "Plow Message", JOptionPane.ERROR_MESSAGE);
                case WITHERED ->
                        JOptionPane.showMessageDialog(null, "This tile is withered! Remove it with a shovel...", "Plow Message", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) { showErrorMessage(); }
    }

    private void waterTile() {
        System.out.println("water tile button clicked...");
        try {
            int selectedPosition = Integer.parseInt(
                    JOptionPane.showInputDialog(
                            null,
                            "Select a Tile to Water On!",
                            "Water Tile Message",
                            JOptionPane.QUESTION_MESSAGE));

            switch (farmModel.getBOARD().getPlot(selectedPosition).getTileStatus()) {
                case SEEDED -> {

                    farmModel.getFARMER().waterCrop(farmModel.getTOOL_INVENTORY().get(1), farmModel.getBOARD().getPlot(selectedPosition));

                    updateLabels();
                    updateFarmTiles();

                    JOptionPane.showMessageDialog(null, "Successfully Watered!", "Water Tile Message", JOptionPane.INFORMATION_MESSAGE);
                }
                case ROCKED, UNPLOWED, PLOWED, HARVEST, WITHERED ->
                        JOptionPane.showMessageDialog(null, "You can only water seeded tiles!", "Water Tile Message", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) { showErrorMessage(); }
    }

    private void fertilizeTile() {
        System.out.println("fertilize tile button clicked...");
        try {
            int selectedPosition = Integer.parseInt(
                    JOptionPane.showInputDialog(
                            null,
                            "Select a Tile to Fertilize!",
                            "Fertilize Tile Message",
                            JOptionPane.QUESTION_MESSAGE));

            switch (farmModel.getBOARD().getPlot(selectedPosition).getTileStatus()) {
                case SEEDED -> {

                    farmModel.getFARMER().fertilizeCrop(farmModel.getTOOL_INVENTORY().get(2), farmModel.getBOARD().getPlot(selectedPosition));

                    updateLabels();
                    updateFarmTiles();

                    JOptionPane.showMessageDialog(null, "Successfully Fertilized!", "Fertilize Tile Message", JOptionPane.INFORMATION_MESSAGE);
                }
                case ROCKED, UNPLOWED, PLOWED, HARVEST, WITHERED ->
                        JOptionPane.showMessageDialog(null, "You can only water seeded tiles!", "Water Tile Message", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) { showErrorMessage(); }
    }

    private void harvestCrop() {
        System.out.println("harvest button clicked...");
        try {
            if (farmModel.getBOARD().containsHarvest()) {
                int selectedPosition = Integer.parseInt(
                        JOptionPane.showInputDialog(
                                null,
                                "Select a Tile to Harvest!",
                                "Harvest Crop Message",
                                JOptionPane.QUESTION_MESSAGE));

                switch (farmModel.getBOARD().getPlot(selectedPosition).getTileStatus()) {
                    case HARVEST -> {
                        farmModel.getFARMER().harvestCrop(farmModel.getBOARD().getPlot(selectedPosition));
                        updateLabels();
                        updateFarmTiles();

                        JOptionPane.showMessageDialog(
                                null, "Successfully Harvested!",
                                "Harvest Crop Message",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                    case ROCKED, UNPLOWED, PLOWED, SEEDED, WITHERED ->
                            JOptionPane.showMessageDialog(null, "Invalid Action!", "Harvest Crop Message", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "There are no seeds ready for harvest!", "Harvest Crop Message", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) { showErrorMessage(); }
    }

    private void pickaxeRock() {
        System.out.println("pickaxe button clicked...");
        try {
            if (farmModel.getBOARD().containsRocks()) {

                int selectedPosition = Integer.parseInt(
                        JOptionPane.showInputDialog(
                                null,
                                "Select a Tile to Remove a Rock On!",
                                "Pickaxe Message",
                                JOptionPane.QUESTION_MESSAGE));

                switch (farmModel.getBOARD().getPlot(selectedPosition).getTileStatus()) {
                    case ROCKED -> {
                        if (farmModel.getFARMER().getObjectCoins().
                                compareTo(farmModel.getTOOL_INVENTORY().get(3).getToolCost()) >= 0) {
                            farmModel.getFARMER().removeRock(farmModel.getTOOL_INVENTORY().get(3), farmModel.getBOARD().getPlot(selectedPosition));
                            updateLabels();
                            updateFarmTiles();
                            JOptionPane.showMessageDialog(
                                    null, "Rocks Successfully Removed!",
                                    "Pickaxe Message",
                                    JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null,
                                    "Insufficient Object Coins!",
                                    "Pickaxe Message", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    case UNPLOWED, PLOWED, SEEDED, HARVEST, WITHERED ->
                            JOptionPane.showMessageDialog(null, "You can only pickaxe rocked tiles!", "Pickaxe Message", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "There are no seeds ready for harvest!", "Harvest Crop Message", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) { showErrorMessage(); }
    }

    private void shovelWithered() {
        System.out.println("shovel button clicked...");
        try {
            int selectedPosition = Integer.parseInt(
                    JOptionPane.showInputDialog(
                            null,
                            "Select a Tile to Shovel!",
                            "Shovel Message",
                            JOptionPane.QUESTION_MESSAGE));

            switch (farmModel.getBOARD().getPlot(selectedPosition).getTileStatus()) {
                case WITHERED, UNPLOWED, ROCKED, SEEDED -> {
                    if (farmModel.getFARMER().getObjectCoins().compareTo(farmModel.getTOOL_INVENTORY().get(4).getToolCost()) >= 0) {
                        farmModel.getFARMER().shovelWithered(farmModel.getTOOL_INVENTORY().get(4), farmModel.getBOARD().getPlot(selectedPosition));
                        updateLabels();
                        updateFarmTiles();

                        JOptionPane.showMessageDialog(
                                null, "Shovel Successfully Used...!",
                                "Shovel Message",
                                JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Insufficient Object Coins!",
                                "Shovel Message", JOptionPane.ERROR_MESSAGE);
                    }
                }
                default ->
                        JOptionPane.showMessageDialog(null, "Invalid Action!", "Shovel Message", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) { showErrorMessage(); }
    }

    private void endDay() {
        System.out.println("end day button pressed");
        if (JOptionPane.showConfirmDialog(null,
                "Do you want to end the day?", "End Day",
                JOptionPane.YES_NO_OPTION) == 0) {
            System.out.println("ending day...");
            farmModel.getBOARD().incrementDay();
            farmView.getDayLabel().setText(String.valueOf(farmModel.getBOARD().getDayCount()));

            farmModel.getBOARD().checkForHarvest();
            updateFarmTiles();
        } else { System.out.println("continuing day..."); }
    }

    private void quitGame() {
        System.out.println("quit game button pressed");
        if (JOptionPane.showConfirmDialog(null,
                "Do you want to quit the game and log your farm?", "Quit Game",
                JOptionPane.YES_NO_OPTION) == 0) {
            System.out.println("quitting game...");
            farmView.dispose();
        } else { System.out.println("continuing game..."); }
    }

    private void showTileDetails(int position) {
        String details = switch (farmModel.getBOARD().getPlot(position).getTileStatus()) {
            case ROCKED -> "<html>This tile is rocked!<p><p>" +
                    "You first have to remove it with a pickaxe tool before unplowing it... </html>";
            case UNPLOWED -> "<html>This tile is unplowed!<p><p>" +
                    "You first have to plow it with a plow tool to begin planting seeds... </html>";
            case PLOWED -> "<html>This tile is plowed!<p><p>" +
                    "Navigate to plant seeds phase to begin planting seeds unto it... </html>";

            case SEEDED -> "<html>Seeded " + farmModel.getBOARD().getPlot(position).getCrop().getSeedName() + " Details: <p>" +
                    "Crop Type: " + farmModel.getBOARD().getPlot(position).getCrop().getCropType() + "<p><p>" +
                    "Water Needed: " + farmModel.getBOARD().getPlot(position).getCrop().getWaterNeeded() + "<p>" +
                    "Times Watered: " + farmModel.getBOARD().getPlot(position).getCrop().getTimesWatered() + "<p>" +
                    "Water Bonus Limit: " + farmModel.getBOARD().getPlot(position).getCrop().getWaterBonus() + "<p><p>" +
                    "Fertilizer Needed: " + farmModel.getBOARD().getPlot(position).getCrop().getFertilizerNeeded() + "<p>" +
                    "Times Fertilized: " + farmModel.getBOARD().getPlot(position).getCrop().getTimesFertilized() + "<p>" +
                    "Fertilizer Bonus Limit: " + farmModel.getBOARD().getPlot(position).getCrop().getFertilizerBonus() + "<p><p>" +
                    "Base Price: " + farmModel.getBOARD().getPlot(position).getCrop().getBasePrice() + "<p>" +
                    "Date for Harvest: Day #" + farmModel.getBOARD().getPlot(position).getCrop().getDateForHarvest() + "</html>";

            case HARVEST -> "<html>This tile is now ready for harvest!<p><p>" +
                    "Harvestable Crop: " + farmModel.getBOARD().getPlot(position).getCrop().getSeedName() + "<p><p>" +
                    "Harvest this crop today as it will wither tomorrow!</html>";

            case WITHERED -> "<html>This tile is withered!<p><p>" +
                    "The crop planted upon this tile is no longer harvestable... <p> " +
                    "Please remove the withered crop using a shovel tool... </html>";
        };
        farmView.getDetailLabel().setText(details);
    }

    private void showErrorMessage() { JOptionPane.showMessageDialog(null, "Invalid Input!", "Input Error Message", JOptionPane.ERROR_MESSAGE); }

    private void resetDetailLabel() { farmView.getDetailLabel().setText(""); }
}