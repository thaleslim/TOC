package oberon.expression

abstract class BinExpression(val lhs: Expression, val rhs: Expression) extends Expression{}

class AddExpression(lhs: Expression, rhs: Expression) extends BinExpression(lhs,rhs){

    override def eval() : Value = {
        val v1 : IntValue = lhs.eval().asInstanceOf[IntValue]
        val v2 : IntValue = rhs.eval().asInstanceOf[IntValue]

        return new IntValue(v1.value + v2.value)
    }
}