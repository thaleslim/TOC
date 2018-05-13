package ds.mutable

import ds.design.Traversable

/**
  * A List is a linear container like data structure divided in elements.
  *
    This has a certain amount of storage space and holds a
  * set of elements, being able to track their
  * positions through a index.
  * 
  * @author rbonifacio / thaleslim
  */

trait List[T] extends Traversable[T]{
    /**
      * Inserts a value in this
      * @param index value's position within this
      * @example {{{
      *      var list: List[Int]
      *      list.insert(0,5)    //list = [5]
      * }}}
      */
    def insert        (index: Int, value: T) : Unit
    /**
      * Removes a value from a position
      * @param index value's position within this
      * @example {{{
      *      var list: List[Int]
      *      list(1,2,3,4,5)     //list = [1,2,3,4,5]
      *      list.remove(2)      //list = [1,2,4,5]
      * }}}
      */       
    def remove        (index: Int)           : Unit
    /**
      * Based on a index, returns a value from within this
      * @param index value's position within this
      * @example {{{
      *      var list: List[Int]
      *      list(1,2,3,4,5)     //list = [1,2,3,4,5]
      *      list.elementAt(2)   //== Some(3)
      *      list.elementAt(7)   //== None
      * }}}
      * @return Some(value) or None, if value doesn't exist within this
      */
    def elementAt     (index: Int)           : Option[T]
    /**
      * Searchs for a value and returns its position within this
      * @example {{{
      *      var list: List[Int]
      *      list(1,2,3,4,5)     //list = [1,2,3,4,5]
      *      list.find(2)        //== Some(1)
      *      list.find(88)       //== None
      * }}}
      * @return Some(index) or None, if value doesn't exist within this 
      */
    def find          (value: T)           : Option[Int]
    /**
      * Concatenates this & that, without modifying that
      * @example {{{
      *      var list: List[Int]; var that: List[Int]
      *      list(1,2);that(3,4) //list = [1,2]     && that = [3,4]
      *      list.addAll(that)   //list = [1,2,3,4] && that = [3,4]
      * }}}
      */
    def addAll[B <: T](that: List[B])      : List[T]
    /**
      * Removes all of this' elements
      * @example {{{
      *      var list: List[Int]
      *      list(1,2,3,4,5)     //list = [1,2,3,4]
      *      list.clear          //list = []
      * }}}
      */
    def clear(): Unit
    /**
      * Substitutes this' elements for a copy of that' elements
      * @example {{{
      *      var list: List[Int]; var listAux: List[Int]
      *      list(1,2,3)         //list = [1,2,3]
      *      listAux(10,20,30)   //listAux = [10,20,30]
      *      list.subst(listAux) //list = [10,20,30] 
      * }}}
      */
    def subst (that: List[T]) : Unit = { 
        this.clear
        this.addAll(that)
    }
    /**
      * Extracts from a tuple the args to insert a value
      * @param tuple will be used as in this.insert(tuple._1,tuple._2)
      * @example {{{
      *      var list: List[Int]
      *      list(0 -> 2)        //== list((0,2)) = [2]
      * }}}
      * @see [[List!.insert(index:Int,value:T):Unit* this.insert(Int,T)]]
      */
    def apply (tuple: Tuple2[Int,T]) = this.insert(tuple._1,tuple._2)
    /** 
      * Adds a tuple2[Int,T] to this
      * @example {{{
      *      var list: List[Int]
      *      list + (0 -> 2)     //== list + ((0,2)) = [2]
      * }}}
      * @see [[List!.apply(tuple:(Int,T)):Unit* this( (Int,T) )]]
      */
    def +     (tuple: Tuple2[Int,T]) = this(tuple)
    /**
      * Adds a tuple2[Int,T] sequence to this
      * @example {{{
      *      var list: List[Int]
      *      list + (0->2, 1->3, 2->4)
      *      //== list + ((0,2),(1,3),(2,4)) = [2,3,4]
      * }}}
      * @see [[List!.+(tuple:(Int,T))* this( (Int,T) )]] [[https://www.scala-lang.org/api/2.12.3/scala/collection/Seq.html]]
      */
    def +     (that: Tuple2[Int,T]*): Unit =
        if( !that.isEmpty ) {
            this(that.head)
            this + (that.tail: _*)
        }
    /**
      * Adds a value at this' tail
      * @example {{{
      *      var list: List[Int]
      *      list(1,2,3)         //list = [1,2,3]
      *      list + 4            //list = [1,2,3,4]
      * }}}
      * @see [[List!.insert(index:Int,value:T):Unit* this.insert(Int,T)]]
      */
    def +     (value: T) = this((this.size,value))
    /**
      * Inserts a value sequence in this
      * @example {{{
      *      var list: List[Int]
      *      list(1,2,3)         //list = [1,2,3]
      * }}}
      * @see [[List!.+(value:T)* this + (T)]] [[https://www.scala-lang.org/api/2.12.3/scala/collection/Seq.html]]
      */
    def apply (that: T*): Unit = {
        if( !that.isEmpty ) { this + (that.head); this(that.tail: _*) }
    }
    /**
      * Removes a value from this
      * @param index value's position within this
      * @example {{{
      *      var list: List[Int]
      *      list(10,20,30)         //list = [10,20,30]
      *      list - 1               //list = [10,30]
      * }}}
      * @see [[List!.remove(index:Int)* this.remove(Int)]]
      */
    def -     (index: Int): Unit = this.remove(index)
    /**
      * Removes a value sequence from this
      * @note It will search for the values and only remove the ones that exist within this
      * @example {{{
      *      var list: List[Int]
      *      list(1,2,3,4,5)     //list = [1,2,3,4,5]
      *      list - (1,2,5)      //list = [3,4]
      * 
      * }}}
      * @see [[List!.-(index:Int)* this - (Int)]] [[https://www.scala-lang.org/api/2.12.3/scala/collection/Seq.html]]
      */
    def -     (that: T*): Unit = {
        if( !that.isEmpty ){ 
            this.find(that.head) match { 
                case Some(index) => this - (index)
                case None => {}
            }
            this - (that.tail: _*)
        }
    }
}
