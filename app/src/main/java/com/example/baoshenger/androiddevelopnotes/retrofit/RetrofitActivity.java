package com.example.baoshenger.androiddevelopnotes.retrofit;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitActivity extends ListActivity {



    ListView mListView;
    Callback<List<GitHubClient.Contributor>> callback = new Callback<List<GitHubClient.Contributor>>() {

        @Override
        public void onResponse(Call<List<GitHubClient.Contributor>> call, Response<List<GitHubClient.Contributor>> response) {

            ArrayAdapter<GitHubClient.Contributor> adapter = new ArrayAdapter<GitHubClient.Contributor>(getApplicationContext(),
                    android.R.layout.simple_list_item_1, (List<GitHubClient.Contributor>) call);
            mListView.setAdapter(adapter);
        }

        @Override
        public void onFailure(Call<List<GitHubClient.Contributor>> call, Throwable t) {

        }


    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mListView = getListView();
        GitHubClient.getContributors(callback);
    }
}
