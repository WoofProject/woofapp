package com.enterprises.woof.woof;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FragmentClient extends Fragment {

    View view;
    ViewPager viewPager;
    DatabaseHelper helper;
    public final static String emailregex = " ^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
    private Pattern pattern;
    private Matcher matcher;

    public FragmentClient() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.client_fragment, container, false);
        helper = new DatabaseHelper(view.getContext());

        Button nextPage = (Button) view.findViewById(R.id.nextPage);
        viewPager = (ViewPager) getActivity().findViewById(R.id.viewpager);
        nextPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText name = getActivity().findViewById(R.id.clientname);
                EditText email = getActivity().findViewById(R.id.clientemail);
                EditText password = getActivity().findViewById(R.id.clientpassword);
                EditText password2 = getActivity().findViewById(R.id.clientpasswordconfirm);

                String namestr = name.getText().toString();
                String emailstr = email.getText().toString();
                String passwordstr = password.getText().toString();
                String passwordstr2 = password2.getText().toString();

                pattern = Pattern.compile(emailregex);
                matcher = pattern.matcher(email.getText().toString());

                    if (matcher.matches()) {
                        if (!passwordstr.equals(passwordstr2)) {
                            //incorrect password
                            Toast pass = Toast.makeText(getActivity(), "Passwords don't match!", Toast.LENGTH_SHORT);
                            pass.show();
                        } else {
                            //insert to database
                            Client c = new Client();
                            c.setName(namestr);
                            c.setEmail(emailstr);
                            c.setPassword(passwordstr);
                            helper.insertClient(c);
                            viewPager.setCurrentItem(1);
                        }
                    } else {
                        Toast pass = Toast.makeText(getActivity(), "Username/email is invalid!", Toast.LENGTH_SHORT);
                        pass.show();
                    }
            }
        });
        return view;
    }

}
