package kapitel_3.work.tests;

import kapitel_3.work.IWorker;

public class PrintWorker implements IWorker {
    public void work(Object data) {
        System.out.println(data);
    }
}
