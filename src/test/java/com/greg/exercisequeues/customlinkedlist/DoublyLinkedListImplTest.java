package com.greg.exercisequeues.customlinkedlist;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DoublyLinkedListImplTest {

    private DoublyLinkedListImpl list;

    @BeforeEach
    void setup() {
        list = new DoublyLinkedListImpl();
    }

    @Test
    void newListHasNoElementsWhenCreated() {
        assertEquals(0, list.size());
    }

    @Test
    void isEmptyIndicatesNoElementsInTheList() {

        assertAll(
                () -> assertEquals(0, list.size()),
                () -> assertTrue(list.isEmpty())
        );
    }

    @Test
    void newElementsCanBeAddedToTheEndOfTheList() {

        list.addElementEnd(1);
        list.addElementEnd(2);
        list.addElementEnd(3);

        assertAll(
                () -> assertEquals(3, list.size()),
                () -> assertFalse(list.isEmpty())
        );
    }

    @Test
    void newElementsCanBeAddedToTheFrontOfTheList() {

        list.addElementFront(1);
        list.addElementFront(2);
        list.addElementFront(3);

        assertAll(
                () -> assertEquals(3, list.size()),
                () -> assertFalse(list.isEmpty())
        );
    }

    @Test
    void elementsAddedToEndCanBeRetrievedFromTheListInACorrectOrder() {

        list.addElementEnd(1);
        list.addElementEnd(2);
        list.addElementEnd(3);
        list.addElementEnd(4);
        list.addElementEnd(5);

        assertAll(

                () -> assertEquals(1, list.getElement()),
                () -> assertEquals(2, list.getElement()),
                () -> assertEquals(3, list.getElement()),
                () -> assertEquals(4, list.getElement()),
                () -> assertEquals(5, list.getElement())
        );
    }

    @Test
    void elementsAddedToFrontCanBeRetrievedFromTheListInACorrectOrder() {

        list.addElementFront(1);
        list.addElementFront(2);
        list.addElementFront(3);
        list.addElementFront(4);
        list.addElementFront(5);

        assertAll(

                () -> assertEquals(5, list.getElement()),
                () -> assertEquals(4, list.getElement()),
                () -> assertEquals(3, list.getElement()),
                () -> assertEquals(2, list.getElement()),
                () -> assertEquals(1, list.getElement())
        );
    }

    @Test
    void elementsRetrievedFromListAreDeleted() {

        DoublyLinkedListImpl<String> list1 = new DoublyLinkedListImpl<>();

        String[] data = {"a", "b", "c", "d", "e", "f"};

        for (String dPoint : data) {
            list.addElementEnd(dPoint);
            list1.addElementFront(dPoint);
        }

        assertAll(
                () -> assertFalse(list.isEmpty()),
                () -> assertFalse(list1.isEmpty()),
                () -> assertEquals(6, list.size()),
                () -> assertEquals(6, list1.size())
        );

        for (int i = 0; i < 2; i++) {
            list.getElement();
            list1.getElement();
        }

        assertAll(
                () -> assertFalse(list.isEmpty()),
                () -> assertEquals(4, list.size()),
                () -> assertFalse(list1.isEmpty()),
                () -> assertEquals(4, list1.size())
        );

        for (int i = 0; i < 4; i++) {
            list.getElement();
            list1.getElement();
        }

        assertAll(
                () -> assertTrue(list.isEmpty()),
                () -> assertEquals(0, list.size()),
                () -> assertTrue(list1.isEmpty()),
                () -> assertEquals(0, list1.size())
        );
    }

    @Test
    void listWorksWithMultipleDataTypes() {

        list.addElementEnd(1);
        list.addElementFront("a");
        list.addElementEnd(3l);
        list.addElementFront(12.5d);
        list.addElementEnd(9.87f);

        assertAll(
                () -> assertEquals(Double.class, list.getElement().getClass()),
                () -> assertEquals(String.class, list.getElement().getClass()),
                () -> assertEquals(Integer.class, list.getElement().getClass()),
                () -> assertEquals(Long.class, list.getElement().getClass()),
                () -> assertEquals(Float.class, list.getElement().getClass())
        );
    }
}
