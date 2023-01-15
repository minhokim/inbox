package twohundred.campanion_object

class CampanionObjectMain {
}

class Person private constructor() {
    // 동반자 객체 정의
    companion object {
        fun create():Person {
            countCreated += 1
            return Person()
        }

        // create 멤버 함수를 통해서만 Person 객체를 생성할 수 있도록 하기 위해
        // 생성자의 접근 지정자를 private으로 지정.
        var countCreated = 0
            private set
    }
}

fun main() {
    // 동반자 객체는 자신이 속한 클래스의 이름으로 접근할 수 있다.
    val a = Person.create()
    val b = Person.create()
    println(Person.countCreated)
}