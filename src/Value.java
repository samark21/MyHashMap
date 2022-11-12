package src;

import java.util.Objects;

public class Value {

    private int value;

    public Value() {
    }

    public Value(int value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if ((o == null) || (getClass() != o.getClass())) {
            return false;
        }
        Value value1 = (Value) o;
        return value == value1.value;
    }

//    @Override
//    public int hashCode() {
//        return Objects.hash(value);
//    }
}
