package com.example.newapp.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.newapp.R;
import com.example.newapp.activities.Model.Data;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 */
public class IncomeFragment extends Fragment {


    private FirebaseAuth mAuth;
    private DatabaseReference mIncomeDatabase;
   // RecycleView

private RecyclerView recyclerView;


    public IncomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,
                             Bundle savedInstanceState) {

     View myview=inflater.inflate(R.layout.fragment_income,container,false);
     mAuth=FirebaseAuth.getInstance();
     FirebaseUser mUser=mAuth.getCurrentUser();

     String uid=mUser.getUid();

     mIncomeDatabase= FirebaseDatabase.getInstance().getReference().child("IncomeData").child(uid);
recyclerView=myview.findViewById(R.id.recycler_id_income);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
       layoutManager.setReverseLayout(true);
       layoutManager.setReverseLayout(true);
       recyclerView.setHasFixedSize(true);
       recyclerView.setLayoutManager(layoutManager);

        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_income,container,false);

        return  myview;
    }



   public void onStart() {
       super.onStart();

       FirebaseRecyclerAdapter<Data,MyViewHolder>adapter=new FirebaseRecyclerAdapter<Data, MyViewHolder>
               (
                       Data.class,
                       R.layout.income_recycle_data,
                       MyViewHolder.class,
                       mIncomeDatabase
               ) {
           @Override
           protected void populateViewHolder(MyViewHolder viewHolder, final Data model, final int position) {

               viewHolder.setmView(model.getType());
               viewHolder.setNote(model.getNote());
               viewHolder.setDate(model.getDate());
               viewHolder.setAmmount(model.getAmount());

               viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View view) {

                    // post_key=getRef(position).getKey();

                     // type=model.getType();
                   //   note=model.getNote();
                   //    amount=model.getAmount();

                   //    updateDataItem();
                   }
               });

           }
       };

       recyclerView.setAdapter(adapter);

   }


public static class MyViewHolder extends RecyclerView.ViewHolder{
View mView;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        mView=itemView;
    }

    private void setmView(String type){

        TextView mType=mView.findViewById(R.id.type_txt_income);
        mType.setText(type);
    }

    private void setNote(String note){

        TextView mNote=mView.findViewById(R.id.note_txt_income);
        mNote.setText(note);

    }

    private void setDate(String date){
        TextView mDate=mView.findViewById(R.id.date_txt_income);
        mDate.setText(date);
    }


    private void setAmmount(int ammount){

        TextView mAmmount=mView.findViewById(R.id.ammount_txt_income);
        String stammount=String.valueOf(ammount);
        mAmmount.setText(stammount);

    }
}

}
