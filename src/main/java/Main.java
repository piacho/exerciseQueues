import com.greg.exercisequeues.common.QueueException;

import java.io.IOException;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) throws QueueException, IOException {
        int[] testArray = {1,2,3};

        DummyClassExtendingLIFO extendingLIFO = new DummyClassExtendingLIFO();
        DummyClassExtendingFIFO extendingFIFO = new DummyClassExtendingFIFO();

        extendingLIFO.addFromArrayToLIFO(testArray);

        extendingLIFO.printAllElements(extendingLIFO);

        extendingLIFO.addFromStreamToLIFO(Stream.of(-20, 40, -2000, 88));

        System.out.println("The max number provided to LIFO is: "+
                extendingLIFO.getTheMaxElement(extendingLIFO));

        extendingFIFO.addUsersInputToFIFO();
        extendingFIFO.printFIFO(extendingFIFO);

    }
}