package br.unb.cic.oberon.expression

class VUndefined(var name: String) extends Expression {
    def eval(): Value = UndefValue()
}

class VInt(var value: Integer) extends Expression {
    def eval(): Value = IntValue(value)
}

class VBoolean(var value: Boolean) extends Expression {
    def eval(): Value = BoolValue(value)
}