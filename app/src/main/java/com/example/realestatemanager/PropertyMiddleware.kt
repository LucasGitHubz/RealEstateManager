package com.example.realestatemanager

import android.util.Log
import com.example.realestatemanager.redux.Action
import com.example.realestatemanager.redux.Middleware
import com.example.realestatemanager.redux.State
import com.example.realestatemanager.redux.Store

class PropertyMiddleware<S: State, A: Action> : Middleware<S, A> {
    override suspend fun process(action: A, currentState: S, store: Store<S, A>) {
        Log.v("PropertyMiddleware", "Processing action: $action; Current state: $currentState")
    }
}