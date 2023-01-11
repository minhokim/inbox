package twohundred.exception

class ExceptionMain {
}

fun main() {
    val str = "abcd"

    try {
        val num = str.toInt()

        println(num)
    } catch (e:NumberFormatException) {
        println("exception")
    } finally {
        println("exit")
    }

}