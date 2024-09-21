package com.github.pjfanning.jackson.scala

import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.infra.Blackhole

import scala.collection.JavaConverters._

class ToListBenchmark extends CommonParams {
  val size = 100
  val entry = "entry"
  val javaList = new java.util.ArrayList[String]()
  (0 until size).foreach(_ => javaList.add(entry))

  @Benchmark
  def toList(blackhole: Blackhole) = {
    blackhole.consume(javaList.asScala.toList)
  }

  @Benchmark
  def toVector(blackhole: Blackhole) = {
    blackhole.consume(javaList.asScala.toVector)
  }

  @Benchmark
  def toSeq(blackhole: Blackhole) = {
    blackhole.consume(javaList.asScala.toList)
  }

  @Benchmark
  def toImmutableSeq(blackhole: Blackhole) = {
    blackhole.consume(Util.immutableSeq(javaList))
  }
}
