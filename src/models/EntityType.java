package models;

import lombok.Getter;

@Getter
public enum EntityType {
    Player ("player"), Enemy ("enemy"), NPC ("npc");

    public final String name;

    EntityType(String s) {
        name = s;
    }

    public boolean equalsName(String otherName) {
        return name.equals(otherName);
    }

    public String toString() {
        return this.name;
    }
}
