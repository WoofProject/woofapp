package com.enterprises.woof.woof;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class FragmentDog extends Fragment {
    View view;

    public FragmentDog() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.dog_fragment, container, false);
        Button register = (Button) view.findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast registered = Toast.makeText(getActivity(), "Successfully created account, welcome!", Toast.LENGTH_SHORT);
                registered.show();
                startActivity(new Intent(getActivity(), Login.class));
            }
        });
        return view;
    }
}
