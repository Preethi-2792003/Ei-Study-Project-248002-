import java.util.*;
interface Observer {
    void update(String news);
}
class NewsAgency {
    private List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer o) {
        observers.add(o);
    }

    public void notifyObservers(String news) {
        for (Observer o : observers) {
            o.update(news);
        }
    }
}
class Subscriber implements Observer {
    private String name;

    public Subscriber(String name) {
        this.name = name;
    }

    @Override
    public void update(String news) {
        System.out.println(name + " received news: " + news);
    }
}

public class ObserverDemo {
    public static void main(String[] args) {
        NewsAgency agency = new NewsAgency();
        agency.addObserver(new Subscriber("Alice"));
        agency.addObserver(new Subscriber("Bob"));
        agency.notifyObservers(" Observer Pattern Implemented!");
    }
}
