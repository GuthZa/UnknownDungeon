package objects;

import lombok.Getter;

@Getter
public enum ItemCategory {
        Key ("key"), Door ("door"), Chest ("chest"), Boots ("boots");

        public final String name;

        ItemCategory(String s) {
                name = s;
        }

        public boolean equalsName(String otherName) {
                return name.equals(otherName);
        }

        public String toString() {
                return this.name;
        }
}
