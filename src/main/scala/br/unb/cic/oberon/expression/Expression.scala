package br.unb.cic.oberon.expression

/** TODO: Until 15/05:
  * Implement::
  * Expressions:::
  *     (-,*,/);
  *     (AND, OR, NOT);
  *     (>,<,>=,<=,!=).
  * Comands::: 
  *     While;
  *     Assignment;
  *     Variable Declaration;
  *     If Then;
  *     If Then Else; 
  *     For.
  */
trait Expression{
    def eval(): Value
}

trait Value extends Expression{
    def eval() = this
}

case class IntValue(value: Integer) extends Value
case class BoolValue(value: Boolean) extends Value
case class UndefValue() extends Value
