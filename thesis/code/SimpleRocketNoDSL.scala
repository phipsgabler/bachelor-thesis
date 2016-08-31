package actium.examples.simpleRocket

import actium._
import actium.expressions._

abstract class SimpleRocketNoDSL
  extends ActionSystem
  with BaseTypes {

  type State = Int

  val F = 0
  val T = 1
  val Air = 0
  val Ground = 1
  val Destroyed = 2

  val engine = Variable('engine)
  val mode = Variable('mode)
  val pos_x = Variable('pos_x)
  val pos_y = Variable('pos_y)

  initialize (
    Assignment(engine, Constant(F)),
    Assignment(mode, Constant(Ground)),
    Assignment(pos_x, Constant(0)),
    Assignment(pos_y, Constant(0))
  )


  addAction(Action('PowerOn,
    And(Predicate2("==", engine, Constant(F)), Predicate2("!=", mode, Constant(Destroyed))),
    Seq(Assignment(engine, Constant(T))))
  )

  addAction(Action('PowerOff,
    And(Predicate2("==", engine, Constant(T)), Predicate2("==", mode, Constant(Ground))),
    Seq(Assignment(engine, Constant(F))))
  )

  addAction(Action('Start,
    And(Predicate2("==", engine, Constant(T)), Predicate2("==", mode, Constant(Ground))),
    Seq(Assignment(mode, Constant(Air))))
  )

  addAction(Action('Land,
    Predicate2("==", mode, Constant(Air)),
    Seq(Assignment(mode, Constant(Ground))))
  )

  addAction(Action('Destroy,
    Predicate2("==", mode, Constant(Air)),
    Seq(Assignment(engine, Constant(F)), Assignment(mode, Constant(Destroyed))))
  )

  addAction(Action('Move,
    And(Predicate2("==", engine, Constant(T)), Predicate2("==", mode, Constant(Air))),
    Seq(Assignment(pos_x, Application("+", pos_x, Variable('dx))),
      Assignment(pos_y, Application("+", pos_y, Variable('dy)))),
    Seq(Variable('dx), Variable('dy)))
  )
}
