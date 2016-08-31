package actium.examples.simpleRocket

import actium._
import actium.expressions._
import actium.translations.as2smt.As2SmtTranslator
import actium.translations.as2bmc.As2BmcTranslator
import scala.language.dynamics

abstract class SimpleRocket 
  extends ActionSystem 
  with GherkinSyntax with ExternalEffects 
  with BaseTypes 
  with As2BmcTranslator {

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


  when('PowerOn) given engine === F and mode =!= Destroyed then_do (
    engine := T
  )

  when('PowerOff) given engine === T but mode === Ground then_do (
    engine := F
  )

  when('Start) given engine === T && mode === Ground then_do (
    mode := Air
  )

  when('Land) given mode === Air then_do (
    mode := Ground
  )

  when('Destroy) given mode === Air then_do (
    mode := Destroyed,
    engine := F
  )

  when('Move('dx, 'dy)) given mode === Air && engine === T then_do (
    pos_x := pos_x + 'dx,
    pos_y := pos_y + 'dy
  )
}
