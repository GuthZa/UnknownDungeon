package objects;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Object extends ObjectManager {
    public Object(ItemCategory itemCategory, int worldX, int worldY) {

        setItemCategory(itemCategory);
        setWorldX(BigDecimal.valueOf(worldX));
        setWorldY(BigDecimal.valueOf(worldY));
        setCollision(
                itemCategory.equals(ItemCategory.Door) ||
                itemCategory.equals(ItemCategory.Chest)
        );

    }
}
