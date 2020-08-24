package com.example.tutorialfirstproject.Retrofit;



import com.example.tutorialfirstproject.Model.Post;
import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface IMyAPI {
    @GET("posts")
    Observable<List<Post>> getPosts();
}