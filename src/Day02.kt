fun main() {
    fun part1(input: List<String>): Int {
        return sumPossible(Bag(14, 12, 13), input.map { Game.parse(it) })
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 8)

    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}



fun sumPossible(bag: Bag, games: List<Game>) = games.filter { game ->
    isPossible(game, bag)
}.sumOf { it.id }

fun isPossible(game: Game, bag: Bag): Boolean {
    return game.rounds.all { round ->
        val blue = bag.blue - round.blue
        val red = bag.red - round.red
        val green = bag.green - round.green
        if (blue < 0 || red < 0 || green < 0) {
            return false
        }
        true
    }
}