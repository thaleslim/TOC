package br.unb.cic.oberon.typedef

trait Value[T] {
    
    def typeOf(): String

    def apply(value: T): Unit

    def apply(): T

    def equalsTo(that: T): Boolean
}