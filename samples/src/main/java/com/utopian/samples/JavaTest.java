package com.utopian.samples;

/**
 * Created by garamasu on 15-Sep-15.
 */
public class JavaTest {
    public void doSomething(char[] a) {

    }
    public void doSomething(Object a) {

    }
    public void doSomething(String a) {

    }
    public static void main(String[] args) {
        char[] tmp = null; // to prevent unambiguous compile time error, one has to explicitly typecast or do like this with assignment.
        JavaTest obj = new JavaTest();
        obj.doSomething(tmp);
        byte[] b = new String("1").getBytes();
    }
}
