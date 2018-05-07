package expression

import typedef.TBoolean
import ds.mutable.HashMap

class Undefined(var name: String) {
    def interpret(context: HashMap[String,TBoolean]): Option[TBoolean] = context(name)
}