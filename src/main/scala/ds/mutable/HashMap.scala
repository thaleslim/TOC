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

trait HashMap[A <: Comparable[A],B]{
    /** Sets a new default hashCode */
    def apply(newHash: A => Int): Unit
    /** Searches for a value inside this based on its key */
    def apply(key: A): Option[B]
    /** Inserts a value in this, creating a bond to a key */
    def apply(pair: Tuple2[A,B]): Unit
    /** Inserts a Tuple2[A,B] sequence in this */
    def apply(values: Tuple2[A,B]*): Unit
    /** Removes a value from this */
    def - (key: A): Unit
    /** Removes a value sequence from this */
    def - (keys: A*): Unit
}
