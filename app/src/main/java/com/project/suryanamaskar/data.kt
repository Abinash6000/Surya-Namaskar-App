package com.project.suryanamaskar

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
val roundsKey = intPreferencesKey("rounds_count")
val repetitionsKey = intPreferencesKey("repetitions_count")
var roundsCount: Int = 0
var repetitionsCount: Int = 0

var currRounds = roundsCount
var currRepetitions = repetitionsCount

var humans = false