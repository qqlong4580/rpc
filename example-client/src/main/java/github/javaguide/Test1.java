package github.javaguide;

import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Scanner;


public class Test1 {
    private static int flag = 1;
    static Object obj = new Object();
    public static void main(String[] args) {
//        WaitNotify waitNotify = new WaitNotify(1, 10);
//        new Thread(()->{
//            waitNotify.print("c",1,2);
//        }).start();
//        new Thread(()->{
//            waitNotify.print("b",2,3);
//        }).start();
//        new Thread(()->{
//            waitNotify.print("a",3,1);
//        }).start();
//
//        int a = (int) Math.sqrt(2);

    }
}

class WaitNotify{
    public void print(String str, int waitFlag, int nextFlag){
        for (int i = 0; i < number; i++) {
            synchronized (this){
                while(flag!=waitFlag){
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.print(str);
                flag = nextFlag;
                this.notifyAll();
            }
        }
    }

    int flag;
    int number;

    public WaitNotify(int flag, int number) {
        this.flag = flag;
        this.number = number;
    }
}
