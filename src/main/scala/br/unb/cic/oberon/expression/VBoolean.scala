package br.unb.cic.oberon.expression

import br.unb.cic.oberon.typedef.TBoolean
import br.unb.cic.ds.mutable.HashMap

class VBoolean(var name: String, var value: Boolean) extends Expression {
    def interpret[TBoolean](context: HashMap[String,TBoolean]): Option[TBoolean] = context(name)
}