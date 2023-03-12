package basic_syntax

fun main(args: Array<String>) {
    println("Hello")
    println(sum(1, 9))
    println(multiply(1, 9))
    printSum(1, 9)
    printMultiply(1, 9)
}

fun sum(a: Int, b: Int): Int {
    return a + b
}

fun multiply(a: Int, b: Int) = a * b

fun printSum(a: Int, b: Int): Unit {
    println("sum : ${a + b}")
}

fun printMultiply(a: Int, b: Int) {
    println("multiply : ${a * b}")
}