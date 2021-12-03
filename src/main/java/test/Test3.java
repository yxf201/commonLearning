package test;

public class Test3 {
    public static void main(String[] args) throws InterruptedException {
        new Thread() {
            @Override
            public void run() {
                throw new RuntimeException("1");
            }
        }.start();
        while (true) {
            Thread.sleep(1000);
            System.out.println(1);
        }
    }
}
