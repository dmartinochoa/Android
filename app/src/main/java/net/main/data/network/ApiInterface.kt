import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {
    @GET("/3/movie/{category}")
    fun getMovies(
        @Path("category") category: String?,
        @Query("api_key") apiKey: String?,
        @Query("language") language: String?
    ): Call<MovieResult?>?

    @GET("/3/search/movie")
    fun searchMovies(
        @Query("api_key") apiKey: String?,
        @Query("query") name: String?,
        @Query("language") language: String?
    ): Call<MovieResult?>?

}