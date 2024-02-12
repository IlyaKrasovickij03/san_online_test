package com.san_online_test.domain

interface UseCaseWithParams <R, P> {
    suspend fun execute(params: P) : R
}

interface UseCaseWithoutParams <R> {
    suspend fun execute() : R
}

