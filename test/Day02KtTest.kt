import com.google.common.truth.Truth.assertThat
import org.junit.Test

class Day02KtTest {
    @Test
    fun `parse game`() {
        //given
        val input = "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green"
        //when
        val actual = Game.parse(input)
        //then
        val expected = Game(
            id = 1, listOf(
            Round(blue = 3, red = 4), Round(red = 1, green = 2, blue = 6), Round(green = 2)
        ))
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `game is possible`() {
        //given
        val bag = Bag(14, 12, 13)
        //when
        val game = Game(
            id = 1, listOf(
                Round(blue = 3, red = 4), Round(red = 1, green = 2, blue = 6), Round(green = 2)
            ))
        //then
        assertThat(isPossible(game, bag)).isTrue()
    }

    @Test
    fun `game is not possible`() {
        //given
        val bag = Bag(14, 12, 13)
        //when
        val game = Game(
            id = 1, listOf(
                Round(blue = 6, red = 20, 8), Round(5, 4, 13), Round(red = 1, green = 5)
            ))
        //then
        assertThat(isPossible(game, bag)).isFalse()
    }

    @Test
    fun `sum possible games`() {
        //given
        val input = readInput("Day02_test")
        val bag = Bag(14, 12, 13)
        //when
        val actual = sumPossible(bag, input.map { Game.parse(it) })
        //then
        val expected = 8
        assertThat(actual).isEqualTo(expected)
    }
}