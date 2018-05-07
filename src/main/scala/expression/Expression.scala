package expression

import typedef.Value
import ds.mutable.HashMap

trait Expression{
    def interpret[T](code: HashMap[String,T]): Option[T]
}