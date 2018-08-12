public class JumpList{

    ARun[] runs;
    public int head = -1;
    public int tail = -1;

    public JumpList (int queueLength) {
        runs = new ARun[queueLength+1];
        System.out.println("queuelength = "+queueLength);
        System.out.println("Jumplist length = "+runs.length);
    }

    public boolean isEmpty() {
        return (head == -1 && tail == -1);
    }

    public boolean isFull() {
        return (head == tail-1 || (tail == 0 && head == runs.length-1));
    }

    public void enqueue(ARun push) {

        if (!isFull()) {
            if (head == runs.length-1) { head = 0; }
            else { head++; }
            runs[head] = push;
            if (tail == -1) { tail++; }
            System.out.println("Queued: "+push.getStart()+", head = "+head+", tail = "+tail);
        }
        else { System.out.println("Error: Queue is full"); }
    }

    public ARun dequeue() {
        ARun pop = new ARun();

        if (!isEmpty()) {
            pop = runs[tail];
            if (head != tail) {
                if (tail == runs.length-1) { tail = 0; }
                else { tail++; }
                System.out.println("Dequeued: "+pop.getStart()+", head = "+head+", tail = "+tail);
            }
            else {
                head = -1;
                tail = -1;
                System.out.println("Dequeued: "+pop.getStart()+", head = "+head+", tail = "+tail);
                System.out.println("Queue emptied!");
            }
        }
        else { System.out.println("Error: Queue is empty"); }

        return pop;
    }
}
