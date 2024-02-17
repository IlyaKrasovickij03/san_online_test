package com.san_online_test.data.storage

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.san_online_test.domain.model.Location
import kotlinx.coroutines.flow.first

private const val LATITUDE_KEY = "latitudeKey"
private const val LONGITUDE_KEY = "longitudeKey"
private const val NETWORK_KEY = "networkKey"

class LocalStorage(
    private val localDataBase: DataStore<Preferences>,
    ) {

    private val lastUserLatitude: Preferences.Key<String> = stringPreferencesKey(LATITUDE_KEY)
    private val lastUserLongitude: Preferences.Key<String> = stringPreferencesKey(LONGITUDE_KEY)
    private val lastDataFromNetwork: Preferences.Key<String> = stringPreferencesKey(NETWORK_KEY)

    suspend fun saveLastUserLocation(location: Location) {
        val latitude = location.latitude.toString()
        val longitude = location.longitude.toString()
        localDataBase.edit {
            it[lastUserLatitude] = latitude
            it[lastUserLongitude] = longitude
        }
    }

    suspend fun saveLastDataFromNetwork(json: String) {
        localDataBase.edit {
            it[lastDataFromNetwork] = json
        }
    }

    suspend fun getLastUserLocation(): Location {
        val lastUserLatitude = localDataBase.data.first()[lastUserLatitude]
        val lastUserLongitude = localDataBase.data.first()[lastUserLongitude]
        return if (lastUserLatitude == null && lastUserLongitude == null) Location(55.7522, 37.6156)
        else Location(latitude = lastUserLatitude!!.toDouble(), longitude = lastUserLongitude!!.toDouble())
    }

    suspend fun getLastDataFromNetwork(): String {
        val lastDataFromNetwork = localDataBase.data.first()[lastDataFromNetwork]
        return lastDataFromNetwork ?: ""
    }

}