package api


import com.raywenderlich.githubrepolist.RepoResult
import retrofit2.http.GET

//This code creates an interface that lets Retrofit connect to the GitHub API.
// I’ve also added two methods to the interface with @GET annotations that specify the GitHub endpoints to make GET requests.

interface GithubService {
    @GET("/repositories")
    suspend fun retrieveRepositories(): RepoResult

    //sample search
    @GET("/search/repositories?q=language:kotlin&sort=stars&order=desc&per_page=50")
    suspend fun searchRepositories(): RepoResult
}

//Notice that suspend appears in front of the function names.
// Also, there’s no need to wrap the return value in Call anymore.
// This transforms the functions into coroutines.