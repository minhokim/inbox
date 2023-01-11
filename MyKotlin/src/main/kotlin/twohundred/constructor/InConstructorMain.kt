package twohundred.constructor

class InConstructorMain(val name:String, val speed:Int = 0)

fun main() {
    val car = InConstructorMain("my car")
    println(car.name)
    println(car.speed)
}