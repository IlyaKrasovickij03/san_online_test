package com.san_online_test.domain.location

import com.san_online_test.domain.model.Location

interface LocationTracker {
    suspend fun getCurrentLocation(): Location?
}