package actium.examples.simpleRocket

import actium._
import actium.expressions._
import scala.language.dynamics


abstract class ExtendedSimpleRocket
  extends ActionSystem
  with GherkinSyntax with ExternalEffects
  with BaseTypes {

  type State = Int

  val F = 0
  val T = 1
  val Air = 0
  val Ground = 1
  val Destroyed = 2

  val engine = statevar('engine)
  val mode = statevar('mode)
  val pos_x = statevar('pos_x)
  val pos_y = statevar('pos_y)


  initialize (
    engine := F,
    mode := Ground,
    pos_x := 0,
    pos_y := 0
  )


  var cnt = 0

  when('PowerOn) given engine === F and mode =!= Destroyed then_do (
    engine := T
  )

  when('PowerOff) given engine === T but mode === Ground then_do (
    engine := F
  )

  when('Start) given engine === T && mode === Ground then_do (
    mode := Air
  )

  // `given' and `when' can be swapped
  given (mode === Air) when 'Land then_do (
    mode := Ground
  )

  // external statement, closing over local variable
  when('Destroy) given mode === Air then_do (
    mode := Destroyed,
    engine := F,

    externally(println(s"E: BOOM * $cnt")),
    externally(cnt += 1)
  )

  // multiple actions with the same name
  when('Destroy) given mode === Ground then_do (
    mode := Destroyed,
    engine := F,

    externally(println("How could that happen?"))
  )

  // external statement accessing the environment of the action 
  // at the time of evaluation
  when('Position('x, 'y)) given mode === Air && engine === T then_do (
    pos_x := 'x,
    pos_y := 'y,

    // thinkable alternative syntaxes:
    // - externally { implicit env => ...} (explicitly passing the 
    //   environment by an implicit lambda)
    // - externally('x, 'y) { ... } (like C++ lambda capture)
    externally {
      println(s"E: x = ${'x.value}, y = ${'y.value}, pos_x = ${'pos_x.value}")
      if ('x.value < 0 || 'y.value < 0) {
        println("Aborting: x < 0 || y < 0.")
        abort
      }
    }
  )

  when('Move('dx, 'dy)) given mode === Air && engine === T then_do (
    pos_x := pos_x + 'dx,
    pos_y := pos_y + 'dy
  )
}
