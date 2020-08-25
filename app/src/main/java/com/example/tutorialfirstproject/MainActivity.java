package com.example.tutorialfirstproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.tutorialfirstproject.Adapter.PostAdapter;
import com.example.tutorialfirstproject.Model.Post;
import com.example.tutorialfirstproject.Retrofit.IMyAPI;
import com.example.tutorialfirstproject.Retrofit.RetrofitClient;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;


public class MainActivity extends AppCompatActivity {

    IMyAPI iMyAPI;
    RecyclerView recyclerView;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = RetrofitClient.getInstance();
        iMyAPI = retrofit.create(IMyAPI.class);

        recyclerView = findViewById(R.id.recyclerview_container);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fetchData();
        createPost();
    }

    private void fetchData() {
        compositeDisposable.add(iMyAPI.getPosts()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<List<Post>>() {
            @Override
            public void accept(List<Post> posts) throws Exception {
                displayData(posts);
            }
        }));


    }

    private void displayData(List<Post> posts) {
        PostAdapter adapter = new PostAdapter(this, posts);
        recyclerView.setAdapter(adapter);
    }

    private  void createPost() {
//        Post post = new Post(2,1,"Name","Sameer");
        Post postBody = new Post();
        postBody.setUserId(20);
        postBody.setTitle("Name");
        postBody.setBody("Sameer");
        IMyAPI myAPI = RetrofitClient.getInstance().create(IMyAPI.class);
        myAPI.createPost(postBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Post>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d("LOG", "Subscribe : start");
                    }

                    @Override
                    public void onNext(Post post) {
                        Log.d("LOG", "Next : start");
                    }


                    @Override
                    public void onError(Throwable e) {
                        Log.d("LOG", "Error : start");
                        Log.d("LOG", e.getMessage(),e.getCause());
                    }

                    @Override
                    public void onComplete() {
                        Log.d("LOG", "Completed : start");
                    }
                });

    }


    @Override
    protected void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }
}




//        trying code
//        AuthBody authBody = new AuthBody();
//        authBody.setUsername(username.getText().toString());
//        authBody.setPassword(password.getText().toString());
//        Log.e("ButtonP", gson.toJson(authBody));
//        Api_interface api_interface = RetrofitClient.getClient(MainActivity.this).create(Api_interface.class);
//        api_interface.postInit(authBody)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .doOnSubscribe(a -> {
//                })
//                .doOnError(e -> Log.e("onError", gson.toJson(e)))
//                .subscribe(response -> {
//                    responseData = response;
//                    String str = gson.toJson(response);
//                    Log.e("string", str);
//                    ends here