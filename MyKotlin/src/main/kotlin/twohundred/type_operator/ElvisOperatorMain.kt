package twohundred.type_operator

class ElvisOperatorMain {
}

fun main() {
    val number:Int? = null
    println(number ?: 0)

    val number2:Int? = 15
    println(number2 ?: 0)
}

