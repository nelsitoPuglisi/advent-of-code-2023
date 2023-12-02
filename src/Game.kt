data class Game(val id: Int, val rounds: List<Round>) {
    companion object {
        fun parse(input: String): Game {
            val id = input.substringAfter("Game ").substringBefore(":").toInt()
            val rounds = input.substringAfter(": ").split("; ").map { round ->
                var blue = 0
                var red = 0
                var green = 0
                round.split(",").map { cube ->
                    val amount = cube.trim().split(" ")[0]
                    val color = cube.trim().split(" ")[1]
                    when (color) {
                        "blue" -> blue = amount.toIntOrNull() ?: 0
                        "red" -> red = amount.toIntOrNull() ?: 0
                        "green" -> green = amount.toIntOrNull() ?: 0
                    }
                }
                Round(blue, red, green)
            }
            return Game(id, rounds)
        }
    }
}

data class Round(val blue: Int = 0, val red: Int = 0, val green: Int = 0)

data class Bag(val blue: Int = 0, val red: Int = 0, val green: Int = 0)
