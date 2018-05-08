package br.unb.cic.oberon.expression

import br.unb.cic.oberon.typedef.Value
import br.unb.cic.ds.mutable.HashMap

trait Expression{
    def interpret[T](code: HashMap[String,T]): Option[T]
}