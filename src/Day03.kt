fun main() {

    fun part1(input: List<String>): Int {
        return sumPartNumbers(input)
    }

    fun part2(input: List<String>): Int {
        return EngineSchematic(input).sum
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part2(testInput) == 467835)

    val input = readInput("Day03")
    part1(input).println()
    part2(input).println()
}

fun Char.isSymbol() : Boolean {
    return !this.isDigit() && this != '.'
}

fun sumPartNumbers(input: List<String>): Int {
    var start = -1
    var end = -1
    var number: Int
    var sum = 0
    input.forEachIndexed { lineIndex, line ->
        line.forEachIndexed { index, c ->
            try {
                if (c.isDigit()) {
                    if (start == -1)
                        start = index
                    end = index
                }

                if ((index == line.length - 1 || !c.isDigit()) && start != -1) {
                    number = line.substring(start, end + 1).toInt()
                    sum = sumPartNumberIfNearSymbol(start, line, end, lineIndex, input, number, sum)
                    start = -1
                    end = -1
                }
            } catch (e: Exception) {
                println("sumPartNumbers $lineIndex $start $end $line")
                println(e)
                return -1
            }
        }
    }
    return sum
}

fun sumPartNumberIfNearSymbol(
    start: Int,
    line: String,
    end: Int,
    lineIndex: Int,
    input: List<String>,
    number: Int,
    sum: Int
) : Int {
    if (symbolBefore(start, line) ||
        symbolAfter(end, line) ||
        symbolAbove(lineIndex, input, start, end) ||
        symbolBelow(lineIndex, input, start, end)
    ) {
        return sum + number
    }
    return sum
}

fun symbolBelow(
    lineIndex: Int,
    input: List<String>,
    partNumberStartIndex: Int,
    partNumberEndIndex: Int
) : Boolean {
    if (lineIndex < input.size - 1) {
        val lineBelow = input[lineIndex + 1]
        val start = maxOf(0, partNumberStartIndex - 1)
        val end = minOf(lineBelow.length - 1, partNumberEndIndex + 1)
        return lineBelow.substring(start, end + 1).any { it.isSymbol() }
    }
    return false
}

fun symbolAbove(
    lineIndex: Int,
    input: List<String>,
    partNumberStartIndex: Int,
    partNumberEndIndex: Int
) : Boolean {
    if (lineIndex > 0) {
        val lineAbove = input[lineIndex - 1]
        val start = maxOf(0, partNumberStartIndex - 1)
        val end = minOf(lineAbove.length - 1, partNumberEndIndex + 1)
        return lineAbove.substring(start, end + 1).any { it.isSymbol() }
    }
    return false
}

fun symbolAfter(end: Int, line: String) = (end < line.length - 1 && line[end + 1].isSymbol())

fun symbolBefore(start: Int, line: String) = (start > 0 && line[start - 1].isSymbol())

