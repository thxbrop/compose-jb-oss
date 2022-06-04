package database.dao

interface Dao<T> {
    fun insert(t: T)
    fun delete(t: T)
    fun findById(id: Int): T?
    fun findAll(): List<T>
}