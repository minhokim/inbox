package kr.re.bgp.jpademo.pattern.strategy;

public class Squeak implements QuackBehavior {
    public void quack() {
        System.out.println("Squeak");
    }
}
