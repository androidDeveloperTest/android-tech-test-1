package com.example.data.errors

sealed class Error : Throwable() {
    class HeroNotFound : Error()
}
