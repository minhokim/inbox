package twohundred.object_declaration

class ObjectDeclarationMain {
}
// 프로그램 전체에서 공유 가능한 유일한 객체
// 클래스를 선언하듯 객체를 선언하고 있다.
// 이렇게 하면 Person이라는 식별자로 객체에 바로 접근할 수 있다.
// 싱글톤 패턴 코드없이 object 키워드로 단 하나만 존재하는 객체를 만들수 있다.
object Person {
    var name:String = ""
    var age:Int = 0

    fun print() {
        println("name : ${name}")
        println("age : ${age}")
    }
}

fun main() {
    // 식별자 Person으로 객체에 바로 접근 가능
    Person.name = "Singleton"
    Person.age = 45
    Person.print()
}