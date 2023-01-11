package twohundred.type_operator

import twohundred.inheritance.Person
import twohundred.inheritance.Student

class DowncastingMain {
}

fun main() {
    val person:Person = Student("John", 32, 2017)
    val person2:Person = Person("Jack", 29)

    //as 연산자는 왼쪽 피연산자의 타입을 오른쪽 피연산자로 캐스팅한다.
    var person3:Student? = person as? Student
    println(person3?.name)

    person3 = person2 as? Student
    println(person3?.name)
}

