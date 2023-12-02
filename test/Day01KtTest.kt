import com.google.common.truth.Truth.assertThat
import org.junit.Test

class Day01KtTest {

    @Test
    fun spelled() {
        assertThat(spelled("one")).isEqualTo("1")
    }

    @Test
    fun `find spelled number in string`() {
        //given
        val twoNumbers = "twone"
        //when
        val actual = findSpelled(twoNumbers)
        //then
        val expected = listOf(Pair(2, "1"), Pair(0, "2"))
        assertThat(actual).containsExactlyElementsIn(expected)
    }

    @Test
    fun `find spelled numbers in eightwothree`() {
        //given
        val eightwothree = "eightwothree"
        //when
        val actual = findSpelled(eightwothree)
        //then
        val expected = listOf(Pair(4, "2"), Pair(7, "3"), Pair(0, "8"))
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `no spelled number`() {
        //given
        val noSpelledNumber = "45mjmblfqjvf9"
        //when
        val actual = findSpelled(noSpelledNumber)
        //then
        assertThat(actual).isEmpty()
    }

    @Test
    fun `no text number`() {
        //given
        val noSpelledNumber = "931"
        //when
        val actualFirst = firstDigit(noSpelledNumber)
        val actualLast = lastDigit(noSpelledNumber)
        //then
        assertThat(actualFirst).isEqualTo("9")
        assertThat(actualLast).isEqualTo("1")
    }

    @Test
    fun `find first digit`() {
        //given
        val listOfNumbersToNine = listOf(
            "two1nine",
            "eightwothree",
            "abcone2threexyz",
            "xtwone3four",
            "4nineeightseven2",
            "zoneight234",
            "7pqrstsixteen",
            "hmbfjdfnp989mfivefiverpzrjs",
            "45mjmblfqjvf9"
        )
        //when
        val actual = listOfNumbersToNine.map { firstDigit(it) }
        //then
        assertThat(actual).containsExactly("2", "8", "1", "2", "4", "1", "7", "9", "4")
    }

    @Test
    fun `find last digit`() {
        //given
        val listOfNumbersToNine = listOf(
            "two1nine",
            "eightwothree",
            "abcone2threexyz",
            "xtwone3four",
            "4nineeightseven2",
            "zoneight234",
            "7pqrstsixteen",
            "hmbfjdfnp989mfivefiverpzrjs",
            "45mjmblfqjvf9"
        )
        //when
        val actual = listOfNumbersToNine.map { lastDigit(it) }
        //then
        assertThat(actual).containsExactly("9", "3", "3", "4", "2", "4", "6", "5", "9")
    }
}