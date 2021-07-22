package org.acme.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class Controller {
    static final String BASE_URL = "https://jsonplaceholder.typicode.com/";
    public CommentAPI commentAPI;

    public Controller()  {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        commentAPI = retrofit.create(CommentAPI.class);
    }


    public CommentAPI getAPI() {
        return commentAPI;
    }
}
