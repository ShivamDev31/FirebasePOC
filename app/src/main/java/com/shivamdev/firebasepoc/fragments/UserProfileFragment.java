package com.shivamdev.firebasepoc.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.shivamdev.firebasepoc.R;
import com.shivamdev.firebasepoc.commons.BaseFragment;

/**
 * Created by Shivam on 21-08-2016.
 */

public class UserProfileFragment extends BaseFragment {

    private static final String TAG = UserProfileFragment.class.getSimpleName();

    // Firebase stuff
    private FirebaseAuth firebaseAuth;


    public static UserProfileFragment newInstance() {
        UserProfileFragment fragment = new UserProfileFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        firebaseAuth = FirebaseAuth.getInstance();
        return inflater.inflate(R.layout.user_profile_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView tvLoggedin = (TextView) view.findViewById(R.id.tv_logged_in);
        String userEmail = firebaseAuth.getCurrentUser().getEmail();
        if (TextUtils.isEmpty(userEmail)) {
            tvLoggedin.setText(R.string.no_user_found);
        } else {
            tvLoggedin.setText(getString(R.string.user_email, userEmail));
        }
    }
}
