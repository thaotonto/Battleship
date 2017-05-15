package controllers;

import ai.AIEasy;
import models.PlayerModel;
import views.PlayerView;

/**
 * Created by tonto on 5/12/2017.
 */
public class PlayerController {
    private PlayerModel playerModel;
    private PlayerView playerView;

    public PlayerController(PlayerModel playerModel, PlayerView playerView) {
        this.playerModel = playerModel;
        this.playerView = playerView;
    }

    public PlayerModel getPlayerModel() {
        return playerModel;
    }

    public PlayerView getPlayerView() {
        return playerView;
    }


}
