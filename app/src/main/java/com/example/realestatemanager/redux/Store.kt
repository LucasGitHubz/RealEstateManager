package com.example.realestatemanager.redux

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class Store<S: State, A: Action>(
    initialState: S,
    private val reducer: Reducer<S, A>,
    private val middlewares: List<Middleware<S, A>> = emptyList(),
) {
    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<S> = _state

    suspend fun dispatch(action: A) {
        val currentState = _state.value

        middlewares.forEach { middleware ->
            middleware.process(action, currentState, this)
        }

        val newState = reducer.reduce(currentState, action)
        _state.value = newState
    }
}