package com.example.admin.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.admin.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Login extends Fragment {
    public Button createGroupBtn;
    public Button goToTheGroupBtn;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference db = database.getReference().child("Groups");
    private EditText gname,gcode;
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_login, container, false);
        goToTheGroupBtn=v.findViewById(R.id.LoginButton);
        createGroupBtn=v.findViewById(R.id.createGroupButton);
        gname=v.findViewById(R.id.groupName);
        gcode=v.findViewById(R.id.groupCode);
        createGroupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container,new CreateGroup());
                fr.commit();}
        });

        //connect to group
        goToTheGroupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkDataEntered()==true){
                final String codeS=gcode.getText().toString();
                final String gnameS=gname.getText().toString();
                db= FirebaseDatabase.getInstance().getReference("groups");
                db.child(codeS).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String value = dataSnapshot.getValue(String.class);
                        if (dataSnapshot.exists() == false) {
                            Toast.makeText(getContext(), "Group is not exist!", Toast.LENGTH_SHORT).show();
                            return;
                        } else {

                            FragmentTransaction fr = getFragmentManager().beginTransaction();
                            Fragment f = new AddQuestion();
                            fr.addToBackStack(null);
                            fr.replace(R.id.fragment_container, f);
                            Bundle args = new Bundle();
                            args.putString("groupCode", codeS);
                            f.setArguments(args);
                            fr.commit();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });}
            }
        });
        return v;
    }


    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }


    boolean checkDataEntered() {
        boolean isTrue = true;
        if (isEmpty(gcode)) {
            gcode.setError("Group code is required!");
            isTrue = false;
        }

        if (isEmpty(gname)) {
            gname.setError("Group name is required!");
            isTrue = false;
        }
        return isTrue;
    }

}
