package twohundred

class BuildingMain {
}

fun main(args:Array<String>) {
    val building = Building()
    building.name = "A Office"
    building.date = "2023-01-10"
    building.area = 120 * 8

    building.print()
}