package twohundred.inheritance

class OverridingPropertyMain {
}

open class CCC {
    open var number = 10
        get() {
            println("CCC number Getter")
            return field
        }

        set(value) {
            println("CCC number Setter")
            field = value
        }
}

class DDD:CCC() {
    override var number:Int
        get() {
            println("DDD number Getter")
            return super.number
        }
        set(value) {
            println("DDD number Setter")
            super.number = value
        }
}

fun main() {
    val test = DDD()
    test.number = 5
    println(test.number)
}