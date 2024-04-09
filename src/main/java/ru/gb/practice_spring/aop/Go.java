package ru.gb.practice_spring.aop;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Go {
    @Timer
    public void go() {
        System.out.println("Go");
        List<String> list = new ArrayList<>(1000000000);
    }
}
