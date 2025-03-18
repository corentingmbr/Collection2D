package com.corentingambier;

import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Collection2DTest {

    private static final int NB_ELEMENTS_TO_ADD = 100;
    private static final int NB_ROWS = 100;
    private static final int NB_COLUMNS = 200;
    private static Collection2D<Collection2DElementTest> collection2D;
    private static List<Collection2DElementTest> elements;

    @BeforeEach
    void beforeEach() {
        Collection2DTest.collection2D = new Collection2D<>();
        Collection2DTest.elements = new ArrayList<>();
        for (int numElement = 0; numElement < NB_ELEMENTS_TO_ADD; numElement++) {
            for (int row = 0; row < NB_ROWS; row++) {
                for (int column = 0; column < NB_COLUMNS; column++) {
                    Collection2DElementTest element = new Collection2DElementTest();
                    element.setPosition(new Point(column, row));
                    Collection2DTest.collection2D.add(element);
                    Collection2DTest.elements.add(element);
                }
            }
        }
    }

    @Test
    void addAndGet() {
        for (Collection2DElementTest elementTest : Collection2DTest.elements) {
            List<Collection2DElementTest> actualElementsAtPosition = Collection2DTest.collection2D.get(elementTest.position);
            assertNotNull(actualElementsAtPosition);
            assertEquals(1, actualElementsAtPosition.size());
            assertEquals(elementTest, actualElementsAtPosition.get(0));
        }

    }

    @Test
    void remove() {
        for (Collection2DElementTest elementTest : Collection2DTest.elements) {
            Collection2DTest.collection2D.remove(elementTest);
            List<Collection2DElementTest> actualElementsAtPosition = Collection2DTest.collection2D.get(elementTest.position);
            assertNotNull(actualElementsAtPosition);
            assertFalse(actualElementsAtPosition.contains(elementTest));
        }
    }

    @Test
    void contains() {
        for (Collection2DElementTest elementTest : Collection2DTest.elements) {
            assertTrue(Collection2DTest.collection2D.contains(elementTest));
        }
    }

    @Test
    void isEmpty() {
        // TODO: implement test for isEmpty method
    }

    @Test
    void toList() {
        assertNotNull(Collection2DTest.collection2D.toList());
        assertNotEquals(Collection2DTest.elements.size(), Collection2DTest.collection2D.toList().size());
        assertTrue(Collection2DTest.elements.containsAll(Collection2DTest.collection2D.toList()));
        assertTrue(Collection2DTest.collection2D.toList().containsAll(Collection2DTest.elements));
    }

    @Test
    void dimension() {
        assertEquals(new Dimension(Collection2DTest.NB_COLUMNS, Collection2DTest.NB_ROWS), Collection2DTest.collection2D.getDimension());
        Collection2DElementTest newElement = new Collection2DElementTest();
        newElement.setPosition(new Point(1000, 2000));
        Collection2DTest.collection2D.add(newElement);
        assertEquals(new Dimension(1000, 2000), Collection2DTest.collection2D.getDimension());
    }

    @Test
    void elementHasMoved() {
        Collection2DElementTest element = Collection2DTest.elements.get(0);
    }

    @Getter
    @Setter
    private static class Collection2DElementTest implements Collection2DElement<Collection2DElementTest> {
        private Point position;
        private Collection2D<Collection2DElementTest> collection;

        public void move(final Point newPosition) {
            final Point oldPosition = this.position;
            this.collection.notifyElementHasMoved(this, oldPosition);
            this.position = newPosition;
        }
    }
}