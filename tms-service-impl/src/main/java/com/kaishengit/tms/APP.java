package com.kaishengit.tms;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class APP {

    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring*.xml");

        context.start();
        System.out.println("-----------已启动------------");

        System.in.read();
    }
}
