package edu.concurrency;

import com.google.gson.internal.bind.util.ISO8601Utils;

import java.util.concurrent.Semaphore;

public class Philosophers {

    public static void main(String[] args) {
        Semaphore sem = new Semaphore(2);
        for (int i = 1; i < 6; i++)
            new Philosopher(sem, i).start();
    }
}

// класс философа
class Philosopher extends Thread {
    Semaphore sem; // семафор. ограничивающий число философов
    // кол-во приемов пищи
    int num = 0;
    // условный номер философа
    int id;

    // в качестве параметров конструктора передаем идентификатор философа и семафор
    Philosopher(Semaphore sem, int id) {
        this.sem = sem;
        this.id = id;
    }

    public void run() {
        try {
            while (num < 3)// пока количество приемов пищи не достигнет 3
            {
                //Запрашиваем у семафора разрешение на выполнение
                System.out.println("Философ " + id + " пытается сесть за стол");
                sem.acquire();
                System.out.println("Философ " + id + " садится за стол");
                // философ ест
                sleep(1);
                num++;

//                System.out.println("Философ " + id + " выходит из-за стола");
//                sem.release();

                // философ гуляет
                sleep(1);
            }
        } catch (InterruptedException e) {
            System.out.println("у философа " + id + " проблемы со здоровьем");
        }
    }
}