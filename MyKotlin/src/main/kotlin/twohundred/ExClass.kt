package twohundred

class ExClass {
    var name:String = ""
    var age:Int = 0
}

fun main(args:Array<String>) {
    val person:ExClass = ExClass()
    person.name = "홍길동"
    person.age = 36

    val person2:ExClass = ExClass()
    person2.name = "김미영"
    person2.age = 29
}