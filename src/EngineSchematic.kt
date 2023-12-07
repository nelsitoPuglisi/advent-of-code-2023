open class EngineSchematic(lines: List<String>) {
    val engineCandidates = mutableMapOf<Int, MutableList<Int>>()
    val partNumbers = mutableMapOf<Int, MutableMap<Int, Int>>()
    var sum = 0

    init {
        lines.forEachIndexed { lineIndex, line ->
            var start = -1
            var end = -1
            var number: Int
            line.forEachIndexed { index, c ->
                if (c == '*') {
                    if (engineCandidates[lineIndex] == null) {
                        engineCandidates[lineIndex] = mutableListOf()
                    }
                    engineCandidates[lineIndex]?.add(index)
                }
                if (c.isDigit()) {
                    if (start == -1)
                        start = index
                    end = index
                }
                if ((index == line.length - 1 || !c.isDigit()) && start != -1) {
                    number = line.substring(start, end + 1).toInt()
                    if (partNumbers[lineIndex] == null) {
                        partNumbers[lineIndex] = mutableMapOf()
                    }
                    partNumbers[lineIndex]?.set(start, number)
                    start = -1
                    end = -1
                }
            }
        }

        engineCandidates.forEach { gear ->
            gear.value.forEach { gearIndex ->
                sumGearRatio(gear.key, gearIndex)
            }
        }
    }

    fun sumGearRatio(lineIndex: Int, gearIndex: Int) {
        //busco part numbers adyacentes en la linea actual
        val partNumbersSameLine = findNumbersSameLine(lineIndex, gearIndex)
        //busco part numbers adyacentes en la linea anterior
        val partNumbersLineBelow = findNumbersOtherLine(lineIndex + 1, gearIndex)
        //busco part numbers adyacentes en la linea siguiente
        val partNumbersLineAbove = findNumbersOtherLine(lineIndex - 1, gearIndex)
        val numbers = mutableListOf<Int>()
        numbers.addAll(partNumbersLineAbove)
        numbers.addAll(partNumbersLineBelow)
        numbers.addAll(partNumbersSameLine?: listOf())

        if (numbers.size == 2) {
            sum += numbers.first() * numbers.last()
        }
    }

    fun findNumbersOtherLine(lineIndex: Int, gearIndex: Int): List<Int> {
        val p = partNumbers.getOrDefault(lineIndex, mutableMapOf())
            .filterNot {
                it.key > gearIndex + 1
            }
            .filterNot {
                it.key + it.value.toString().length - 1 <= gearIndex - 2
            }
            .map { it.value }
        return p
    }

    fun findNumbersSameLine(lineIndex: Int, gearIndex: Int): Collection<Int>? {
        val partNumbersSameLine = partNumbers[lineIndex]?.filter {
            it.key == gearIndex + 1 || it.key + it.value.toString().length == gearIndex
        }?.values
        return partNumbersSameLine
    }
}