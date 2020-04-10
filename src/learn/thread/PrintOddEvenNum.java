package learn.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 交替打印奇偶数
 * Created by liubin on 2020/3/29.
 */
public class PrintOddEvenNum {

    int num = 0;
    public ReentrantLock lock = new ReentrantLock();
    public Condition condition = lock.newCondition();

    public static void main(String[] args) {
        PrintOddEvenNum lock = new PrintOddEvenNum();
        Thread odd = new Thread(new PrintOdd(lock));
        Thread even = new Thread(new PrintEven(lock));

        odd.start();
        even.start();

    }

    static class PrintOdd implements Runnable {

        PrintOddEvenNum mLock;

        public PrintOdd(PrintOddEvenNum lock) {
            mLock = lock;
        }

        @Override
        public void run() {
            while (mLock.num < 101) {
                try {
                    mLock.lock.lock();
                    if (mLock.num % 2 == 0) {
                        mLock.condition.await();
                    } else {
                        System.out.println("奇 " + Thread.currentThread().getName() + " num = " + mLock.num);
                        mLock.num++;
                        mLock.condition.signalAll();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    mLock.lock.unlock();
                }
            }
        }
    }

    static class PrintEven implements Runnable {

        PrintOddEvenNum mLock;

        public PrintEven(PrintOddEvenNum lock) {
            mLock = lock;
        }

        @Override
        public void run() {
            while (mLock.num < 101) {
                try {
                    mLock.lock.lock();
                    if (mLock.num % 2 == 1) {
                        mLock.condition.await();
                    } else {
                        System.out.println("偶数线程" + Thread.currentThread().getName() + " num = " + mLock.num);
                        mLock.num++;
                        mLock.condition.signalAll();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    mLock.lock.unlock();
                }
            }
        }
    }
/*
    static class PrintOdd implements Runnable {

        PrintOddEvenNum mLock;

        public PrintOdd(PrintOddEvenNum lock) {
            mLock = lock;
        }

        @Override
        public void run() {
            synchronized (mLock) {
                while (mLock.num < 101) {
                    try {
                        if (mLock.num % 2 == 0) {
                            mLock.wait();
                        } else {
                            System.out.println("奇数线程" + Thread.currentThread().getName() + " num = " + mLock.num);
                            mLock.num++;
                            mLock.notifyAll();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    static class PrintEven implements Runnable {

        PrintOddEvenNum mLock;

        public PrintEven(PrintOddEvenNum lock) {
            mLock = lock;
        }

        @Override
        public void run() {
            synchronized (mLock) {
                while (mLock.num < 101) {
                    try {
                        if (mLock.num % 2 == 1) {
                            mLock.wait();
                        } else {
                            System.out.println("偶数线程" + Thread.currentThread().getName() + " num = " + mLock.num);
                            mLock.num++;
                            mLock.notifyAll();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
*/

}
