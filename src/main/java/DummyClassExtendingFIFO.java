import com.greg.exercisequeues.common.QueueException;
import com.greg.exercisequeues.implementation.FIFOQueueImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DummyClassExtendingFIFO extends FIFOQueueImpl {

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    void addUsersInputToFIFO() throws IOException, QueueException {

        System.out.println("Please provide 5 numbers\n");

        for(int i = 5; i > 0; i--) {
            try {
                add(Integer.valueOf(reader.readLine()));
            } catch (NumberFormatException ex) {
                throw new QueueException("Only integers accepted");
            }
        }
    }

    void printFIFO(FIFOQueueImpl fifo) throws QueueException {

        int elementNum = 0;
        while(!fifo.isEmpty()){
            elementNum += 1;
            System.out.println(elementNum + " number in the FIFO queue is: " + fifo.get());
        }
    }
}
