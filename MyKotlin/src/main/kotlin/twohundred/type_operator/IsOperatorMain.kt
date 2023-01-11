package twohundred.type_operator

import twohundred.inheritance.Person
import twohundred.inheritance.Student

class IsOperatorMain {
}

class Professor(name:String, age:Int):Person(name, age)

//참조 변수가 어떤 클래스의 인스턴스를 가리키고 있는지 확인하는 방법
fun main() {
    val person:Person = Student("Mark Zuckerberg", 33, 20172211)

    //is 연산자는 왼쪽 피연산자가 실제로 가리키고 있는 객체를 오른쪽 피연산자로 가리킬 수 있는지 여부를 조사한다.
    print("${person is Person} ")
    print("${person is Student} ")
    print("${person is Professor} ")

    val person2:Person = Professor("Kim", 48)
    print("${person2 is Person} ")
    print("${person2 is Student} ")
    print("${person2 !is Professor} ")

    //사용예
    /*
    val person:Person
    ...
    when(person) {
        is Person -> {}
        is Student -> {}
        is Professor -> {}
    }
    */
}