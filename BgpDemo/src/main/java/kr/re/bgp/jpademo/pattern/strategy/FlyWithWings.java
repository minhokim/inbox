package kr.re.bgp.jpademo.pattern.strategy;

public class FlyWithWings implements FlyBehavior {
    public void fly() {
        System.out.println("I'm flying with wings");
    }
}
