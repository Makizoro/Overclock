package com.example.a1746074.assessmenthelper;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class Tab1Fragment extends Fragment {
    //
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String TAG = "Tab1Fragment";
    //private static final String ARG_PARAM2 = "param2";
    private Button btnTEST;
    @Nullable

    //
    //private String mParam1;
    //private String mParam2;

    //private OnFragmentInteractionListener mListener;

    /*public Tab1Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TabFragment.
     */
    //
    /*public static Tab1Fragment newInstance(String param1, String param2) {
        Tab1Fragment fragment = new Tab1Fragment();
        Bundle args = new Bundle();
        args.putString(TAG, param1);
        //args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(TAG);
            //mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }*/

    @Override
    public View onCreateView(LayoutInflater inflater,@Nullable ViewGroup container, @Nullable
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.tab1_fragment, container, false);

        // TODO: get array of all clubs
        String Club_id = "";
        ContentValues params = new ContentValues();
        params.put("Club_id",Club_id);
        @SuppressLint("StaticFieldLeak") AsyncHTTPPost asYncHttpPost = new AsyncHTTPPost(
                "http://lamp.ms.wits.ac.za/~s1746074/giveclubs.php",params) {
            @Override
            protected void onPostExecute(String output) {
                processClublist(output);
            }
        };

        return view;
    }

    public void processClublist(String output) {
        LinearLayout l = (LinearLayout)findViewById(R.id.container1);
        l.removeAllViews();
        Log.d("output", output);
        try {
            JSONArray ja = new JSONArray(output);
            for (int i = 0; i < ja.length(); i++) {
                JSONObject jo = (JSONObject) ja.get(i);
                LinearLayout item = (LinearLayout) getLayoutInflater().inflate(R.layout.tab1_fragment, null);


                // TODO: Set field to CLUB_NAME so I can call that field.
                TextView assigntxt = (TextView) item.findViewById(R.id.textTab1);
                assigntxt.setText(jo.getString("CSI_name"));
                final String id = jo.getString("CSI_name");

                l.addView(item);
                item.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        LinearLayout item = (LinearLayout) getLayoutInflater().inflate(R.layout.event_display, null);
                        String club_name = item.toString();
                        ContentValues params = new ContentValues();
                        params.put("club_name", club_name);
                        // TODO: return array of events for that club
                        @SuppressLint("StaticFieldLeak") AsyncHTTPPost AsyncHttpPost = new AsyncHTTPPost(
                                "http://lamp.ms.wits.ac.za/~s1746074/getclubevents.php", params) {
                            @Override
                            protected void onPostExecute(String output) {
                                processEventlist(output);
                            }
                        };
                        AsyncHttpPost.execute();
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    };

    public void processEventlist(String output) {
        LinearLayout l = (LinearLayout)findViewById(R.id.container1);
        l.removeAllViews();
        Log.d("output", output);
        try {
            JSONArray ja = new JSONArray(output);
            for (int i = 0; i < ja.length(); i++) {
                JSONObject jo = (JSONObject) ja.get(i);
                LinearLayout item = (LinearLayout) getLayoutInflater().inflate(R.layout.tab1_fragment, null);


                // TODO: Set field to CLUB_NAME so I can call that field.
                TextView assigntxt = (TextView) item.findViewById(R.id.textTab1);
                assigntxt.setText(jo.getString("EVENT_name"));
                final String id = jo.getString("EVENT_name");

                l.addView(item);
            }
        } catch (Exception e){
            e.printStackTrace();
        };

    // TODO: Rename method, update argument and hook method into UI event
    /*public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    /*public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }*/
}
