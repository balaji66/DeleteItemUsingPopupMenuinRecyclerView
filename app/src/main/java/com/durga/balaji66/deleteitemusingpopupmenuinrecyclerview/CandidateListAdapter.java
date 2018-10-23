package com.durga.balaji66.deleteitemusingpopupmenuinrecyclerview;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.durga.balaji66.deleteitemusingpopupmenuinrecyclerview.APIs.APIUrl;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CandidateListAdapter extends RecyclerView.Adapter<CandidateListAdapter.CandidateListViewHolder> {

    private String mUid;
    private List<ModelClass> candidateList;
    private Context mCtx;

    private int pos;

    public CandidateListAdapter(List<ModelClass> candidateList, Context mCtx) {
        this.candidateList = candidateList;
        this.mCtx = mCtx;
    }

    @NonNull
    @Override
    public CandidateListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_candidate_list, parent, false);

        return new CandidateListViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final CandidateListViewHolder holder, final int position) {

        ModelClass candidate = candidateList.get(position);
        holder.mUID.setText(candidate.getmUId());
        holder.mName.setText(candidate.getmName());
        holder.mMobile.setText(candidate.getmMobile());
        holder.mDelete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View v) {
                //creating a popup menu
                PopupMenu popup = new PopupMenu(v.getContext(), v);
                //inflating menu from xml resource
                popup.inflate(R.menu.menu_candidatelist_delete);
                //adding click listener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.menu_candidate_delete:
                                //handle menu1 click

                                Toast.makeText(mCtx, holder.mUID.getText().toString(), Toast.LENGTH_SHORT).show();

                                mUid = holder.mUID.getText().toString();

                                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(v.getContext());
                                alertDialogBuilder.setMessage("Are You Sure Want to Delete ?");
                                alertDialogBuilder.setPositiveButton("yes",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface arg0, int arg1) {

                                                pos = position;

                                                deleteCandidate();

//                                                candidateList.remove(position);
//                                                notifyItemRemoved(position);

                                            }
                                        });

                                alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });
                                AlertDialog alertDialog = alertDialogBuilder.create();

                                alertDialog.show();

                                return true;

                        }

                        return false;
                    }

                });
                popup.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return candidateList.size();
    }

    public class CandidateListViewHolder extends RecyclerView.ViewHolder {

        TextView mUID,mName,mMobile,mDelete;

        public CandidateListViewHolder(View itemView) {
            super(itemView);

            mUID = (TextView)itemView.findViewById(R.id.user_Id);
            mName = (TextView)itemView.findViewById(R.id.name);
            mMobile = (TextView)itemView.findViewById(R.id.mobile);
            mDelete = (TextView)itemView.findViewById(R.id.deleteCandidate);

        }
    }

    public void deleteCandidate(){

        final ProgressDialog progressDialog = new ProgressDialog(mCtx);
        progressDialog.setMessage("Please wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();

//        String phone = mID.getText().toString().trim();
//        String password = mPassword.getText().toString().trim();

        Call<ResponseBody> call = APIUrl.getmInstance().getApi().DeleteParticularCandidate(mUid);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {

                if(response.code() == 201)
                {
                    progressDialog.dismiss();

//                    notifyDataSetChanged();

                    candidateList.remove(pos);
                    notifyItemRemoved(pos);

                    Toast.makeText(mCtx,"Deleted Successfully",Toast.LENGTH_LONG).show();


                }
                else if( response.code() == 401)
                {
                    progressDialog.dismiss();
//                    Toast.makeText(mCtx,"Invalid Mobile Number or password",Toast.LENGTH_LONG).show();
                }

                else
                {
                    progressDialog.dismiss();

                    Toast.makeText(mCtx,"Please Try After Some Time",Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                progressDialog.dismiss();

            }
        });
    }
}
