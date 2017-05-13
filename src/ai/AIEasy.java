package ai;

import models.Ship;

import java.util.List;

/**
 * Created by THM on 5/11/2017.
 */
public class AIEasy extends AI{


    public AIEasy(List<Ship> shipList, int[][] battleMap) {
        super(shipList, battleMap);
    }

    @Override
    public void shot() {
        lastShot = randomShot();
    }


}
