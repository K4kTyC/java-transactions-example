package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ServiceExample {

    @Autowired
    RepoExample repo;

    @EventListener(ApplicationReadyEvent.class)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void method1() {
        repo.save(new EntityExample("m1"));
        try {
            method2();
        } catch (Exception ignored) {}
    }

    //@Transactional(propagation = Propagation.REQUIRES_NEW)
    public void method2() {
        repo.save(new EntityExample("m2"));
        throw new RuntimeException();
    }
}
