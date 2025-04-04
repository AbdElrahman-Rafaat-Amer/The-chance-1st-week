package ipv4

import sudoku.isValidIPv4

/***
 * Class that has test cases that validate a String is valid IPv4.
 * IPv4 Address Rules:
 *      1- Each segment (octet) range be between 0 and 255.
 *      2- No leading zeros (e.g., "192.168.01.1" is invalid).
 *      3- Must have exactly four octets separated by dots.
 */

fun main() {

    // region ValidIPv4
    checkIPv4(name = "Valid IPv4 Address", input = "0.168.1.255", expected = true)
    // endregion

    // region InvalidIPv4OutOfRange
    checkIPv4(
        name = "Invalid: Octets exceed allowed range (0..255)",
        input = "256.256.256.256",
        expected = false
    )
    // endregion


    // region InvalidIPv4IncorrectFormat
    checkIPv4(
        name = "Invalid: Missing an octet (only three provided)",
        input = "192.168.1",
        expected = false
    )
    checkIPv4(
        name = "Invalid: Extra octet present (five instead of four)",
        input = "192.168.1.1.1",
        expected = false
    )
    checkIPv4(
        name = "Invalid: Contains a non-numeric octet ('a')",
        input = "192.168.1.a",
        expected = false
    )
    checkIPv4(
        name = "Invalid: Contains an India numeric character ('١' - India number)",
        input = "192.168.1.١",
        expected = false
    )
    checkIPv4(
        name = "Invalid: Contains an empty octet",
        input = "192.168..1",
        expected = false
    )
    checkIPv4(
        name = "Invalid: No dots separating octets",
        input = "1921681",
        expected = false
    )
    checkIPv4(
        name = "Invalid: Contains only whitespace characters",
        input = "      ",
        expected = false
    )
    // endregion


    // region InvalidIPv4LeadingZero
    checkIPv4(
        name = "Invalid: Octet contains a leading zero ('01' is not allowed)",
        input = "192.168.01.1",
        expected = false
    ) // Leading zero
    // endregion

}

private fun checkIPv4(name: String, input: String, expected: Boolean) {
    val result = isValidIPv4(input)
    println("Test case: $name, input: $input -> Expected: $expected, Got: $result, ${if (result == expected) "✅ Passed" else "❌ Failed"}")
}