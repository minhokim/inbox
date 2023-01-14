package twohundred.access_modifier

class ExtensionPropertyMain {
}

val String.isLarge: Boolean
    get() = this.length >= 10

fun main() {
    println("1234567890".isLarge)
    println("500 원".isLarge)
}