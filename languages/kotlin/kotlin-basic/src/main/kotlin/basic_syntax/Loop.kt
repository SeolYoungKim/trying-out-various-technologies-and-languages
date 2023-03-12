package basic_syntax

val items = listOf("apple", "banana", "kiwi")
fun main() {
    for (item in items) {
        println(item)
    }

    for (index in items.indices) {
        println("item at $index is ${items[index]}")
    }

    println(describe(1))
    println(describe("Hello"))
    println(describe(1L))
    println(describe(Rectangle(1.0, 2.0)))
    println(describe("Hi"))
}

fun describe(obj: Any): String =
    when (obj) {
        1 -> "One"
        "Hello" -> "Greeting"
        is Long -> "Long"
        !is String -> "Not a string"
        else -> "Unknown"
    }