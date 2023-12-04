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
    fun `find numbers with index`() {
        //given
        val input = readInput("Day03_test")
        //when
        sumPartNumbers(input).println()
        //then
        assertThat(sumPartNumbers(input)).isEqualTo(4361)
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

}