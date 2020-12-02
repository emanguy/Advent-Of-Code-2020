import lib.PasswordPolicy
import java.io.File

fun main() {
    val puzzleInputs = File("inputs/official/2-1-password-philosophy.txt").readLines()

    var validPasswords = 0
    for (input in puzzleInputs) {
        val (rulePart, rawPasswordPart) = input.split(":")
        val passwordPart = rawPasswordPart.trim()
        val rule = PasswordPolicy.fromRule(rulePart)

        val charIndexRange = 1..passwordPart.length
        if (rule.acceptableAppearances.first !in charIndexRange || rule.acceptableAppearances.last !in charIndexRange) continue
        if ((passwordPart[rule.acceptableAppearances.first-1] == rule.character) xor (passwordPart[rule.acceptableAppearances.last-1] == rule.character)) {
            validPasswords++
        }
    }

    println("There are $validPasswords valid passwords.")
}