package ru.gb.practice_spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.gb.practice_spring.aop.Go;
import ru.gb.practice_spring.aop.Timer;

/**
 * Домашнее задание:
 * 1. Подключить базу данных к проекту "библиотека", который разрабатывали на прошлых уроках.
 * 1.1 Подключить spring-boot-starter-data-jpa (и необходимый драйвер) и указать параметры соединения в application.yml
 * 1.2 Для книги, читателя и факта выдачи описать JPA-сущности
 * 1.3 Заменить самописные репозитории на JPA-репозитории
 * Замечание: базу данных можно использовать любую (h2, mysql, postgres).
 */
@SpringBootApplication
public class PracticeSpringApplication {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(PracticeSpringApplication.class, args);
        Go go = context.getBean(Go.class);

        go.go();

    }
}
