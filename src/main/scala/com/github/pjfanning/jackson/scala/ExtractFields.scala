package com.github.pjfanning.jackson.scala

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

object ExtractFields {
  def genJson(num: String): String = {
    val stringBuilder = new StringBuilder
    stringBuilder.append("{\"s\":\"s\",\"n\":").append(num).append(",\"i\":1}")
    stringBuilder.toString
  }
}

@JsonIgnoreProperties(ignoreUnknown = true)
case class ExtractFields(s: String, i: Int)
