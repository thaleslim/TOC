package ds.mutable

import scala.collection.Seq

/**
  * Uma implementação do tipo Hash Map usando
  * alocacao sequencial (um array de elementos).
  *
  * @author thaleslim
  */

case class HashMapElement[A <: Comparable[A]: Manifest, B: Manifest](var key: A, var value: B)(implicit m: Manifest[HashMapElement[A,B]]) extends Comparable[HashMapElement[A,B]]{
    def equalTo (key: A): Boolean = if( key.compareTo(this.key) == 0 ) true else false
    def compareTo(that: HashMapElement[A,B]) = this.key.compareTo(that.key)
}

class HashMap[A <: Comparable[A]: Manifest, B: Manifest](max: Int = 10) extends Map[A,B]{

    private var elements: CustomArray[HashMapElement[A,B]] = new CustomArray[HashMapElement[A,B]](max)
    private var default_hash: A => Int = ( _.hashCode() )

    private def sondagem_quadratica(start: Int): Int = {
        /** Uses a quadratic function to generate a index and checks
          * if inside this elements array at index position has a 
          * empty space.
          * @return free space' index or, if doesn't find any, -1 
          */

        var index = start
        var contador = 1

        while( index < this.elements.length && this.elements.elementAt(index) != None ){
            index = index + 2 * contador + 5 * contador * contador
            contador += 1
        }
        
        if( index < this.elements.length && this.elements.elementAt(index) == None ) index else -1
    }

    private def sondagem_linear(start: Int): Int = {
        /** Runs through every index of this elements array, 
          * searching for a empty space.
          * @return free space' index or, if doesn't find any, -1
          */

        var index = start
        var found: Boolean = false

        var break: Boolean = false
        while (!break && !found){
            index += 1

            if( !(index < this.elements.length) && start != 0 )
                index = 0
            else if( this.elements.elementAt(index) == None )
                found = true
            else if( index == start )
                break = true
        }
        
        if( found ) return index else return -1
    }

    private def get_position(key: A): Int = {
        /** Selects a valid position for inserting a value
          *
          * First Step: attemps using the hash method to find the index,
          * if that fails in pointing to a empty space proceeds;
          *
          * Second Step: attemps to use the index generated in the last
          * step with a quadratic function, that generates some empty
          * gaps between the values, but if that fails as a last resort
          * we proceed to the last step;
          *
          * Third Step: Runs through the entire array searching for
          * a empty space.
          *
          * @return free space' index or, if doesn't find any, -1 
          */

        var index: Int = this.default_hash(key) % this.elements.length

        if( index > -1 && index < this.elements.length ) this.elements.elementAt(index) match {
            case None => return index
            case _ => {}
        }

        var position = sondagem_quadratica(index)

        if( position > -1 )
            return position

        position = sondagem_linear(index)

        if( position > -1 )
            return position
        
        return -1
    }

    private def insert(key: A, value: B): Unit = {
        /** Inserts a new value.
          * @note if this(index) has the same key the value will be overwritten
          * @throws InvalidArgument if this has reached it's storage limit.
          */
        this - (key)
        var index = get_position(key)
        if( index >= 0 )
            this.elements.insert(index, HashMapElement[A,B](key,value))
        else throw ds.mutable.InvalidArgument("Maximum Capacity Reached")
    }

    private def locate(start: Int, key: A): Int = {
        /** Helps this.search to locate a value in this. Usefull in cases where's a collision.
          *
          * @param start Usually the result from the hashing method, it's used as the
          * starting position for the search.
          * @param key The acess key to the value being searched.
          * @return value' index or -1 if doesn't find the corresponding value
          */

        var index = start
        var found: Boolean = false

        var break: Boolean = false
        while( !break && !found ){
            index += 1
            
            if( !(index < this.elements.length) && start != 0 )
                index = 0
            else if( index == start )
                break = true
            else if( this.elements.elementAt(index) != None){
                this.elements.elementAt(index) match {
                    case Some(element) => if( element.equalTo(key) ) found = true 
                    case None => {}
                }
            }
        }

        if( found ) return index else return -1
    }
    
    private def search(key: A): Option[B] = {
        /** Searches for the values based on their respective keys.
          * Interacts with the user through the apply method
          * @return None if doesn't find the corresponding value
          */

        if( this.elements.length == 0 ) None
        var index = this.default_hash(key) % this.elements.length
        if( index > -1 && index < this.elements.length) this.elements.elementAt(index) match {
            case Some(element) =>
                if( element.equalTo(key) )
                    return Some(element.value)
                else
                    this.elements.elementAt(locate(index,key)) match {
                        case Some(elemental) => return Some(elemental.value)
                        case _ => {}
                    }
            case _ => {}
        }

        return None
    }

    def apply(newHash: A => Int): Unit = { this.default_hash = newHash }

    def apply(key: A): Option[B] = this.search(key)

    def apply(pair: Tuple2[A,B]): Unit = this.insert(pair._1, pair._2)

    def apply(pairs: Tuple2[A,B]*): Unit = {
        if(!pairs.isEmpty){
            this.apply(pairs.head)
            this.apply(pairs.tail: _*)
        }
    }

    def - (key: A): Unit = {
        var index = this.default_hash(key) % this.elements.length
        if( this.elements.length > 0 && index > -1 && index < this.elements.length ) this.elements.elementAt(index) match {
            case Some(element) => {
                if( element.equalTo(key) )
                    this.elements - (index)
                else{
                    index = locate(index, key)
                    if( index > -1 )
                        this.elements - (index)
                }
            }
            case None => {}
        }	
    }

    def - (keys: A*): Unit = {
        if(!keys.isEmpty){
            this.-(keys.head)
            this.-(keys.tail: _*)
        }
    }

    def + (pair: Tuple2[A,B]): HashMap[A,B] = { this(pair); this }

    def + (pairs: Tuple2[A,B]*): HashMap[A,B] = {
        if(!pairs.isEmpty){
            this.+(pairs.head)
            this.+(pairs.tail: _*)
        }
        return this
    }

}
