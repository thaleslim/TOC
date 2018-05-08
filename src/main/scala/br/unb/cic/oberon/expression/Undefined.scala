package br.unb.cic.oberon.expression

import br.unb.cic.oberon.typedef.TBoolean
import br.unb.cic.ds.mutable.HashMap

class Undefined(var name: String) {
    def interpret(context: HashMap[String,TBoolean]): Option[TBoolean] = context(name)
}