fun main() {


}

/**
 * Function to validate if the given string isValid IPv4 Address
 */
fun isValidIPv4(iPv4: String): Boolean {
    val allowedRange = 0..255
    val octets = iPv4.split(".")

    if (octets.size != 4) return false

    octets.forEach {
        val number = it.toIntOrNull() ?: return false
        val isLeadingZero = it.length > 1 && it[0] == '0'
        val isNumberInRange = number in allowedRange
        val isDigits = isArabicDigitsOnly(it) && !isLeadingZero && isNumberInRange
        if (!isDigits) {
            return false
        }
    }
    return true
}


/**
 * Function to check only if the string is Arabic digits
 */
fun isArabicDigitsOnly(str: String): Boolean {
    return str.all { it in '0'..'9' }
}