package ui;

import controllers.PlayerController;
import gamemain.GameFrame;
import views.PlayerView;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by tonto on 5/10/2017.
 */
public class MainContainer {
    private static MainContainer instance;
    private List<Component> components;
    public static final String TAG_MENU = "tag_menu";
    public static final String TAG_START = "tag_start";
    public static final String TAG_INSTRUCTION = "tag_instruction";
    public static final String TAG_GAME = "tag_game";
    public static final String TAG_GAME_OVER = "tag_game_over";

    private MenuPanel menuPanel;
    private GamePanel gamePanel;
    private PlayerView playerView;
    private JFrame instructionFrame;

    public void setInstructionOn(boolean instructionOn) {
        isInstructionOn = instructionOn;
    }

    private boolean isInstructionOn;
    public MainContainer() {
        components = new ArrayList<>();
        instance = this;
        showPanel(TAG_MENU, true);
    }

    public static MainContainer getInstance() {
        if (instance == null) {
            instance = new MainContainer();
        }
        return instance;
    }


    public void showPanel(String tag, boolean refresh) {
        if (tag.equals(TAG_START)) {
            if (playerView != null) {
                components.remove(playerView);
            }
            playerView = new PlayerView();
            components.add(playerView);
            Component component = components.get(components.size() - 1);
            component.setName(TAG_START);
            GameFrame.getInstance().setPanel(playerView);
        } else if (tag.equals(TAG_MENU)) {
            if (menuPanel != null) {
                components.clear();
            }
            menuPanel = new MenuPanel();
            components.add(menuPanel);
            Component component = components.get(components.size() - 1);
            component.setName(TAG_MENU);
            GameFrame.getInstance().setPanel(menuPanel);
        } else if (tag.equals(TAG_INSTRUCTION)) {
            showInstruction();
        } else if (tag.equals(TAG_GAME)) {
            PlayerController playerController = new PlayerController(playerView.getPlayerModel(), playerView);
            gamePanel = new GamePanel(playerController);
            GameFrame.getInstance().setPanel(gamePanel);
        }
    }

    public void onBackPressed() {
        components.remove(components.size() - 1);
        Component component = components.get(components.size() - 1);
        GameFrame.getInstance().setPanel((JPanel) component);
    }

    public void showInstruction() {
        if(!isInstructionOn)
            new InstructionFrame();
    }

}
