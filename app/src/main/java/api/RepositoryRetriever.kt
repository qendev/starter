package api


import com.raywenderlich.githubrepolist.RepoResult
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RepositoryRetriever {
    private val service: GithubService

    companion object {
        //1
        //Specifies the base URL.
        const val BASE_URL = "https://api.github.com/"
    }

    init {
        // 2
        //Creates a Retrofit object.
        val retrofit = Retrofit.Builder()
                // 1
                .baseUrl(BASE_URL)
                //3
                //Specifies GsonConverterFactory as the converter, which uses Gson for its JSON deserialization.
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        //4
        //Generates an implementation of the GithubService interface using the Retrofit object.
        service = retrofit.create(GithubService::class.java)
    }
    //to use Kotlin Coroutines
    suspend fun getRepositories(): RepoResult  {
        return service.searchRepositories()
    }
}
//About Retrofit
//Retrofit is an Android and Java library that excels at retrieving and uploading structured data, such as JSON and XML.
// This library makes HTTP requests using OkHttp, another library from Square.

//About Okhttp
//OkHttp is an efficient HTTP client that supports synchronous and asynchronous calls.
// It handles the opening and closing of connections along with InputStream-to-string conversion.