package homework;


import java.util.ArrayDeque;
import java.util.Deque;

public class CustomerReverseOrder {
    private Deque<Customer> set;

    public CustomerReverseOrder() {
        this.set = new ArrayDeque<>();
    }

    //todo: Сделано -- 2. надо реализовать методы этого класса
    //надо подобрать подходящую структуру данных, тогда решение будет в "две строчки"

    public void add(Customer customer) {
        set.add(customer);
    }

    public Customer take() {
        return set.pollLast();
    }
}
