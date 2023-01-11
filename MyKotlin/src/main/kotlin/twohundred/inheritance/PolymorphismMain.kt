package twohundred.inheritance

class PolymorphismMain {
}

open class EEE {
    open fun hello() = println("this is EEE")
}

class FFF:EEE() {
    override fun hello() = println("this is FFF")
}

fun main() {
    val one = EEE()
    val two = FFF()
    val three:EEE = two

    one.hello()
    two.hello()
    three.hello()
}