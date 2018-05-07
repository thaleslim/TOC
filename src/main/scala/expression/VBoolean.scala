package expression

import typedef.TBoolean
import ds.mutable.HashMap

class VBoolean(var name: String, var value: Boolean) extends Expression {
    def interpret[TBoolean](context: HashMap[String,TBoolean]): Option[TBoolean] = context(name)
}