package base.mvc;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class FarmView extends JFrame {


    public FarmView() {
        ImageIcon hukayLogo = new ImageIcon("resources/hukay-logo.png");
        this.setIconImage(hukayLogo.getImage());
    }

    /*
     * Start Phase variables
     * startButton ~ button which will signify the start of the game phase when pressed
     * startPanel ~ where the startButton and startPage variables will be stored and added
     */

    // start phase variables
    private final JButton startButton = new JButton();
    private final JPanel startPanel = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            ImageIcon startPage = new ImageIcon("resources/start_phase/hukay-start-page.png");
            g.drawImage(startPage.getImage(), 0, 0, null);
        }
    };

    protected void startPhase() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(510, 288);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        startPanel.setLayout(null);

        startButton.setBounds(187, 155, 125, 50);
        startButton.setFocusable(true);
        startButton.setContentAreaFilled(false);

        startPanel.add(startButton);
        this.add(startPanel);
    }

    /**
     * Game Phase variables
     * mainPanel (livePanel, boardPanel, actionPanel)
     * livePanel
     * boardPanel
     * actionPanel
     */

    // colorway initialization
    private final Color BROWN = new Color(0x996B49);
    private final Color GREEN = new Color(0x718F3F);
    private final Color BEIGE = new Color(0xE4BD98);

    // gamePhase panels
    private final JPanel mainPanel = new JPanel();
    private final JPanel boardPanel = new JPanel();
    private final JPanel livePanel = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(new ImageIcon("resources/game_phase/panel_backgrounds/livePanelBG.png").getImage(), 0, 0, null);
        }
    };
    private final JPanel actionPanel = new JPanel() { // consists of toolsPanel and cropsPanel
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(new ImageIcon("resources/game_phase/panel_backgrounds/actionPanelBG.png").getImage(), 0, 0, null);
        }
    };

    // live panel variables
    private final JLabel levelLabel = new JLabel();
    private final JLabel experienceLabel = new JLabel();
    private final JLabel statusLabel = new JLabel();
    private final JLabel objectCoinsLabel = new JLabel();
    private final JLabel detailLabel = new JLabel();
    private final JLabel dayLabel = new JLabel();

    // board panel variables
    private final HashMap<Integer, JLabel> farmTiles = new HashMap<>();

    // action button variables
    private final ArrayList<JButton> actionButtons = new ArrayList<>();

    private final JButton harvestButton = new JButton(), registerButton = new JButton(),
            endDayButton = new JButton(), quitButton = new JButton();

    // tools button variables
    private final ArrayList<JButton> toolButtons = new ArrayList<>();

    private final JButton plowButton = new JButton(), waterButton = new JButton(),
            fertilizeButton = new JButton(), pickaxeButton = new JButton(),
            shovelButton = new JButton();

    // crops button variables
    private final ArrayList<JButton> seedButtons = new ArrayList<>();

    private final JButton turnipButton = new JButton(), carrotButton = new JButton(),
            potatoButton = new JButton(), roseButton = new JButton(),
            tulipsButton = new JButton(), sunflowerButton = new JButton(),
            mangoButton = new JButton(), appleButton = new JButton();

    protected void gamePhase() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1290, 900);
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        mainPanel.setLayout(null);
        mainPanel.setBackground(BROWN);

        livePanel.setLayout(null);
        livePanel.setBounds(0, 0, 420, 900);

        boardPanel.setBackground(BROWN);
        boardPanel.setLayout(new GridLayout(10, 5, 5, 5));
        boardPanel.setBounds(430, 5, 420, 850);

        actionPanel.setBounds(860, 0, 420, 900);
        actionPanel.setBackground(GREEN);
        actionPanel.setLayout(null);

        setLivePanel();
        setBoardPanel();
        setActionPanel();

        mainPanel.add(livePanel);
        mainPanel.add(boardPanel);
        mainPanel.add(actionPanel);
        this.add(mainPanel);
    }

    private void setLivePanel() {
        Font OpenSymbol = new Font("OpenSymbol", Font.BOLD | Font.ITALIC, 24);

        levelLabel.setFont(OpenSymbol);
        levelLabel.setForeground(BEIGE);
        levelLabel.setBounds(156, 50, 200, 33);

        experienceLabel.setFont(OpenSymbol);
        experienceLabel.setForeground(BEIGE);
        experienceLabel.setBounds(257, 90, 200, 33);

        statusLabel.setFont(OpenSymbol);
        statusLabel.setForeground(BEIGE);
        statusLabel.setBounds(193, 132, 250, 33);

        objectCoinsLabel.setFont(OpenSymbol);
        objectCoinsLabel.setForeground(BEIGE);
        objectCoinsLabel.setBounds(193, 175, 200, 33);

        dayLabel.setBounds(147, 302, 75, 40);
        dayLabel.setForeground(BEIGE);
        dayLabel.setFont(new Font("OpenSymbol", Font.BOLD | Font.ITALIC, 35));

        detailLabel.setBounds(99, 507, 221, 260);
        detailLabel.setFont(new Font("OpenSymbol", Font.BOLD, 14));
        detailLabel.setVerticalAlignment(JLabel.TOP);
        detailLabel.setHorizontalAlignment(JLabel.LEFT);
        detailLabel.setForeground(BEIGE);

        livePanel.add(levelLabel);
        livePanel.add(experienceLabel);
        livePanel.add(statusLabel);
        livePanel.add(objectCoinsLabel);
        livePanel.add(dayLabel);
        livePanel.add(detailLabel);
    }

    private void setBoardPanel() {
        for (int position = 1; position <= 50; position++) {
            JLabel farmTile = new JLabel();
            JLabel tilePositions = new JLabel(String.valueOf(position));

            tilePositions.setText(String.valueOf(position));
            tilePositions.setFont(new Font("OpenSymbol", Font.BOLD | Font.ITALIC, 18));
            tilePositions.setForeground(new Color(0xE4BD98));

            farmTile.add(tilePositions);
            farmTile.setLayout(new FlowLayout(FlowLayout.LEADING));
            farmTiles.put(position, farmTile);
            boardPanel.add(farmTiles.get(position));
        }
    }

    private void setActionPanel() {

        // tool buttons
        plowButton.setBounds(245, 259, 50, 54);
        plowButton.setContentAreaFilled(false);
        waterButton.setBounds(214, 201, 50, 54);
        waterButton.setContentAreaFilled(false);
        fertilizeButton.setBounds(275, 201, 50, 54);
        fertilizeButton.setContentAreaFilled(false);
        pickaxeButton.setBounds(214, 317, 50, 54);
        pickaxeButton.setContentAreaFilled(false);
        shovelButton.setBounds(275, 317, 50, 54);
        shovelButton.setContentAreaFilled(false);

        actionPanel.add(plowButton);
        actionPanel.add(waterButton);
        actionPanel.add(fertilizeButton);
        actionPanel.add(pickaxeButton);
        actionPanel.add(shovelButton);

        toolButtons.add(plowButton);
        toolButtons.add(waterButton);
        toolButtons.add(fertilizeButton);
        toolButtons.add(pickaxeButton);
        toolButtons.add(shovelButton);

        // action buttons
        harvestButton.setBounds(122, 482, 170, 97);
        harvestButton.setContentAreaFilled(false);
        registerButton.setBounds(122, 589, 170, 97);
        registerButton.setContentAreaFilled(false);
        endDayButton.setBounds(72, 696, 125, 97);
        endDayButton.setContentAreaFilled(false);
        quitButton.setBounds(215, 696, 125, 97);
        quitButton.setContentAreaFilled(false);

        actionPanel.add(harvestButton);
        actionPanel.add(registerButton);
        actionPanel.add(endDayButton);
        actionPanel.add(quitButton);

        actionButtons.add(harvestButton);
        actionButtons.add(registerButton);
        actionButtons.add(endDayButton);
        actionButtons.add(quitButton);

        // seed buttons
        turnipButton.setBounds(89, 201, 40, 54);
        turnipButton.setContentAreaFilled(false);
        carrotButton.setBounds(89, 259, 40, 54);
        carrotButton.setContentAreaFilled(false);
        potatoButton.setBounds(89, 317, 40, 54);
        potatoButton.setContentAreaFilled(false);
        roseButton.setBounds(140, 201, 40, 54);
        roseButton.setContentAreaFilled(false);
        tulipsButton.setBounds(140, 259, 40, 54);
        tulipsButton.setContentAreaFilled(false);
        sunflowerButton.setBounds(140, 317, 40, 54);
        sunflowerButton.setContentAreaFilled(false);
        mangoButton.setBounds(89, 375, 40, 54);
        mangoButton.setContentAreaFilled(false);
        appleButton.setBounds(140, 375, 40, 54);
        appleButton.setContentAreaFilled(false);

        actionPanel.add(turnipButton);
        actionPanel.add(carrotButton);
        actionPanel.add(potatoButton);
        actionPanel.add(roseButton);
        actionPanel.add(tulipsButton);
        actionPanel.add(sunflowerButton);
        actionPanel.add(mangoButton);
        actionPanel.add(appleButton);

        seedButtons.add(turnipButton);
        seedButtons.add(carrotButton);
        seedButtons.add(potatoButton);
        seedButtons.add(roseButton);
        seedButtons.add(tulipsButton);
        seedButtons.add(sunflowerButton);
        seedButtons.add(mangoButton);
        seedButtons.add(appleButton);
    }

    // start phase variables getters
    protected JPanel getStartPanel() { return startPanel; }
    protected JButton getStartButton() { return startButton; }

    // live panel button getters
    protected JLabel getLevelLabel() { return levelLabel; }
    protected JLabel getExperienceLabel() { return experienceLabel; }
    protected JLabel getStatusLabel() { return statusLabel; }
    protected JLabel getObjectCoinsLabel() { return objectCoinsLabel; }
    protected JLabel getDayLabel() { return dayLabel; }
    protected JLabel getDetailLabel() { return detailLabel; }

    // farm tiles getter
    protected HashMap<Integer, JLabel> getFarmTiles() { return farmTiles; }

    // action button getters
    protected ArrayList<JButton> getActionButtons() { return actionButtons; }
    protected JButton getHarvestButton() { return harvestButton; }
    protected JButton getRegisterButton() { return registerButton; }
    protected JButton getEndDayButton() { return endDayButton; }
    protected JButton getQuitButton() { return quitButton; }

    // tool button getters
    protected ArrayList<JButton> getToolButtons() { return toolButtons; }
    protected JButton getPlowButton() { return plowButton; }
    protected JButton getWaterButton() { return waterButton; }
    protected JButton getFertilizeButton() { return fertilizeButton; }
    protected JButton getPickaxeButton() { return pickaxeButton; }
    protected JButton getShovelButton() { return shovelButton; }

    // seed button getters
    protected ArrayList<JButton> getSeedButtons() { return seedButtons; }
    protected JButton getTurnipButton() { return turnipButton; }
    protected JButton getCarrotButton() { return carrotButton; }
    protected JButton getPotatoButton() { return potatoButton; }
    protected JButton getRoseButton() { return roseButton; }
    protected JButton getTulipsButton() { return tulipsButton; }
    protected JButton getSunflowerButton() { return sunflowerButton; }
    protected JButton getMangoButton() { return mangoButton; }
    protected JButton getAppleButton() { return appleButton; }
}