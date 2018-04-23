package com.example.astimen.absensi.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.astimen.absensi.EndPoint;
import com.example.astimen.absensi.R;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

public class frg_input_karyawan extends Fragment {
    private OnFragmentInteractionListener mListener;
    public frg_input_karyawan() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        new f_insert().execute();
        return inflater.inflate(R.layout.fragment_input_karyawan, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    class f_insert extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        protected String doInBackground(String... args) {
            return null;
        }
        protected void onPostExecute(String file_url) {

            Button btn_save = (Button) getView().findViewById(R.id.btnSave);
            btn_save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // do something

                    EditText etUserId = (EditText) getView().findViewById(R.id.txtUserId);
                    final String dtxtUserId=etUserId.getText().toString();

                    final String dtxtUserName   = ((EditText) getView().findViewById(R.id.txtUserName)).getText().toString();
                    final String dtxtPassword   = ((EditText) getView().findViewById(R.id.txtPassword)).getText().toString();
                    final String dtxtPassword2  = ((EditText) getView().findViewById(R.id.txtPassword2)).getText().toString();
                    final String dspnDepartment = ((Spinner) getView().findViewById(R.id.spnDepartment)).getSelectedItem().toString();

                    final int drbSex = ((RadioGroup) getView().findViewById(R.id.rgSex)).getCheckedRadioButtonId();
                    final RadioButton rbSex = ((RadioButton) getView().findViewById(drbSex));
                    final String sdrbSex = rbSex.getText().toString();

                    final Boolean bolRoleAdmin=((CheckBox) getView().findViewById(R.id.cbxAdmin)).isChecked();
                    final Boolean bolRoleUser=((CheckBox) getView().findViewById(R.id.cbxUser)).isChecked();

                    final String sbolRoleAdmin = bolRoleAdmin.toString();
                    final String sbolRoleUser = bolRoleUser.toString();

                    if(dtxtUserId.trim().length()==0){
                        pesandialog("Entrian User Id tidak boleh kosong ...!");
                        etUserId.requestFocus();
                    }
                    else if(dtxtUserName.trim().length()==0){
                        pesandialog("Entrian User Name tidak boleh kosong ...!");
                        ((EditText) getView().findViewById(R.id.txtUserName)).requestFocus();
                    }
                    else if(dtxtPassword.trim().length()==0){
                        pesandialog("Entrian Password tidak boleh kosong ...!");
                        ((EditText) getView().findViewById(R.id.txtPassword)).requestFocus();
                    }
                    else if(dtxtPassword2.trim().length()==0){
                        pesandialog("Entrian Password kedua tidak boleh kosong ...!");
                        ((EditText) getView().findViewById(R.id.txtPassword2)).requestFocus();
                    }

                    //pengecekan password satu dan dua
                    if(!dtxtPassword.equals(dtxtPassword2)){
                        pesandialog("Password dan Pasword2 tidak sama ...!");
                        ((EditText) getView().findViewById(R.id.txtPassword2)).requestFocus();
                    }

                    //------volley
                    final String REGISTER_URL = EndPoint.ROOT_URL+"/volleySimpan.php";
                    final String KEY_USER_ID = "user_id";
                    final String KEY_USER_NAME = "email";
                    final String KEY_PASSWORD = "password";
                    final String KEY_DEPARTMENT = "department";
                    final String KEY_USER_SEX = "user_sex";
                    final String KEY_ROLE_ADMIN = "role_admin";
                    final String KEY_ROLE_USER = "role_user";

                    StringRequest stringRequest = new StringRequest(Request.Method.POST, REGISTER_URL,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Toast.makeText(getActivity(),"Server : " + response, Toast.LENGTH_LONG).show();
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(getActivity(),"Server :" + error.toString(), Toast.LENGTH_LONG).show();
                                }
                            }){
                        @Override
                        protected Map<String,String> getParams(){
                            Map<String,String> params = new HashMap<String, String>();
                            params.put(KEY_USER_ID, dtxtUserId);
                            params.put(KEY_USER_NAME, dtxtUserName);
                            params.put(KEY_PASSWORD, dtxtPassword);
                            params.put(KEY_DEPARTMENT, dspnDepartment);
                            params.put(KEY_USER_SEX, sdrbSex);
                            params.put(KEY_ROLE_ADMIN, sbolRoleAdmin);
                            params.put(KEY_ROLE_USER, sbolRoleUser);
                            return params;
                        }
                    };
                    RequestQueue requestQueue = Volley.newRequestQueue(getContext());
                    requestQueue.add(stringRequest);
                    //------volley
                }
            });
        }
    }

    void pesandialog(String pesan){
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which){
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        Toast.makeText(getActivity(), "Isi kembali entrian yang kosong ...", Toast.LENGTH_LONG).show();
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(getView().getContext());
        builder
                .setTitle("Alert")
                .setMessage(pesan)
                .setCancelable(false)
                .setPositiveButton("OK",dialogClickListener)
                .show();
    }
}