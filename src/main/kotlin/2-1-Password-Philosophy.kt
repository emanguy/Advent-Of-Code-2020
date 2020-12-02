import lib.PasswordPolicy
import lib.countCharactersInPassword
import java.io.File

fun main() {
    val puzzleInputs = File("inputs/official/2-1-password-philosophy.txt").readLines()

    var validPasswords = 0
    for (input in puzzleInputs) {
        val (rulePart, rawPasswordPart) = input.split(":")
        val passwordPart = rawPasswordPart.trim()
        val rule = PasswordPolicy.fromRule(rulePart)

        val charCount = countCharactersInPassword(passwordPart)
        if (charCount.getOrDefault(rule.character, 0) in rule.range) {
            validPasswords++
        }
    }

    println("There are $validPasswords valid passwords.")
}