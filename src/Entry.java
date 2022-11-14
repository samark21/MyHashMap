package src;

import java.util.Objects;

public class Entry {

    Key key;
    Object value;

    public Entry(Key key, Object value) {
        this.key = key;
        this.value = value;
    }

    public Object getValue(){
        return value;
    }

}
