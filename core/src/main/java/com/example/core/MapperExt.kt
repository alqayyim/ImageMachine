package com.example.core

fun <A, B> A.mapTo(mapper: Mapper<A, B>): B {
    return mapper.to(this)
}

