package org.apache.pekko.util

import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.infra.Blackhole

import java.util.concurrent.ThreadLocalRandom

class RNGBenchmark extends common.CommonParams {

  private val xoroshiro12 = new Jep356RandomNumberGenerator("Xoroshiro128PlusPlus")
  private val lxm = new Jep356RandomNumberGenerator("L32X64MixRandom")

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

  @Benchmark
  def lxmBench(blackhole: Blackhole) = {
    blackhole.consume(lxm.nextInt())
  }
}
