package model;

import java.util.ArrayList;

/**
 * Created by agustin on 20/06/17.
 */
import java.util.ArrayList;

public class CircularList<T> extends ArrayList<T> {
     @Override
        public T get(int index) {
            return super.get(index % this.size());
        }
}

