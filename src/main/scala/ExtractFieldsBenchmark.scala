package com.github.pjfanning.jackson.scala

import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.infra.Blackhole

import java.nio.charset.StandardCharsets

class ExtractFieldsBenchmark extends CommonParams {
  val (val1000, val1000000) = {
    val stringBuilder = new StringBuilder
    (0 until 1000).foreach(_ => stringBuilder.append(7))
    val v1000 = stringBuilder.toString()
    (1000 until 1000000).foreach(_ => stringBuilder.append(7))
    (v1000, stringBuilder.toString())
  }
  val test1000 = ExtractFields.genJson(val1000).getBytes(StandardCharsets.UTF_8)
  val test1000000 = ExtractFields.genJson(val1000000).getBytes(StandardCharsets.UTF_8)
  val mapper = JacksonUtil.createJacksonMapper()

  @Benchmark
  def deser1000(blackhole: Blackhole) = {
    blackhole.consume(mapper.readValue(test1000, classOf[ExtractFields]))
  }

  @Benchmark
  def deser1000000(blackhole: Blackhole) = {
    blackhole.consume(mapper.readValue(test1000000, classOf[ExtractFields]))
  }
}
