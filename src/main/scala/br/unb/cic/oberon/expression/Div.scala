package br.unb.cic.oberon.expression

class Div(var numerador: Expression, var denominador: Expression) extends BinaryExpression(numerador,denominador) {}