package twohundred

class ConstructorMain constructor(name:String, age:Int) {
    val name:String
    val age:Int

    init {
        this.name = name
        this.age = age
    }
}

fun main() {
    val person = ConstructorMain("홍길동", 46)
    println("이름:${person.name}")
    println("나이:${person.age}")
}