package com.greg.exercisequeues.implementation;

import com.greg.exercisequeues.common.QueueException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;


public class LIFOQueueImplTest {

    private LIFOQueueImpl lifoQueue = new LIFOQueueImpl();
    private static final String path = "src/test/resources/testData.txt";
    private long testLongNumber = 3000000000L;
    private float testFloatNumber = 2.71f;
    private double testDoubleNumber = 0.123456789123456d;

    @BeforeEach
    void setup() {

        lifoQueue = new LIFOQueueImpl();

    }

    @Test
    void aLIFOQueueIsCreatedWithNoElements() {

        assertEquals(0, lifoQueue.size());

    }

    @Test
    void aLIFOQAcceptsIntegers() {

        Stream.of(1, 2, 3, 4, 5).forEach(lifoQueue::add);

        assertAll(
                () -> assertEquals(5, lifoQueue.get()),
                () -> assertEquals(4, lifoQueue.get()),
                () -> assertEquals(3, lifoQueue.get()),
                () -> assertEquals(2, lifoQueue.get()),
                () -> assertEquals(1, lifoQueue.get())
        );
    }

    @Test
    void aLIFOQAcceptsLongs() {

        Stream.of(testLongNumber, testLongNumber - 200000L, testLongNumber + 200000L).forEach(lifoQueue::add);

        assertAll(
                () -> assertEquals(testLongNumber + 200000L, lifoQueue.get()),
                () -> assertEquals(testLongNumber - 200000L, lifoQueue.get()),
                () -> assertEquals(testLongNumber, lifoQueue.get())
        );
    }

    @Test
    void aLIFOQAcceptsFractionalNumbers() throws QueueException {

        lifoQueue.add(testFloatNumber);

        assertEquals(testFloatNumber, lifoQueue.get());

        lifoQueue.add(testDoubleNumber);

        assertEquals(testDoubleNumber, lifoQueue.get());
    }

    @Test
    void aLIFOCanHaveMultipleNumericTypes() {

        lifoQueue.add(10);
        lifoQueue.add(testDoubleNumber);
        lifoQueue.add(testFloatNumber);
        lifoQueue.add(testLongNumber);

        assertAll(
                () -> assertEquals(testLongNumber, lifoQueue.get()),
                () -> assertEquals(testFloatNumber, lifoQueue.get()),
                () -> assertEquals(testDoubleNumber, lifoQueue.get()),
                () -> assertEquals(10, lifoQueue.get())
        );
    }

    @Test
    void addingNumbersToLIFOIncreasesItsSize() {

        assertEquals(0, lifoQueue.size());

        lifoQueue.add(1);

        assertEquals(1, lifoQueue.size());

        lifoQueue.add(2);

        assertEquals(2, lifoQueue.size());

        lifoQueue.add(3);

        assertEquals(3, lifoQueue.size());
    }

    @Test
    public void aLIFOTreatsDuplicatesAsIndividualElements() {

        lifoQueue.add(1);
        lifoQueue.add(1);
        lifoQueue.add(2);
        lifoQueue.add(5);
        lifoQueue.add(5);
        lifoQueue.add(10);

        assertEquals(6, lifoQueue.size());
    }

    @Test
    public void numbersAreCorrectlyReturnedFromLIFO() {

        lifoQueue.add(1);
        lifoQueue.add(2);
        lifoQueue.add(3);

        assertAll(
                () -> assertEquals(3, lifoQueue.get()),
                () -> assertEquals(2, lifoQueue.get()),
                () -> assertEquals(1, lifoQueue.get())
        );
    }

    @Test
    void numbersReturnedFromLIFOAreRemovedFromIt() throws QueueException {

        lifoQueue.add(1);
        lifoQueue.add(2);
        lifoQueue.add(3);
        assertEquals(3, lifoQueue.size());
        lifoQueue.get();
        assertEquals(2, lifoQueue.size());
        lifoQueue.get();
        assertEquals(1, lifoQueue.size());
        lifoQueue.get();
        assertEquals(0, lifoQueue.size());

    }

    @Test
    void returningANumberFromEmptyLIFOResultsInException() {

        assertEquals(0, lifoQueue.size());
        assertThrows(QueueException.class,
                () -> lifoQueue.get()
        );
    }

    @Test
    public void isEmptyIsTrueOnlyForNoElementsInLIFO() {

        assertTrue(lifoQueue.isEmpty());

        lifoQueue.add(1);

        assertFalse(lifoQueue.isEmpty());

    }

    @Test
    void aLIFOAcceptsThousandsOfNumbers() throws IOException {

        Files.lines(Paths.get(path)).map(dataPoint -> Integer.valueOf(dataPoint.replaceAll("\\W", "")))
                .forEach(lifoQueue::add);

        assertEquals(5000, lifoQueue.size());

    }
}
