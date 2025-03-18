package com.corentingambier;

import java.awt.*;
import java.util.HashMap;
import java.util.List;

public class Collection2D<E extends Collection2DElement<E>> extends HashMap<Point, List<E>> {

    public void notifyElementHasMoved(final E collection2DElementTest, final Point oldPosition) {

    }

    public void add(final E element) {

    }

    public void remove(final E element) {

    }

    public boolean contains(final E elementTest) {
        return false;
    }

    public List<E> toList() {
        return null;
    }

    public Dimension getDimension() {
        return null;
    }
}