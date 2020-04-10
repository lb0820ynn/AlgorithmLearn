package learn.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 生产者消费者问题
 * Created by liubin on 2020/3/29.
 */
public class ProducerConsumer {

    /**
     * 商品数量
     */
    int count;
    public ReentrantLock lock = new ReentrantLock();
    public Condition notFull = lock.newCondition();
    public Condition notEmpty = lock.newCondition();

    String synLock = "synLock";

    BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(20);
    AtomicInteger q = new AtomicInteger(0);



    public static void main(String[] args) {
        ProducerConsumer pc = new ProducerConsumer();
//        new Thread(pc.new Producer(), "生产者-1").start();
//        new Thread(pc.new Consumer(), "消费者-1").start();


//        for (int i = 0; i < 5; i++) {
//            new Thread(pc.new Producer(), "生产者" + i).start();
//            new Thread(pc.new Consumer(), "消费者" + i).start();
//        }
        for (int i = 0; i < 5; i++) {
            new Thread(pc.new ProducerQueue(), "生产者" + i).start();
            new Thread(pc.new ConsumerQueue(), "消费者" + i).start();
        }
    }

    class ProducerQueue implements Runnable {

        @Override
        public void run() {
            while (true){
                try {
                    Thread.sleep(500);
                    int i = q.incrementAndGet();
                    System.out.println(Thread.currentThread().getName() + " 生产 -> " + i);
                    queue.put(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class ConsumerQueue implements Runnable {

        @Override
        public void run() {
            while (true){
                try {
                    Thread.sleep(500);
                    Integer value = queue.take();
                    System.out.println(Thread.currentThread().getName() + " 消费数字 -> " + value);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class ProducerSyn implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 50; i++) {
                synchronized (synLock) {
                    try {
                        while (count == 10) {
                            synLock.wait();
                        }
                        count++;
                        System.out.println(Thread.currentThread().getName() + "---- " + count);
                        synLock.notifyAll();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    class ConsumerSyn implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 50; i++) {
                synchronized (synLock) {
                    try {
                        while (count == 0) {
                            synLock.wait();
                        }
                        count--;
                        System.out.println(Thread.currentThread().getName() + "---- " + count);
                        synLock.notifyAll();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


    class Producer implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 50; i++) {
                try {
                    lock.lock();
                    while (count == 10) {
                        notFull.await();
                    }
                    count++;
                    System.out.println(Thread.currentThread().getName() + "---- " + count);
                    notEmpty.signalAll();

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    class Consumer implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 50; i++) {
                try {
                    lock.lock();
                    while (count == 0) {
                        notEmpty.await();
                    }
                    count--;
                    System.out.println(Thread.currentThread().getName() + "---- " + count);
                    notFull.signalAll();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }


}
