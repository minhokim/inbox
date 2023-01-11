package twohundred.inheritance

class AnyBuildingMain {
}

fun main() {
    val building = AnyBuilding("kotlin", area = 100)
    printObject(building)
}

fun printObject(any:Any) {
    println(any.toString())
    println()
    println(any)
}