package lib

data class PasswordPolicy(val acceptableAppearances: IntRange, val character: Char) {
    companion object {
        /**
         * fromRule takes a password rule such as "1-3 a" and converts it into
         * a password policy.
         */
        fun fromRule(rule: String): PasswordPolicy {
            val (appearancesPart, characterPart) = rule.split(" ")

            val appearancesParts = appearancesPart.split("-")
            val minAppearances = appearancesParts[0].toInt()
            val maxAppearances = appearancesParts[1].toInt()

            return PasswordPolicy(minAppearances..maxAppearances, characterPart[0])
        }
    }
}

fun countCharactersInPassword(password: String): Map<Char, Int> {
    val countMap = mutableMapOf<Char, Int>()

    for (character in password) {
        val existingCount = countMap.getOrDefault(character, 0)
        countMap[character] = existingCount + 1
    }

    return countMap
}