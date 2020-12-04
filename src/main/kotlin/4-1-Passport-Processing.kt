import lib.getPasswordSequence
import java.io.File

fun main() {
    val inputLines = File("inputs/official/4-1-passport-processing.txt").readLines()

    val requiredFields = listOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")
    var validPassports = 0
    for (passportKeysAndValues in getPasswordSequence(inputLines)) {
        if (passportKeysAndValues.keys.containsAll(requiredFields)) validPassports++
    }

    println("There are $validPassports valid passports.")
}