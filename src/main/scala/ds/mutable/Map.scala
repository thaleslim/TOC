package ds.mutable

/**
  * Hash map é uma estrutura de dados
  * que associa chaves de pesquisa a valores
  * Seu objetivo é,a partir de uma chave simples,
  * fazer uma busca rápida e obter o valor desejado
  * Uma especifição do tipo Hash Map
  * usando a construção trait da linguagem 
  * Scala. 
  * 
  * @author thaleslim
  */

trait Map[A <: Comparable[A],B]{
    /** Sets a new default hashCode */
    def apply(newHash: A => Int): Unit
    /** Searches for a value inside this based on its key */
    def apply(key: A): Option[B]
    /** Inserts a value in this, creating a bond to a key */
    def apply(pair: Tuple2[A,B]): Unit
    /** Inserts a Tuple2[A,B] sequence in this */
    def apply(pairs: Tuple2[A,B]*): Unit
    /** Removes a value from this */
    def -    (key: A): Unit
    /** Removes a value sequence from this */
    def -    (keys: A*): Unit
    /** Adds a value in this */
    def +    (pair: Tuple2[A,B]): HashMap[A,B]
    /** Adds a value sequence in this */
    def +    (pairs: Tuple2[A,B]*): HashMap[A,B]
    /* Removes all the mapping from this */ 
    //def clear(): Unit
    /* Evaluates if this is Empty */
    //def isEmpty(): Boolean
    /* Checks if the key exists in this */
    //def containsKey(key: A): Boolean
    /* Returns a Tuple list with all this' mappings */
    //def entryList(): List[Tuple2[A,B]]
    /* Returns this key's list */
    //def keyList(): List[A]
    /* Inserts all mappings from that */
    //def addAll(that: HashMap[A,B]): HashMap[A,B]
    //def +    (that: HashMap[A,B]): HashMap[A,B]
}