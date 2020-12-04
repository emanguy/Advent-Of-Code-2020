import lib.getPassportSequence
import java.io.File

fun main() {
    val inputLines = File("inputs/official/4-1-passport-processing.txt").readLines()

    var validPassports = 0

    val requiredFields = listOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")
    val hexRegex = """^#[a-f0-9]{6}$""".toRegex()
    val passportIDRegex = """^\d{9}$""".toRegex()
    val validEyeColors = setOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth")

    for (passportKeysAndValues in getPassportSequence(inputLines)) {
        if (!passportKeysAndValues.keys.containsAll(requiredFields)) continue

        var fieldsValid = true
        for ((passportKey, passportValue) in passportKeysAndValues) {
            fieldsValid = fieldsValid && when (passportKey) {
                "byr" -> passportValue.length == 4 && passportValue.toInt() in 1920..2002
                "iyr" -> passportValue.length == 4 && passportValue.toInt() in 2010..2020
                "eyr" -> passportValue.length == 4 && passportValue.toInt() in 2020..2030
                "hgt" -> {
                    // Assume the last 2 characters are the unit of measurement and split it that way
                    val measure = passportValue.substring(0, passportValue.length-2)
                    val unit = passportValue.substring(passportValue.length-2)

                    when (unit) {
                        "cm" -> measure.toInt() in 150..193
                        "in" -> measure.toInt() in 59..76
                        else -> false
                    }
                }
                "hcl" -> hexRegex.matches(passportValue)
                "ecl" -> passportValue in validEyeColors
                "pid" -> passportIDRegex.matches(passportValue)
                "cid" -> true
                else -> false
            }
        }
        if (fieldsValid) validPassports++
    }

    println("There are $validPassports valid passports.")
}