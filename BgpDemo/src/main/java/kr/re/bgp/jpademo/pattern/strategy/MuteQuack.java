package kr.re.bgp.jpademo.pattern.strategy;

public class MuteQuack implements QuackBehavior {
    public void quack() {
        System.out.println("Mute Quack");
    }
}
