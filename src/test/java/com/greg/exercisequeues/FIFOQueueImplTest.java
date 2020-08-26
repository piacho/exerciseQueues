package com.greg.exercisequeues;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class FIFOQueueImplTest {

    private FIFOQueueImpl fifoQueue = new FIFOQueueImpl();
    private static final String path = "src/test/resources/testData.txt";
    private long testLongNumber = 3000000000L;
    private float testFloatNumber = 2.71f;
    private double testDoubleNumber = 0.123456789123456d;

    @BeforeEach
    void setup() {

        fifoQueue = new FIFOQueueImpl();

    }

    @Test
    void aFIFOQueueIsCreatedWithNoElements() {

        assertEquals(0, fifoQueue.size());

    }

    @Test
    void aFIFOQAcceptsIntegers() {

        Stream.of(1, 2, 3, 4, 5).forEach(fifoQueue::add);

        assertAll(
                () -> assertEquals(1, fifoQueue.get()),
                () -> assertEquals(2, fifoQueue.get()),
                () -> assertEquals(3, fifoQueue.get()),
                () -> assertEquals(4, fifoQueue.get()),
                () -> assertEquals(5, fifoQueue.get())
        );
    }

    @Test
    void aFIFOQAcceptsLongs() {

        Stream.of(testLongNumber, testLongNumber - 200000L, testLongNumber + 200000L).forEach(fifoQueue::add);

        assertAll(
                () -> assertEquals(testLongNumber, fifoQueue.get()),
                () -> assertEquals(testLongNumber - 200000L, fifoQueue.get()),
                () -> assertEquals(testLongNumber + 200000L, fifoQueue.get())
        );
    }

    @Test
    void aFIFOQAcceptsFractionalNumbers() throws QueueException {

        fifoQueue.add(testFloatNumber);

        assertEquals(testFloatNumber, fifoQueue.get());

        fifoQueue.add(testDoubleNumber);

        assertEquals(testDoubleNumber, fifoQueue.get());
    }

    @Test
    void aFIFOCanHaveMultipleNumericTypes() {

        fifoQueue.add(10);
        fifoQueue.add(testDoubleNumber);
        fifoQueue.add(testFloatNumber);
        fifoQueue.add(testLongNumber);

        assertAll(
                () -> assertEquals(10, fifoQueue.get()),
                () -> assertEquals(testDoubleNumber, fifoQueue.get()),
                () -> assertEquals(testFloatNumber, fifoQueue.get()),
                () -> assertEquals(testLongNumber, fifoQueue.get())
        );
    }

    @Test
    void addingNumbersToFIFOIncreasesItsSize() {

        assertEquals(0, fifoQueue.size());

        fifoQueue.add(1);

        assertEquals(1, fifoQueue.size());

        fifoQueue.add(2);

        assertEquals(2, fifoQueue.size());

        fifoQueue.add(3);

        assertEquals(3, fifoQueue.size());
    }

    @Test
    public void aFIFOTreatsDuplicatesAsIndividualElements() {

        fifoQueue.add(1);
        fifoQueue.add(1);
        fifoQueue.add(2);
        fifoQueue.add(5);
        fifoQueue.add(5);
        fifoQueue.add(10);

        assertEquals(6, fifoQueue.size());
    }

    @Test
    public void numbersAreCorrectlyReturnedFromFIFO() {

        fifoQueue.add(1);
        fifoQueue.add(2);
        fifoQueue.add(3);

        assertAll(
                () -> assertEquals(1, fifoQueue.get()),
                () -> assertEquals(2, fifoQueue.get()),
                () -> assertEquals(3, fifoQueue.get())
        );
    }

    @Test
    void numbersReturnedFromFIFOAreRemovedFromIt() throws QueueException {

        fifoQueue.add(1);
        fifoQueue.add(2);
        fifoQueue.add(3);
        assertEquals(3, fifoQueue.size());
        fifoQueue.get();
        assertEquals(2, fifoQueue.size());
        fifoQueue.get();
        assertEquals(1, fifoQueue.size());
        fifoQueue.get();
        assertEquals(0, fifoQueue.size());

    }

    @Test
    void returningANumberFromEmptyFIFOResultsInQueueException() {

        assertEquals(0, fifoQueue.size());
        assertThrows(QueueException.class,
                () -> fifoQueue.get()
        );
    }

    @Test
    public void isEmptyIsTrueOnlyForNoElementsInFIFO() {

        assertTrue(fifoQueue.isEmpty());

        fifoQueue.add(1);

        assertFalse(fifoQueue.isEmpty());

    }

    @Test
    void aFIFOAcceptsThousandsOfNumbers() throws IOException {

        Files.lines(Paths.get(path)).map(dataPoint -> Integer.valueOf(dataPoint.replaceAll("\\W", "")))
                .forEach(fifoQueue::add);

        assertEquals(5000, fifoQueue.size());

    }
}
