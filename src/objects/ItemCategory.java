package objects;

import lombok.Getter;

@Getter
public enum ItemCategory {
        Key("Key"),
        Chest("Chest"),
        Door("Door"),
        Boots("Boots");

        private final String key;

        ItemCategory(String key) {
                this.key = key;
        }

        public String toString() {
                return key;
        }

        public boolean equalName(String otherName) {
                return key.equals(otherName);
        }
}
