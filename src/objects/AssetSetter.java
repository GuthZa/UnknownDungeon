package objects;

import lombok.AllArgsConstructor;
import main.GamePanel;

@AllArgsConstructor
public class AssetSetter {
    GamePanel gamePanel;

    public void setObject() {

        ObjectManager[] objects = new ObjectManager[10];
        //Keys
        objects[0] = new Object(Object.ItemCategory.Key,
                23 * gamePanel.getTileSize().intValue(),
                7 * gamePanel.getTileSize().intValue());
        objects[1] = new Object(Object.ItemCategory.Key,
                23 * gamePanel.getTileSize().intValue(),
                40 * gamePanel.getTileSize().intValue());
        objects[2] = new Object(Object.ItemCategory.Key,
                38 * gamePanel.getTileSize().intValue(),
                8 * gamePanel.getTileSize().intValue());

        //Doors
        objects[4] = new Object(Object.ItemCategory.Door,
                10 * gamePanel.getTileSize().intValue(),
                11 * gamePanel.getTileSize().intValue());
        objects[5] = new Object(Object.ItemCategory.Door,
                8 * gamePanel.getTileSize().intValue(),
                28 * gamePanel.getTileSize().intValue());
        objects[6] = new Object(Object.ItemCategory.Door,
                12 * gamePanel.getTileSize().intValue(),
                2 * gamePanel.getTileSize().intValue());

        //Chest
        objects[7] = new Object(Object.ItemCategory.Chest,
                10 * gamePanel.getTileSize().intValue(),
                7 * gamePanel.getTileSize().intValue());

        //Boots
        objects[8] = new Object(Object.ItemCategory.Boots,
                37 * gamePanel.getTileSize().intValue(),
                42 * gamePanel.getTileSize().intValue());

        for (ObjectManager obj :
                objects) {
            if(obj!=null) obj.setupImage();
        }
        gamePanel.setObjectList(objects);
    }
}
