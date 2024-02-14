package com.san_online_test.domain.usecases

import com.san_online_test.domain.UseCaseWithoutParams
import com.san_online_test.domain.location.LocationTracker
import com.san_online_test.domain.model.Location
import javax.inject.Inject

class GetCurrentUserLocationUseCase @Inject constructor(
    private val location: LocationTracker
):UseCaseWithoutParams<Location?> {
    override suspend fun execute(): Location? = location.getCurrentLocation()
}