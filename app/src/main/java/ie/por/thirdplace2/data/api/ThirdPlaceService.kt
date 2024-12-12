package ie.por.thirdplace2.data.api

import ie.por.thirdplace2.data.ThirdPlaceModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ThirdPlaceService {
    @GET(ServiceEndPoints.THIRDPLACES_ENDPOINT)
    suspend fun getall(): Response<List<ThirdPlaceModel>>

    @GET(ServiceEndPoints.THIRDPLACES_ENDPOINT + "/{id}")
    suspend fun get(@Path("id") id: String): Response<List<ThirdPlaceModel>>

    @DELETE(ServiceEndPoints.THIRDPLACES_ENDPOINT + "/{id}")
    suspend fun delete(@Path("id") id: String): ThirdPlaceWrapper

    @POST(ServiceEndPoints.THIRDPLACES_ENDPOINT)
    suspend fun post(@Body donation: ThirdPlaceModel): ThirdPlaceWrapper

    @PUT(ServiceEndPoints.THIRDPLACES_ENDPOINT + "/{id}")
    suspend fun put(@Path("id") id: String,
                    @Body donation: ThirdPlaceModel
    ): ThirdPlaceWrapper
}