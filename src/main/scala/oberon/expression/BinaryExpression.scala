package oberon.expression

abstract class BinExpression(val lhs: Expression, val rhs: Expression) extends Expression{}

class AddExpression(lhs: Expression, rhs: Expression) extends BinExpression(lhs,rhs){}