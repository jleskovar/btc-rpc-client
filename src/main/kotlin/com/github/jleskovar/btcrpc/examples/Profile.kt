package com.github.jleskovar.btcrpc.examples

/**
 * Created by james on 25/12/17.
 */

fun profile(repetitions: Int, iterations: Int, block: () -> Unit) {
    for (x in 0 until repetitions) {
        val start = System.nanoTime()
        for (i in 0 until iterations) {
            block()
        }
        val duration = (System.nanoTime() - start) / 1_000_000
        println("${(iterations.toFloat() / duration) * 1000} ops/sec")
    }
}
