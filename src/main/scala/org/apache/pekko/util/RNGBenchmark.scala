package org.apache.pekko.util

import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.infra.Blackhole

import java.util.concurrent.ThreadLocalRandom

class RNGBenchmark extends common.CommonParams {

  private val xoroshiro12 = new Jep356RandomNumberGenerator("Xoroshiro128PlusPlus")

  @Benchmark
  def threadLocal(blackhole: Blackhole) = {
    blackhole.consume(ThreadLocalRandom.current().nextInt())
  }

  @Benchmark
  def rngWrapper(blackhole: Blackhole) = {
    blackhole.consume(RandomNumberGenerator.get().nextInt())
  }

  @Benchmark
  def xoroshiro12Bench(blackhole: Blackhole) = {
    blackhole.consume(xoroshiro12.nextInt())
  }
}
