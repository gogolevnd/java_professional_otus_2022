package ru.otus;

import java.util.*;

public class CustomerService {
    private TreeMap<Customer, String> map = new TreeMap<>();

    public Map.Entry<Customer, String> getSmallest() {
        Map.Entry<Customer, String> entry = map.firstEntry();
        return entry != null ? new AbstractMap.SimpleEntry<>(entry.getKey().clone(), entry.getValue()) : null;
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        Map.Entry<Customer, String> entry = map.higherEntry(customer);
        return entry != null ? new AbstractMap.SimpleEntry<>(entry.getKey().clone(), entry.getValue()) : null;
    }

    public void add(Customer customer, String data) {
        map.put(customer, data);
    }
}
