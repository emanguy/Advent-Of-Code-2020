package lib

fun getPassportSequence(inputLines: List<String>): Sequence<Map<String, String>> = sequence {
    var kvPairs = mutableMapOf<String, String>()
    for (line in inputLines) {
        if (line.isEmpty()) {
            yield(kvPairs)
            kvPairs = mutableMapOf<String, String>()
            continue
        }

        val keyValuePairs = line.split(" ")
        for (pair in keyValuePairs) {
            val (key, value) = pair.split(":")
            kvPairs[key] = value
        }
    }

    if (kvPairs.isNotEmpty()) {
        yield(kvPairs)
    }
}