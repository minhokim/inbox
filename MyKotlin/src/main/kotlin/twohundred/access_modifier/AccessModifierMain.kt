package twohundred.access_modifier

class AccessModifierMain {
}

class Rectangle(private val width:Int, private val height:Int) {
    val area:Int
        get() = width * height
}

fun main() {
    val rect = Rectangle(5, 7)
//    println(rect.width) //에러
    println(rect.area)
}

