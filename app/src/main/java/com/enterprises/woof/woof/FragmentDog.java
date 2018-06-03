package com.enterprises.woof.woof;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Spinner;
import android.widget.ArrayAdapter;


public class FragmentDog extends Fragment implements AdapterView.OnItemSelectedListener{
    View view;
    Spinner spinner;
    Spinner spinner2;
    ArrayAdapter<CharSequence> adapter;
    ArrayAdapter<CharSequence> adapter2;
    DatabaseHelper helper;

    Client client = new Client();
    CurrentUser user;

    public FragmentDog() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.dog_fragment, container, false);
        user = new CurrentUser(view.getContext());
        helper = new DatabaseHelper(view.getContext());
        Button register = (Button) view.findViewById(R.id.register);

        //spinner = view.findViewById(R.id.spinner1);
        //adapter = ArrayAdapter.createFromResource(this , R.array.breeds, android.R.layout.simple_spinner_item);
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //spinner.setAdapter(adapter);
        //spinner.setOnItemSelectedListener(this);

        spinner = view.findViewById(R.id.spinner1);
        spinner2 = view.findViewById(R.id.spinner2);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText a = getActivity().findViewById(R.id.doggoName);
                String nameDog = a.getText().toString();

                Spinner spinner = (Spinner)getActivity().findViewById(R.id.spinner1);
                String text = spinner.getSelectedItem().toString();


                client.setDogName(nameDog);
                Log.d("test", text);
                client.setDogBreed(text);
                helper.insertClient(client);
                Toast registered = Toast.makeText(getActivity(), "Successfully created account, welcome!", Toast.LENGTH_SHORT);
                registered.show();
                startActivity(new Intent(getActivity(), Login.class));
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        adapter = ArrayAdapter.createFromResource(getActivity(), R.array.breeds, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        adapter2 = ArrayAdapter.createFromResource(getActivity(), R.array.ages, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String breed = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(adapterView.getContext(), breed, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }
}
