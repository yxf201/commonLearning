package test;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONWrappedObject;
import lombok.Data;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Data
public class Pizza {
    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    public enum Status{
        ORDERED(5){
            @Override
            public boolean isOrdered(){
                return true;
            }
        },
        READY(2){
            @Override
            public boolean isReady() {
                return true;
            }
        },
        DELIVERED (0){
            @Override
            public boolean isDelivered() {
                return true;
            }
        };

        private int timeToDelivery;

        public boolean isOrdered(){return false;}
        public boolean isReady(){return false;}
        public boolean isDelivered(){return false;}

        private Status(int timeToDelivery){
            this.timeToDelivery = timeToDelivery;
        }

        public int getTimeToDelivery(){
            return this.timeToDelivery;
        }
    }

    private Status status;

    public boolean isDeliverable(){
        return this.status.isReady();
    }

    public void print(){
        System.out.println(isDeliverable() + "," + this.getStatus().getTimeToDelivery());
    }

    public static void main( String args[]){
        Pizza p = new Pizza();
        p.setStatus(Status.READY);
        p.print();
        p.setStatus(Status.DELIVERED);
        p.print();
    }

    private static EnumSet<Status> undeliveredPizzaStatuses =
            EnumSet.of(Status.ORDERED, Status.READY);

    public static List<Pizza> getAllUndeliveredPizzas(List<Pizza> input){
        return input.stream().filter(
                x -> undeliveredPizzaStatuses.contains(x.getStatus())
        ).collect(Collectors.toList());
    }

    public enum PizzaDeliverySystemConfiguration {
        DEFAULT{
            @Override
            public PizzaDeliveryStrategy getStrategy(){
                return PizzaDeliveryStrategy.EXPRESS;
            }
        };

        PizzaDeliverySystemConfiguration(){

        }

        private PizzaDeliveryStrategy strategy = PizzaDeliveryStrategy.NORMAL;

        public static PizzaDeliverySystemConfiguration getInstance(){
            return DEFAULT;
        }

        public PizzaDeliveryStrategy getStrategy(){
            return strategy;
        }
    }

    public enum PizzaDeliveryStrategy{
        EXPRESS{
            @Override
            public void deliver(Pizza pz){
                System.out.println("Pizza will be delivered in express mode");
            }
        },
        NORMAL{
            @Override
            public void deliver(Pizza pz){
                System.out.println("Pizza will be delivered in normal mode");
            }
        };

        public abstract void deliver(Pizza pizza);
    }

    public void deliver() {
        if (isDeliverable()) {
            PizzaDeliverySystemConfiguration.getInstance().getStrategy()
                    .deliver(this);
            this.setStatus(Status.DELIVERED);
        }
    }

    @Test
    public void testDeliver(){
        Pizza pz = new Pizza();
        pz.setStatus(Status.READY);

        pz.deliver();
        assertTrue(pz.getStatus() == Status.DELIVERED);
    }

    @Test
    public void testEnumSet(){
        List<Pizza> pizzaList = new ArrayList<>();
        Pizza pizza1 = new Pizza();
        pizza1.setStatus(Status.DELIVERED);
        Pizza pizza2 = new Pizza();
        pizza2.setStatus(Status.ORDERED);
        Pizza pizza3 = new Pizza();
        pizza3.setStatus(Status.ORDERED);
        Pizza pizza4 = new Pizza();
        pizza4.setStatus(Status.READY);

        pizzaList.add(pizza1);
        pizzaList.add(pizza2);
        pizzaList.add(pizza3);
        pizzaList.add(pizza4);

        List<Pizza> undeliveredPizzas = Pizza.getAllUndeliveredPizzas(pizzaList);
        assertTrue(undeliveredPizzas.size() == 3);

    }

    public static EnumMap<Status, List<Pizza>> groupPizzaByStatus(List<Pizza> pzList){
        EnumMap<Status, List<Pizza>> map = pzList.stream().collect(
                Collectors.groupingBy(Pizza::getStatus,
                        () -> new EnumMap<>(Status.class), Collectors.toList())
        );
        return map;
    }

    @Test
    public void testJson() throws JsonProcessingException {
        Pizza pizza = new Pizza();
        pizza.setStatus(Status.READY);
        System.out.println(Arrays.toString(Status.values()));
        System.out.println( new ObjectMapper().writeValueAsString(Status.READY) );
    }


























}
