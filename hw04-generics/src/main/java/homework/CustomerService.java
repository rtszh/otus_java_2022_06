package homework;


import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class CustomerService {

    private TreeMap<Customer, String> map;
    private Iterator iterator;

    public CustomerService() {
        this.map = new TreeMap<>(Comparator.comparing(Customer::getScores));
    }

    //todo: Сделано -- 3. надо реализовать методы этого класса
    //важно подобрать подходящую Map-у, посмотрите на редко используемые методы, они тут полезны

    public Map.Entry<Customer, String> getSmallest() {
        //Возможно, чтобы реализовать этот метод, потребуется посмотреть как Map.Entry сделан в jdk
        iterator = map.entrySet().iterator();
        iterator.next();
        return Map.entry(
                new Customer(
                        map.firstKey().getId(),
                        map.firstKey().getName(),
                        map.firstKey().getScores()
                ),
                map.get(map.firstKey())
        );
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        if (iterator.hasNext()) {
            return (Map.Entry<Customer, String>) iterator.next();
        }
        return null;
    }

    public void add(Customer customer, String data) {
        map.put(customer, data);
    }
}
