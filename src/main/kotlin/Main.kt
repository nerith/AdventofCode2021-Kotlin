import java.io.File

fun main(args: Array<String>) {
    //questionOne()
    //questionTwo()
    //questionThree()
    dayThree()
}

fun questionOne() {
    var previousMeasurement = 0;
    var index = 0;
    var increasedDepthCount = 0;

    val measurements = File("inputs/q1.txt").readLines().map { it.toInt() }
    measurements.forEach {
        if (index++ > 0) {
            println(it)
            if (previousMeasurement < it) {
                println("$it an INCREASE from $previousMeasurement")
                increasedDepthCount++;
            }
        }

        previousMeasurement = it
    }

    println(increasedDepthCount)
}

fun questionTwo() {
    val measurements = File("inputs/q1.txt").readLines().map { it.toInt() }
    var previousSum = 0;
    var depthIncreaseCount = 0;
    
    measurements.windowed(3, 1).drop(1).forEach {
        val sum = it.sum()

        if (sum > previousSum) {
            depthIncreaseCount++
            println(sum)
        }

        previousSum = sum;
    }

    println(depthIncreaseCount)
}

fun questionThree2() {
    val measurements = File("inputs/q2.txt").readLines()
    var xPosition = 0
    var depth = 0
    var aim = 0

    measurements.forEach {
        val inputs = it.split(' ')
        val direction = inputs[0]
        val amount = inputs[1].toInt()

        when (direction) {
            "forward" -> {
                xPosition += amount
                depth += (aim * amount)
            }
            "down" -> aim += amount
            "up" -> aim -= amount

        }
    }

    println("Horizontal position: $xPosition")
    println("Depth: $depth")
    println("Answer: ${xPosition * depth}")
}

fun dayThree() {
    val diagnostics = File("inputs/q3.txt").readLines()
    var gammaRate = "";

    var bits = IntArray(diagnostics.first().length)

    var row = 0
    diagnostics.forEach {
      var index = 0
        it.toCharArray().forEach {
            bits[index] += Character.getNumericValue(it)
            index++
        }
    }

    for (bit in bits) {
        var mostCommonBit = 1
        if (bit < diagnostics.count() - bit) {
            mostCommonBit = 0;
        }

        gammaRate += mostCommonBit.toString()
    }

    val epsilonRate = gammaRate.map { if (it == '1') '0' else '1'}.joinToString("")
    println("Gamma Rate: $gammaRate [${gammaRate.toInt(2)}]")
    println("Epsilon Rate: $epsilonRate [${epsilonRate.toInt(2)}]")


    val powerConsumption = gammaRate.toInt(2) * epsilonRate.toInt(2);
    println("POWER CONSUMPTION: $powerConsumption")
}