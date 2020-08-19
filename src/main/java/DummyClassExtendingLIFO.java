import com.greg.exercisequeues.common.QueueException;
import com.greg.exercisequeues.implementation.LIFOQueueImpl;

import java.util.Collections;
import java.util.LinkedList;
import java.util.stream.Stream;

public class DummyClassExtendingLIFO extends LIFOQueueImpl {

    void addFromArrayToLIFO(int[] array){
        for(int number : array){
            add(number);
        }
    }

    void addFromStreamToLIFO(Stream<Integer> stream){
        stream.forEach(element -> add(element));
    }

    int getTheMaxElement(LIFOQueueImpl lifo) throws QueueException {
        LinkedList<Integer> list = new LinkedList<>();
        long size = lifo.size();
        if(!lifo.isEmpty()){
            for(long i = 0; i < size; i++ )
            {
                list.add((Integer)lifo.get());
            }
            Collections.sort(list);
        }
        return list.getLast();
    }

    void printAllElements(LIFOQueueImpl lifo) throws QueueException {

        while(lifo.isEmpty() != true) {
            System.out.println(lifo.get());
        }
    }
}