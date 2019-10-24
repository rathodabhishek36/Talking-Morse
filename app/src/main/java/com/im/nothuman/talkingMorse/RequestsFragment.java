package com.im.nothuman.talkingMorse;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class RequestsFragment extends Fragment {


    private Button button4;
    private FirebaseUser mCurrent_user;
    private DatabaseReference dRef ;

    public RequestsFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_requests, container, false);
        mCurrent_user = FirebaseAuth.getInstance().getCurrentUser();
        final String uid = mCurrent_user.getUid();
        button4 = (Button) rootView.findViewById(R.id.button4);

      dRef = FirebaseDatabase.getInstance().getReference().child("messages").child(uid) ;
      dRef.addValueEventListener(new ValueEventListener() {
          @Override
          public void onDataChange(DataSnapshot dataSnapshot) {

          }

          @Override
          public void onCancelled(DatabaseError databaseError) {

          }
      });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){

                Intent profileIntent = new Intent(getActivity(), user_schedule.class);
                profileIntent.putExtra("user_id", uid);
                startActivity(profileIntent);

            }
        });
        return rootView;




    }

}
