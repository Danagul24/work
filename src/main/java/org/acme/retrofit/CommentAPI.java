package org.acme.retrofit;

import retrofit2.Call;

import retrofit2.Response;
import retrofit2.http.*;

import java.util.List;

public interface CommentAPI {

    @GET("comments/")
    Call<List<Comment>> getComments();

    @GET("comments/{id}")
    Call<Comment> getCommentById(@Path("id") int id);

    @POST("comments/")
    Call<Comment> createComment(@Body Comment comment);

    @DELETE("comments/{id}/")
    Call<Comment> deleteComment(@Path("id") int id);

    @PUT("comments/{id}")
    Call<Comment> updateComment(@Path("id") int id, @Body Comment comment);
}
