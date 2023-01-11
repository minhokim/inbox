package twohundred.exception

class ThrowException {
}

fun main() {
    try {
        something()
    } catch (e:Exception) {
        println(e.message)
    }
}

fun something() {
    val num1 = 10
    val num2 = 0
    div(num1, num2)
}

fun div(a:Int, b:Int):Int {
    if(b == 0)
        throw Exception("not divide by zero")

    return a / b
}