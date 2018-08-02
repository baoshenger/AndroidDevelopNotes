package com.example.baoshenger.androiddevelopnotes.retrofit;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class GitHubClient {

    private static final String API_URL = "https://api.github.com";

    static class Contributor {
        String login;
        int contributions;

        @Override
        public String toString() {
            return login + ", " + contributions;
        }

    }

    interface GitHub {
        @GET("/repos/{owner}/{repo}/contributors")
        Call<ResponseBody>   contributors(@Path("owner") String owner, @Path("repo") String repo);


    }

    public static void getContributors(Callback<List<Contributor>> callback) {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .build();
        GitHub github = retrofit.create(GitHub.class);
        // Fetch and print a list of the contributors to this library.
        Call<ResponseBody> call =  github.contributors("square", "retrofit");

        printResponseBody(call);




    }




    public static void printResponseBody(Call<ResponseBody> call) {


        final Gson gson = new GsonBuilder().create();

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                System.out.println(response.body());

                Log.i(GitHubClient.class.getSimpleName(),gson.toJson(response.body().source().toString()));
                Log.i(GitHubClient.class.getSimpleName(),response.body().source().toString());
                Log.i(GitHubClient.class.getSimpleName(),gson.toJson(response.headers()));
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });



    }
}
