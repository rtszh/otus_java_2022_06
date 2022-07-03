package ru.otus;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

public class HelloOtus {
    public static void main(String[] args) {
        Multiset<String> bookStore = HashMultiset.create();
        bookStore.add("Potter");
        bookStore.add("Potter");
        bookStore.add("Potter");
        bookStore.add("Marvel");
        bookStore.add("Marvel");
        bookStore.add("Marvel");
        bookStore.add("Marvel");
        System.out.println("Full bookstore: " + bookStore);
    }
}