package kr.re.bgp.jpademo.pattern.strategy;

public class FlyNoWay implements FlyBehavior{
    public void fly() {
        System.out.println("FlyNoWay");
    }
}
