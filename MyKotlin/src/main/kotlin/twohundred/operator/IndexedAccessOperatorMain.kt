package twohundred.operator

class IndexedAccessOperatorMain {
}

fun main() {
    val indexedAccessOperator = IndexedAccessOperator("Kotlin", "2023-01-10")
    println(indexedAccessOperator[0])
    println(indexedAccessOperator[1])
    println(indexedAccessOperator[-1])

    indexedAccessOperator[0] = "Java"
    println(indexedAccessOperator.name)

    indexedAccessOperator[1] = "1970-01-01"
    println(indexedAccessOperator.birthday)

}