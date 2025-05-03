package kr.re.bgp.jpademo.pattern.strategy;

public class Quack implements QuackBehavior {
    public void quack() {
        System.out.println("Quack");
    }
}
