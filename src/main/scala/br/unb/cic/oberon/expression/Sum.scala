package br.unb.cic.oberon.expression

class Sum(var left: Expression, var right: Expression) extends BinaryExpression(left,right) {}