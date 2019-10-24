package com.im.nothuman.talkingMorse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class user_schedule extends AppCompatActivity {

    private Toolbar mToolbar;

    private RecyclerView mUsersList;
    private FirebaseUser mCurrent_user;
    private DatabaseReference mUsersDatabase;

    private LinearLayoutManager mLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_schedule);
        mToolbar = findViewById(R.id.users_appBar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("All Activity");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mCurrent_user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = mCurrent_user.getUid();
        final String user_id = getIntent().getStringExtra("user_id");
        mUsersDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id).child("Activity");

        mLayoutManager = new LinearLayoutManager(this);
        mUsersList = findViewById(R.id.users_list3);
        mUsersList.setHasFixedSize(true);
        mUsersList.setLayoutManager(mLayoutManager);

    }
    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<user, UsersViewHolder2> firebaseRecyclerAdapter2 = new FirebaseRecyclerAdapter<user, UsersViewHolder2>(
                user.class,
                R.layout.users_single_activity,
               UsersViewHolder2.class,
                mUsersDatabase

        ) {
            @Override
            protected void populateViewHolder(UsersViewHolder2 viewHolder, user users123 , int position) {
                viewHolder.setuserTitle(users123.getTitle());
                viewHolder.setuserDescription(users123.getDescription());
                viewHolder.setuserTo(users123.getTo());
                viewHolder.setuserFrom(users123.getFrom());
                viewHolder.setuserStatus(users123.getStatus());
                final String user_id = getRef(position).getKey();
                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent profileIntent = new Intent(user_schedule.this, ProfileActivity.class);
                        profileIntent.putExtra("user_id", user_id);
                        startActivity(profileIntent);

                    }
                });


            }


        };
        mUsersList.setAdapter(firebaseRecyclerAdapter2);
    }
        public static class UsersViewHolder2 extends RecyclerView.ViewHolder {

            View mView;

            public UsersViewHolder2(View itemView) {
                super(itemView);

                mView = itemView;
}


            public void setuserTo(String name){

                TextView  userToView = mView.findViewById(R.id.user_single_to);
                userToView.setText(name);

            }
            public void setuserDescription(String status){

                TextView userDescriptionView = mView.findViewById(R.id.user_single_description);
                userDescriptionView.setText(status);


            }
            public void setuserTitle(String status){

                TextView userTitleView = mView.findViewById(R.id.user_single_title);

                userTitleView.setText(status);


            }  public void setuserFrom(String name){

                TextView   userFromView = mView.findViewById(R.id.user_single_from);
                userFromView.setText(name);

            }

            public void setuserStatus(String status){

                TextView   userStatusView = mView.findViewById(R.id.user_single_status);
                userStatusView.setText(status);


            }


        }



    }







