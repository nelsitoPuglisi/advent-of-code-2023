fun main() {
    fun part1(input: List<String>): Int {
        return input.sumOf {
            val sum = (firstDigit(it) + lastDigit(it)).toInt()
            sum
        }
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 281)

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}

fun firstDigit(it: String) : String {
    try {
        val indexOfDigit = it.indexOfFirst { c -> c.isDigit() }
        val spelled = findSpelled(it)
        println("$it first $spelled")
        val indexOfSpelled = spelled.minOfOrNull { it.first } ?: -1
        return when {
            indexOfDigit == -1 -> spelled.find { it.first == indexOfSpelled }?.second ?: ""
            indexOfSpelled == -1 -> it[indexOfDigit].toString()
            indexOfDigit < indexOfSpelled -> it[indexOfDigit].toString()
            indexOfSpelled < indexOfDigit -> spelled.find { it.first == indexOfSpelled }?.second ?: ""
            else -> it.substring(0, indexOfSpelled)
        }
    } catch (e: Exception) {
        println("first $it")
        println(e)
        return ""
    }
}

fun lastDigit(it: String) : String {
    try {
        val indexOfDigit = it.indexOfLast { c -> c.isDigit() }
        val spelled = findSpelled(it)
        println("$it last $spelled")
        val indexOfSpelled = spelled.maxOfOrNull { it.first } ?: -1
        return when {
            indexOfDigit == -1 -> spelled.find { it.first == indexOfSpelled }?.second ?: ""
            indexOfSpelled == -1 -> it[indexOfDigit].toString()
            indexOfDigit > indexOfSpelled -> it[indexOfDigit].toString()
            indexOfSpelled > indexOfDigit -> spelled.find { it.first == indexOfSpelled }?.second ?: ""
            else -> it.substring(0, indexOfSpelled)
        }
    } catch (e: Exception) {
        println("last $it")
        println(e)
        return ""
    }
}

fun findSpelled(numbersAsString: String): List<Pair<Int, String>> {
    val listOfNumbersToNine = listOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine")
    return listOfNumbersToNine
        .flatMap { number ->
            val lista = mutableListOf<Pair<Int, String>>()
            var index = 0
            numbersAsString.windowed(number.length) {
                if (it == number) {
                    lista.add(Pair(index, spelled(it.toString())))
                }
                index++
            }
            lista
        }
        .filter { it.first != -1 }
}

fun spelled(word: String) : String {
    return when (word) {
        "one" -> "1"
        "two" -> "2"
        "three" -> "3"
        "four" -> "4"
        "five" -> "5"
        "six" -> "6"
        "seven" -> "7"
        "eight" -> "8"
        "nine" -> "9"
        else -> ""
    }
}