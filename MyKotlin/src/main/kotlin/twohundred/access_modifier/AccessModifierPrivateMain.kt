package twohundred.access_modifier

class AccessModifierPrivateMain {
}

fun main() {
//    num = 5 //에러
    hello(15)

    val person = Person(10)
    println(person.age)
//    person.age = 20 //에러

    println(person.isYoung)
}