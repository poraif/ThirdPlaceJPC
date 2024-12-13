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

    @GET(ServiceEndPoints.THIRDPLACES_ENDPOINT + "/{email}")
    suspend fun getall(
        @Path("email") email: String)
            : Response<List<ThirdPlaceModel>>

    @GET(ServiceEndPoints.THIRDPLACES_ENDPOINT + "/{email}" + "/{id}")
    suspend fun get(@Path("email") email: String,
                    @Path("id") id: String): Response<List<ThirdPlaceModel>>

    @GET(ServiceEndPoints.THIRDPLACES_ENDPOINT)
    suspend fun admingetall(): Response<List<ThirdPlaceModel>>

    @GET(ServiceEndPoints.THIRDPLACES_ENDPOINT + "/{id}")
    suspend fun adminget(@Path("id") id: String): Response<List<ThirdPlaceModel>>

    @DELETE(ServiceEndPoints.THIRDPLACES_ENDPOINT + "/{email}" + "/{id}")
    suspend fun delete(@Path("email") email: String,
                       @Path("id") id: String): ThirdPlaceWrapper

    @DELETE(ServiceEndPoints.THIRDPLACES_ENDPOINT + "/{id}")
    suspend fun adminDelete(@Path("id") id: String): ThirdPlaceWrapper

    @POST(ServiceEndPoints.THIRDPLACES_ENDPOINT + "/{email}")
    suspend fun post(@Path("email") email: String,
                     @Body donation: ThirdPlaceModel): ThirdPlaceWrapper

    @PUT(ServiceEndPoints.THIRDPLACES_ENDPOINT + "/{email}" + "/{id}")
    suspend fun put(@Path("email") email: String,
                    @Path("id") id: String,
                    @Body donation: ThirdPlaceModel

    ): ThirdPlaceWrapper
}