import com.google.common.truth.Truth.assertThat
import org.junit.Test

class Day03KtTest {

    @Test
    fun isSymbol() {
        //given
        val symbol = '*'
        //then
        assertThat(symbol.isSymbol()).isTrue()

        //given
        val dot = '.'
        //then
        assertThat(dot.isSymbol()).isFalse()

        //given
        val digit = '3'
        //then
        assertThat(digit.isSymbol()).isFalse()
    }

    @Test
    fun `sum if part number near symbol`() {
        //given
        val input = listOf("467..", "...*.")
        //when
        val actual = sumPartNumberIfNearSymbol(0, "467..", 2, 0, input, 467, 0)
        //then
        val expected = 467
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `ignore if part number far from symbol`() {
        //given
        //when
        val actual = ""
        //then
        val expected = ""
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun symbolBefore() {
        //given
        val inputTrue = "*467.."
        //then
        assertThat(symbolBefore(1, inputTrue)).isTrue()
        //given
        val inputFalse = "467.."
        //then
        assertThat(symbolBefore(1, inputFalse)).isFalse()
    }

    @Test
    fun symbolAfter() {
        //given
        val inputTrue = "467*."
        //then
        assertThat(symbolAfter(2, inputTrue)).isTrue()
        //given
        val inputFalse = "467.."
        //then
        assertThat(symbolAfter(2, inputFalse)).isFalse()
    }

    @Test
    fun symbolAbove() {
        //given
        val inputTrue = listOf("...*.", "467..")
        //then
        assertThat(symbolAbove(1, inputTrue, 0, 2)).isTrue()
        //given
        val inputFalse = listOf(".....", "467..")
        //then
        assertThat(symbolAbove(1, inputFalse, 0, 2)).isFalse()
        //given
        val inputBelow = listOf("467..", "...*.")
        //then
        assertThat(symbolAbove(0, inputBelow, 0, 2)).isFalse()
    }

    @Test
    fun symbolBelow() {
        //given
        val inputBelow = listOf("467..", "...*.")
        //then
        assertThat(symbolBelow(0, inputBelow, 0, 2)).isTrue()
        //given
        val inputFalse = listOf("467..", ".....")
        //then
        assertThat(symbolBelow(0, inputFalse, 0, 2)).isFalse()
        //given
        val inputAbove = listOf("...*.", "467..")
        //then
        assertThat(symbolBelow(1, inputAbove, 0, 2)).isFalse()
    }

    @Test
    fun `number ending line`() {
        //given
        val input = listOf("..467", "...*.")
        //when
        val actual = sumPartNumberIfNearSymbol(2, "..467", 4, 0, input, 467, 0)
        //then
        val expected = 467
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `number end of line`() {
        //given
        val input = listOf("..467", "...*.")
        //when
        val actual = sumPartNumbers(input)
        //then
        val expected = 467
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `part 1`() {
        //given
        val input = readInput("Day03")
        //when
        val actual = sumPartNumbers(input)
        //then
        val expected = 556057
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `part 2`() {
        //given
        val input = readInput("Day03_test")
        //when
        val actual = EngineSchematic(input).sum
        //then
        val expected = 467835
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun findNumbersSameLineTest() {
        //given
        val input1 = listOf("617*......")
        val input2 = listOf("...*617...")
        val input3 = listOf("617*617...")
        //when
        val actual1 = EngineSchematic(input1).findNumbersSameLine(0, 3)
        val actual2 = EngineSchematic(input2).findNumbersSameLine(0, 3)
        val actual3 = EngineSchematic(input3).sum
        //then
        val expected1 = listOf(617)
        val expected2 = listOf(617)
        assertThat(actual1).containsExactlyElementsIn(expected1)
        assertThat(actual2).containsExactlyElementsIn(expected2)
        assertThat(actual3).isEqualTo(617 * 617)
    }

    @Test
    fun findNumbersLineAbove() {
        //given
        val input = listOf("467..114..","...*......")
        //when
        val actual = EngineSchematic(input).findNumbersOtherLine(0, 3)
        //then
        val expected = listOf(467)
        assertThat(actual).containsExactlyElementsIn(expected)
    }

    @Test
    fun findNumbersLineBelow() {
        //given
        val input = listOf("...*......", "..35..633.")
        //when
        val actual = EngineSchematic(input).findNumbersOtherLine(1, 3)
        //then
        val expected = listOf(35)
        assertThat(actual).containsExactlyElementsIn(expected)
    }

    @Test
    fun casesAbove() {
        //given
        val case1 = listOf("467.......","....*.....", "....1.....")
        val case2 = listOf(".467......","....*.....", "....1.....")
        val case3 = listOf("..467.....","....*.....", "....1.....")
        val case4 = listOf("...467....","....*.....", "....1.....")
        val case5 = listOf("....467...","....*.....", "....1.....")
        val case6 = listOf(".....467..","....*.....", "....1.....")
        val case7 = listOf("......467.","....*.....", "....1.....")
        val case8 = listOf(".......467","....*.....", "....1.....")
        //when
        val actual1 = EngineSchematic(case1).sum
        val actual2 = EngineSchematic(case2).sum
        val actual3 = EngineSchematic(case3).sum
        val actual4 = EngineSchematic(case4).sum
        val actual5 = EngineSchematic(case5).sum
        val actual6 = EngineSchematic(case6).sum
        val actual7 = EngineSchematic(case7).sum
        val actual8 = EngineSchematic(case8).sum
        //then
        val expected1 = 0
        val expected2 = 467
        val expected3 = 467
        val expected4 = 467
        val expected5 = 467
        val expected6 = 467
        val expected7 = 0
        val expected8 = 0
        assertThat(actual1).isEqualTo(expected1)
        assertThat(actual2).isEqualTo(expected2)
        assertThat(actual3).isEqualTo(expected3)
        assertThat(actual4).isEqualTo(expected4)
        assertThat(actual5).isEqualTo(expected5)
        assertThat(actual6).isEqualTo(expected6)
        assertThat(actual7).isEqualTo(expected7)
        assertThat(actual8).isEqualTo(expected8)

    }
}