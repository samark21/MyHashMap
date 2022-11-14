package src;

import java.util.Objects;

public class Key {

    private int key;

    public Key() {
    }

    public Key(int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if ((o == null) || (o.getClass() != this.getClass())) {
            return false;
        }
        Key key1 = (Key) o;
        return Objects.equals(key, key1.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key);
    }
    

}
