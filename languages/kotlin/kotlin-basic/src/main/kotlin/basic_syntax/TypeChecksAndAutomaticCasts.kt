package basic_syntax

fun getStringLength(obj: Any): Int? {
    if (obj is String) {
        return obj.length
    }

    return null
}

fun getStringLength2(obj: Any): Int? {
    if (obj !is String) return null

    return obj.length
}

