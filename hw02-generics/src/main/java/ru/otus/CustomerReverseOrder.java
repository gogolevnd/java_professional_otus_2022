package ru.otus;

import java.util.*;

public class CustomerReverseOrder {

    private LinkedList<Customer> list = new LinkedList<>();

    public void add(Customer customer) {
        list.add(customer);
    }

    public Customer take() {
        return list.pollLast();
    }

}
