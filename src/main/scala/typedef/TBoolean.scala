package typedef

case class TBoolean() extends Value[Boolean]{
    private var bool: Type[Boolean] = Type[Boolean]("Boolean")

    def typeOf(): String = this.bool.typeOf

    def apply(value: Boolean): Unit = this.bool(value)

    def apply(): Boolean = this.bool.apply()

    def equalsTo(that: Boolean): Boolean = this.bool.equalsTo(that)

}
