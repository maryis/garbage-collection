package com;

import com.gc.GarbageCollector;
import com.other.NormalClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.ExecutorService;

@SpringBootApplication
public class App implements CommandLineRunner {

    private NormalClass normalClass = new NormalClass();

    @Autowired
    private GarbageCollector gc;
    @Autowired
    private ExecutorService executorService;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Application Started");
        Thread gcThread = new Thread(gc::start);
        gcThread.setDaemon(true);
        executorService.submit(gcThread);
        executorService.submit(normalClass::run);
    }
}
