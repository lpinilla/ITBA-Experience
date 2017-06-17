package person;

import java.util.ArrayList;

public class CircularList<T> extends ArrayList<T> {

    @Override
    public T get(int index) {
        return super.get(index % this.size());
    }
}