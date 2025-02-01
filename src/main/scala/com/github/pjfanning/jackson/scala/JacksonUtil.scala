package com.github.pjfanning.jackson.scala

import scala.collection.immutable.BitSet
import scala.collection.mutable

import com.fasterxml.jackson.annotation.JsonInclude.Include
import com.fasterxml.jackson.core.{JsonFactory, JsonFactoryBuilder, StreamReadFeature, StreamWriteFeature}
import com.fasterxml.jackson.core.json.JsonWriteFeature
import com.fasterxml.jackson.core.util.{DefaultIndenter, DefaultPrettyPrinter}
import com.fasterxml.jackson.databind.{DeserializationFeature, ObjectMapper, SerializationFeature}
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.module.scala.deser.{ImmutableBitSetDeserializer, MutableBitSetDeserializer}
import com.fasterxml.jackson.module.scala.{BitSetDeserializerModule, ClassTagExtensions, DefaultScalaModule}

object JacksonUtil {

  def createJacksonMapper(escapeNonAscii: Boolean = false,
                          indentOutput: Boolean = false): ObjectMapper with ClassTagExtensions = {
    val jsonFactory: JsonFactory = new JsonFactoryBuilder()
      .configure(JsonFactory.Feature.INTERN_FIELD_NAMES, false)
      .configure(JsonWriteFeature.ESCAPE_NON_ASCII, escapeNonAscii)
      .configure(StreamReadFeature.USE_FAST_DOUBLE_PARSER, true)
      .configure(StreamWriteFeature.USE_FAST_DOUBLE_WRITER, true)
      .build()
    new ObjectMapper(jsonFactory) with ClassTagExtensions {
      registerModule(DefaultScalaModule)
      registerModule(BitSetDeserializerModule)
      registerModule(new SimpleModule()
        .addDeserializer(classOf[BitSet], ImmutableBitSetDeserializer)
        .addDeserializer(classOf[mutable.BitSet], MutableBitSetDeserializer)
      )
    }.configure(SerializationFeature.INDENT_OUTPUT, indentOutput)
      .configure(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE, false)
      .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
      .configure(DeserializationFeature.FAIL_ON_NULL_CREATOR_PROPERTIES, true)
      .configure(SerializationFeature.WRITE_CHAR_ARRAYS_AS_JSON_ARRAYS, true)
      .configure(SerializationFeature.WRITE_DURATIONS_AS_TIMESTAMPS, false)
      .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
      .configure(SerializationFeature.WRITE_DATES_WITH_ZONE_ID, true)
      .setSerializationInclusion(Include.NON_EMPTY)
      .setDefaultPrettyPrinter {
        val indenter = new DefaultIndenter("  ", "\n")
        new DefaultPrettyPrinter().withObjectIndenter(indenter).withArrayIndenter(indenter)
      }.asInstanceOf[ObjectMapper with ClassTagExtensions]
  }
}
