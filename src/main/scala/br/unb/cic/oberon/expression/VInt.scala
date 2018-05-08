package br.unb.cic.oberon.expression

import br.unb.cic.oberon.typedef.TInt
import br.unb.cic.ds.mutable.HashMap

class VInt(var name: String, var value: Int) extends Expression {
    def interpret[TInt](context: HashMap[String,TInt]): Option[TInt] = context(name)
}