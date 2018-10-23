package com.durga.balaji66.deleteitemusingpopupmenuinrecyclerview;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.durga.balaji66.deleteitemusingpopupmenuinrecyclerview.APIs.APIUrl;

import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CandidateListFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView mRecyclerView;

    private FloatingActionButton mFloatingActionButton;

    private CandidateListAdapter mCandidateListAdapter;

    private SwipeRefreshLayout swipeRefreshLayout;

    public CandidateListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        getActivity().setTitle("Candidate List");

        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_candidate_list, container, false);

        mRecyclerView = (RecyclerView)view.findViewById(R.id.candidateList_recyclerView);
        swipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.candidateList_swipeToRefresh);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        initiateListeners();

        getCandidateList();

        return view;
    }

    public void initiateListeners(){
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void getCandidateList() {

            final ProgressDialog progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage("Please Wait...");
            progressDialog.setCancelable(false);
            progressDialog.show();

            Call<ResponseClass> call = APIUrl.getmInstance().getApi().CandidateList();

            call.enqueue(new Callback<ResponseClass>() {
                @Override
                public void onResponse(@NonNull Call<ResponseClass> call, @NonNull Response<ResponseClass> response) {

                    progressDialog.dismiss();

                    ArrayList<ModelClass> user_array;

                    try {

                        assert response.body() != null;
                        user_array = new ArrayList<>(Objects.requireNonNull(response).body().getDetails());

                        mCandidateListAdapter = new CandidateListAdapter(user_array, getActivity());

                        mRecyclerView.setAdapter(mCandidateListAdapter);
                    } catch (Exception e) {

                    }
                }

                @Override
                public void onFailure(@NonNull Call<ResponseClass> call, @NonNull Throwable t) {
                    progressDialog.dismiss();

                }
            });
    }

    @Override
    public void onRefresh() {

        swipeRefreshLayout.setRefreshing(false);
        getCandidateList();
    }
}
