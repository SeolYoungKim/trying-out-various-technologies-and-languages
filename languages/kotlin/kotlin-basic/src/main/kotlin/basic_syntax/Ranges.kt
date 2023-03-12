package basic_syntax

fun main() {
    val x = 10
    val y = 9
    if (x in 1..y + 1) {
        println("fits in range")
    }

    val list = listOf("a", "b", "c")
    if (-1 !in 0..list.lastIndex) {
        println("-1 is out of range")
    }

    if (list.size !in list.indices) {
        println("list size is out of valid list indices range, too")
    }

    for (a in 1..5) {
        print(a)
    }

    println()

    for (a in 1..10 step 2) {
        print(a)
    }

    println()
    for (a in 9 downTo 0 step 3) {
        print(a)
    }
}