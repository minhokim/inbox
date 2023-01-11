package twohundred.access_modifier

class AccessModifierPrivateTest {
}

//Test 파일 내에서만 접근 가능
private var num = 10

//Test 파일 내에서만 접근 가능
private fun print() = println(num)

//어디서나 접근 가능
public fun hello(value:Int) {
    num = value
    print()
}

public class Person(age:Int) {
    //디폴트 Setter를 private으로 지정. Setter는 Person 클래스 내부에서만 접근 가능
    public var age = age
        private set

    public val isYoung public get() = age < 30
}
