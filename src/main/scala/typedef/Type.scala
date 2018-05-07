package typedef

case class Type[T <% Comparable[T]](private val name: String) extends Value[T]{
    private var value: T = _

    private def assign(value: T) = { this.value = value }

    def typeOf(): String = this.name

    def apply(value: T): Unit = this.assign(value)

    def apply(): T = this.value

    def equalsTo(that: T): Boolean = {
        if( this.value.compareTo(that) == 0 )
            return true
        else
            return false
    }
}