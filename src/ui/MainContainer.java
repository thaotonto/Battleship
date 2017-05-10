package ui;

import gamemain.GameFrame;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by tonto on 5/10/2017.
 */
public class MainContainer extends Container {
    private static MainContainer instance;
    private List<Component> components;
    public static final String TAG_MENU = "tag_menu";
    public static final String TAG_START = "tag_start";
    public static final String TAG_INSTRUCTION = "tag_instruction";
    public static final String TAG_GAME = "tag_game";
    public static final String TAG_GAME_OVER = "tag_game_over";

    private MenuPanel menuPanel;
    private StartPanel startPanel;

    public MainContainer() {
        components = new ArrayList<Component>();
        instance = this;
        showPanel(TAG_MENU, true);
        setVisible(true);
    }

    public static MainContainer getInstance() {
        if (instance == null) {
            instance = new MainContainer();
        }
        return instance;
    }


    public void showPanel(String tag, boolean refresh) {
        if (tag.equals(TAG_START)) {
            if (!refresh) {
                for (Component component : components) {
                    System.out.println(component.getName());
                    if (component.getName().equals(TAG_START)) {
                        GameFrame.getInstance().setPanel((JPanel) component);
                    }
                }
            } else {
                if (startPanel != null) {
                    components.remove(startPanel);
                }
                startPanel = new StartPanel();
                components.add(startPanel);
                Component component = components.get(components.size() - 1);
                component.setName(TAG_START);
                GameFrame.getInstance().setPanel(startPanel);
            }
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
        }
    }

    public void onBackPressed() {
        components.remove(components.size() - 1);
        Component component = components.get(components.size() - 1);
        GameFrame.getInstance().setPanel((JPanel) component);
    }

    public void showInstruction() {
        new InstructionFrame();
    }

}
