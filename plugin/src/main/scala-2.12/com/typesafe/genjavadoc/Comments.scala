package com.typesafe.genjavadoc
import scala.reflect.internal.util.Position

trait Comments extends BaseComments { this: TransformCake =>
  override def parseComments(): Unit =
    new parser.ScaladocUnitParser(unit, Nil) {
      override def newScanner = new parser.ScaladocUnitScanner(unit, Nil) {
        override def registerDocComment(str: String, pos: Position) = {
          super.registerDocComment(str, pos)
          comments += pos -> Comment(pos, str)
        }
      }
    }.parse()
}
