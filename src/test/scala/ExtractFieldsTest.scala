package com.github.pjfanning.jackson.scala

import com.github.pjfanning.jackson.scala.JacksonUtil.createJacksonMapper
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class ExtractFieldsTest extends AnyFlatSpec with Matchers {

  "ExtractFields" should "handle unknown json field" in {
    val stringBuilder = new StringBuilder
    (0 until 1000000).foreach {
      stringBuilder.append(7)
    }
    val test1000000 = stringBuilder.toString
    val jacksonMapper = createJacksonMapper()
    val result = jacksonMapper.readValue[ExtractFields](ExtractFields.genJson(test1000000))
  }
}
