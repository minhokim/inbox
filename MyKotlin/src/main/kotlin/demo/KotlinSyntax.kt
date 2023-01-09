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
    println("----- forLoop START -----")
    val items = listOf("apple", "banana", "kiwifruit")
    for (item in items) {
        println(item)
    }
    println("----- forLoop END -----")
    println()
}

fun forLoop2() {
    println("----- forLoop2 START -----")
    val items = listOf("apple", "banana", "kiwifruit")
    for (index in items.indices) {
        println("for : item at $index is ${items[index]}")
    }
    println("----- forLoop2 END -----")
    println()
}

fun whileLoop() {
    println("----- whileLoop START -----")
    val items = listOf("apple", "banana", "kiwifruit")
    var index = 0
    while (index < items.size) {
        println("while : item at $index is ${items[index]}")
        index++
    }
    println("----- whileLoop END -----")
    println()
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
    println("----- range START -----")
    val x = 10
    val y = 9
    if (x in 1..9) {
        println("fits in range")
    } else {
        println("fits in not range")
    }
    println("----- range END -----")
    println()
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
    println("----- iterateRange START -----")
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
    println()
    println("----- iterateRange END -----")

}

//Collections
fun collections() {
    println("----- collections START -----")
    val items = listOf("apple", "banana", "kiwifruit")
    for (item in items) {
        println(item)
    }

    when {
        "orange" in items -> println("juicy")
        "apple" in items -> println("apple is fine too")
    }

    println("-------------------------")

    val fruits = listOf("banana", "avocado", "apple", "kiwifruit")
    fruits
        .filter { it.startsWith("a") }
        .sortedBy { it }
        .map { it.uppercase() }
        .forEach { println(it) }
    println("----- collections END -----")
}

//Nullable Values and null checks
fun parseInt(str: String): Int? {
    println("----- parseInt START -----")
    try {
        val num: Int = str.toInt();
        println("num: $num")
        return num
    } catch (e: NumberFormatException) {
        println("Not Number: $str")
        return 0
    }
    println("----- parseInt END -----")
}

fun printProduct(arg1: String, arg2: String) {
    println("----- printProduct START -----")
    val x = parseInt(arg1)
    val y = parseInt(arg2)

    if (x != null && y != null) {
        println(x * y)
    }
    else {
        println("'$arg1' or '$arg2' is not a number")
    }
    println("----- printProduct END -----")
}


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

    //Collections
    collections()

    printProduct("2", "3")
}