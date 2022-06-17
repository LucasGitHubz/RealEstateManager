package com.example.realestatemanager.redux

interface Reducer<S: State, A: Action> {
    fun reduce(currentState: S, action: A): S
}