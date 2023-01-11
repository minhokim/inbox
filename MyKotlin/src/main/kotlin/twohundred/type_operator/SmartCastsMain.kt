package twohundred.type_operator

class SmartCastsMain {
}

fun main() {
    val number:Int? = null
    val number2 = 1225

    checkNull(number)
    checkNull(number2)
}

fun checkNull(any:Any?) {
    if(any == null) {
        println("null")
        return
    }

    println(any.toString())
}