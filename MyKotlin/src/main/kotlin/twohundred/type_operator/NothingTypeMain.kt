package twohundred.type_operator

class NothingTypeMain {
}

fun throwing():Nothing = throw Exception()

fun main() {
    println("start")
    val i:Int = throwing()
    println(i)
//    validate(-1)
}

fun validate(num:Int) {
    val result:Int =
        if(num >= 0) num
        else throw Exception("num 음수")
}
