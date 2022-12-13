package com.github.pjfanning.jackson.scala

object ExtractFields {
  def genJson(num: String): String = {
    val stringBuilder = new StringBuilder
    stringBuilder.append("{\"s\":\"s\",\"n\":").append(num).append(",\"i\":1}")
    stringBuilder.toString
  }
}

case class ExtractFields(s: String, i: Int)
