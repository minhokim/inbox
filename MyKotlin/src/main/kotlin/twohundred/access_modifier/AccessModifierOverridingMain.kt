package twohundred.access_modifier

class AccessModifierOverridingMain {
}

open class SuperA(protected open val number: Int) {
    protected open fun hello() {
        println("hello")
    }
}

/* 상위 클래스에 정의된 protected 프로퍼티와 멤버 함수는
* 하위 클래스에서 오버라이딩을 통해 public으로 변경할 수 있다.
* 접근은 타입을 따른다.
* - a는 SuperA 타입으로 protected 인 프로퍼티와 멤버 함수에 접근하지 못한다.
* */
class SubB(number: Int): SuperA(number) {
    public override val number: Int
        get() = super.number

    public override fun hello() = super.hello()
}

fun main() {
    val b = SubB(26)
    val a: SuperA = b

    //a는 SuperA 타입이므로 protected인 number 프로퍼티와 hello 멤버 함수에 접근할 수 없다.
//    println(a.number) //에러
//    a.hello()   //에러
    println(b.number)
    b.hello()
}