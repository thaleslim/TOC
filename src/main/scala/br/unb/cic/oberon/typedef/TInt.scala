package br.unb.cic.oberon.typedef

case class TInt() extends Value[Int]{
    private var integer: Type[Int] = Type[Int]("Int")

    def typeOf(): String = this.integer.typeOf

    def apply(value: Int): Unit = this.integer(value)

    def apply(): Int = this.integer.apply()

    def equalsTo(that: Int): Boolean = this.integer.equalsTo(that)

}