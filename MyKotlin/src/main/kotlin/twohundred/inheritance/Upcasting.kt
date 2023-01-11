package twohundred.inheritance

class Upcasting {
}

open class UpPerson(val name:String, val age:Int)

class UpStudent(name:String, age:Int, val id:Int):UpPerson(name, age)

fun main() {
    val upPerson:UpPerson = UpStudent("John", 32, 20171218)

    println("upPerson name : ${upPerson.name}, age : ${upPerson.age}")
}