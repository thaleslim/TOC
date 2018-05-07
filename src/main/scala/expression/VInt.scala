package expression

import typedef.TInt
import ds.mutable.HashMap

class VInt(var name: String, var value: Int) extends Expression {
    def interpret[TInt](context: HashMap[String,TInt]): Option[TInt] = context(name)
}