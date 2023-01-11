package twohundred.inheritance

class OverridingMain {
}

open class AAA {
    //멤버 함수도 클래스와 마찬가지로 오버라이딩을 허용하려면 open 키워드를 붙여주어야 한다.
    open fun func() = println("AAA")
}

class BBB:AAA() {
    //오버라이딩을 할 때 override 키워드를 붙여야 한다.
    override fun func() {
        super.func()
        println("BBB")
    }
}

fun main() {
    AAA().func()
    BBB().func()
}