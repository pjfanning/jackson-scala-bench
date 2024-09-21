package com.github.pjfanning.jackson.scala

import scala.collection.immutable

object Util {
  /**
   * Turns an [[java.lang.Iterable]] into an immutable Scala sequence (by copying it).
   */
  final def immutableSeq[T](iterable: java.lang.Iterable[T]): immutable.Seq[T] =
    iterable match {
      case imm: immutable.Seq[_] => imm.asInstanceOf[immutable.Seq[T]]
      case other =>
        val i = other.iterator()
        if (i.hasNext) {
          val builder = new immutable.VectorBuilder[T]

          while ({ builder += i.next(); i.hasNext }) ()

          builder.result()
        } else EmptyImmutableSeq
    }

  final case object EmptyImmutableSeq extends immutable.Seq[Nothing] {
    override final def iterator = Iterator.empty
    override final def apply(idx: Int): Nothing = throw new java.lang.IndexOutOfBoundsException(idx.toString)
    override final def length: Int = 0
  }
}
