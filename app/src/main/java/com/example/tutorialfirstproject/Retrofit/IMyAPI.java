package com.example.tutorialfirstproject.Retrofit;

import com.example.tutorialfirstproject.Model.Post;
import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface IMyAPI {
    @GET("posts")
    Observable<List<Post>> getPosts();

    @POST("posts")
    Observable<Post> createPost(@Body Post post);
};

