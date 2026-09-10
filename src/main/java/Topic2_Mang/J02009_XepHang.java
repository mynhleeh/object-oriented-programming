package Topic2_Mang;

import java.util.*;

class Customer {
    private int start, duration;

    public Customer(int start, int duration) {
        this.start = start;
        this.duration = duration;
    }

    public int getStart() {
        return this.start;
    }

    public int getDuration() {
        return this.duration;
    }

    @Override
    public String toString() {
        return this.start + " " + this.duration;
    }
}

public class J02009_XepHang {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        ArrayList<Customer> cus = new ArrayList<>();

        int n = scn.nextInt();
        while (n-- > 0)
            cus.add(new Customer(scn.nextInt(), scn.nextInt()));

        scn.close();
        cus.sort(Comparator.comparingInt(Customer::getStart)
                .thenComparingInt(Customer::getDuration));

        int last = 0;
        for (Customer c : cus)
            last = Math.max(last, c.getStart()) + c.getDuration();

        System.out.println(last);
    }
}
