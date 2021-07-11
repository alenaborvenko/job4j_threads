package concurrent;

public class ThreadState {
    public static void main(String[] args) throws InterruptedException {
        Thread first = new Thread(() -> {
            System.out.println(Thread.currentThread().getName());
        });
        Thread second = new Thread(() -> {
            System.out.println(Thread.currentThread().getName());
        });
        System.out.println(first.getState());
        System.out.println(second.getState());
        first.start();
        second.start();
        while (first.getState() != Thread.State.TERMINATED) {
            System.out.println(first.getName() + " " + first.getState());
        }
        System.out.println(first.getState());
        while (second.getState() != Thread.State.TERMINATED) {
            System.out.println(second.getName() + " " + second.getState());
        }
        System.out.println(second.getState());
        System.out.println(Thread.currentThread().getName());
        System.out.println("Stop work");
    }
}
