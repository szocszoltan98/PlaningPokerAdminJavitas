package com.example.admin.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.Database.Fire_CreateGroup;
import com.example.admin.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CreateGroup extends Fragment {
    private EditText code;
    private EditText gname;
    private Button createGroupBtn;
    private DatabaseReference db;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_create_group,container,false);
        code=v.findViewById(R.id.groupCode);
        gname=v.findViewById(R.id.groupName);
        createGroupBtn=v.findViewById(R.id.createGroupButton);
        createGroupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String codeS=code.getText().toString();
                final String gnameS=gname.getText().toString();
                //create a new group
                db= FirebaseDatabase.getInstance().getReference("groups");
                db.child(codeS).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String value = dataSnapshot.getValue(String.class);
                        if(dataSnapshot.exists()==true)
                        {
                            Toast.makeText(getContext() , "Group exist!", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        else {
                            addForm();
                            FragmentTransaction fr=getFragmentManager().beginTransaction();
                            Fragment f=new AddQuestion();
                            fr.addToBackStack(null);
                            fr.replace(R.id.fragment_container,f);
                            Bundle args=new Bundle();
                            args.putString("groupCode",codeS);
                            f.setArguments(args);
                            fr.commit();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }

        });
        return v;
    }
    //insert group to database
    public void addForm()
    {
        code=getView().findViewById(R.id.groupCode);
        String codeS=code.getText().toString();
        gname=getView().findViewById(R.id.groupName);
        String groupNameS=gname.getText().toString();
        db = FirebaseDatabase.getInstance().getReference("groups");
        Fire_CreateGroup group = new Fire_CreateGroup(codeS,groupNameS);
        db.child(codeS).setValue(groupNameS);
        Toast.makeText(getActivity(), "GROUP IS CREATED!", Toast.LENGTH_SHORT).show();

    }



}
