package com.base;


/**
 * 无注解
 */
public class MyJob {

    public void execute() {
        System.out.println("执行无注解Job");
    }

    public void findUserName(String name, Integer age) {
        System.out.println("欢迎" + age + "岁的" + name + "光临");
    }
}