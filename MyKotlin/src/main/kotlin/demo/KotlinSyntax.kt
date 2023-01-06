package demo

fun printMessage(message: String): Unit {
    println(message)
}

fun printMessageWithPrefix(message: String, prefix: String = "Info") {
    println("[$prefix] $message")
}

fun readOnlyVal() {
    val a: Int = 1
    val b = 10
    val c: Int
    c = 3;
}


fun sum(x: Int, y: Int): Int {
    return x + y
}

fun multiply(x: Int, y: Int) = x * y

fun maxOf(a: Int, b: Int): Int {
    if (a > b) {
        return a
    } else {
        return b
    }
}

//Conditional expressions
fun maxOf2(a: Int, b: Int) = if (a > b) a else b

//for loop
fun forLoop() {
    val items = listOf("apple", "banana", "kiwifruit")
    for (item in items) {
        println(item)
    }
}

fun forLoop2() {
    val items = listOf("apple", "banana", "kiwifruit")
    for (index in items.indices) {
        println("for : item at $index is ${items[index]}")
    }
}

fun whileLoop() {
    val items = listOf("apple", "banana", "kiwifruit")
    var index = 0
    while (index < items.size) {
        println("while : item at $index is ${items[index]}")
        index++
    }
}

//when expressions
fun describe(obj: Any): String =
    when (obj) {
        1           -> "One"
        "Hello"     -> "Greeting"
        is Long     -> "Long"
        !is String  -> "Not a string"
        else        -> "Unknown"
    }

//Ranges
fun range() {
    val x = 10
    val y = 9
    if (x in 1..9) {
        println("fits in range")
    } else {
        println("fits in not range")
    }
}

//number is out of range
fun outOfRange() {
    val list = listOf("a", "b", "c")

    if (-1 !in 0..list.lastIndex) {
        println("01 is out of range")
    }
    println("list.size : " + list.size)
    println("list.indices : " + list.indices)
    if (list.size !in list.indices) {
        println("list size is out of valid list indices range, too")
    }
}

//Iterate over a range
fun iterateRange() {
    for (x in 1..5) {
        print(x)
    }
    println()
    println("-----")
    for (x in 1..10 step 2) {
        print(x)
    }
    println()
    println("-----")
    for (x in 9 downTo 0 step 3) {
        print(x)
    }
}

//Collections



fun main() {
    printMessage("Hello")
    printMessageWithPrefix("Hello", "Log")
    printMessageWithPrefix("Hello")
    printMessageWithPrefix(prefix = "Log", message = "Hello")
    printMessageWithPrefix(message = "Hello2", prefix = "Debug")
    println(sum(1, 2))
    println(multiply(2, 4))
    readOnlyVal()

    //Variables
    var x = 5
    x += 1
    println("x = $x")

    //Conditional expressions
    println(maxOf(2, 3))
    println(maxOf2(3, 2))

    //for loop
    forLoop()
    forLoop2()

    //while loop
    whileLoop()

    //when expressions
    println(describe(1))

    //range
    range()

    //out of range
    outOfRange()

    //Iterate range
    iterateRange()
}