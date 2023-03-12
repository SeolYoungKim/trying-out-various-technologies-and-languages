package basic_syntax

fun parsInt(str: String): Int? {
    return str.toIntOrNull()
}

fun printProduct(arg1: String, arg2: String) {
    val x = parsInt(arg1)
    val y = parsInt(arg2)

    if (x != null && y != null) {
        println(x * y)
    }

    else {
        println("'$arg1' or '$arg2' is not a number")
    }
}