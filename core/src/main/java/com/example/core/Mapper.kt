package com.example.core

interface Mapper<T, E> {

    fun to(t: T): E
}


