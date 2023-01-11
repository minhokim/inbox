package twohundred.inheritance

class InheritanceMain {
}

open class Person(val name:String, val age:Int)

class Student(name:String, age:Int, val id:Int):Person(name, age)

fun main(args:Array<String>) {
    val person = Person("홍길동", 35)
    val student = Student("김길동", 23, 20171217)

    println("Person name : ${person.name}, age : ${person.age}")
    println("Student name : ${student.name}, age : ${student.age}, id : ${student.id}")
}