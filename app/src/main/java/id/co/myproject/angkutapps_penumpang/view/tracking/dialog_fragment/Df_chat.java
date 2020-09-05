package id.co.myproject.angkutapps_penumpang.view.tracking.dialog_fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import id.co.myproject.angkutapps_penumpang.R;
import id.co.myproject.angkutapps_penumpang.adapter.rv_chat;
import id.co.myproject.angkutapps_penumpang.helper.Utils;
import id.co.myproject.angkutapps_penumpang.model.data_object.Chat;

public class Df_chat extends DialogFragment {

    TextView tv_nama_driver, tv_plat_driver;
    RecyclerView rvChat;
    EditText etPesan;
    ImageView btnSend;
    rv_chat rvAdapter;

    ProgressDialog progressDialog;

    SharedPreferences sharedPreferences;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("message");
    private String temp_key;
    DatabaseReference root;
    ArrayList<Chat> list = new ArrayList<>();

    String noHpUser;
    String noHpDriver, nama_driver, plat_mobil;

    public Df_chat(String noHpDriver) {
        this.noHpDriver = noHpDriver;
    }

    public Df_chat(String no_hp, String nama_driver, String plat_mobil) {
        this.noHpDriver = no_hp;
        this.nama_driver = nama_driver;
        this.plat_mobil = plat_mobil;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frame_messaging, container, false);
    }

    @Override
    public void onViewCreated(View view,Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tv_nama_driver = view.findViewById(R.id.tv_nama_driver);
        tv_plat_driver = view.findViewById(R.id.tv_plat_driver);
        rvChat = view.findViewById(R.id.rvPesan);
        etPesan = view.findViewById(R.id.etPesan);
        btnSend = view.findViewById(R.id.imgButtonSend);
        sharedPreferences = getActivity().getApplicationContext().getSharedPreferences(Utils.LOGIN_KEY, Context.MODE_PRIVATE);
        noHpUser = sharedPreferences.getString(Utils.NO_HP_USER_KEY, "");

        tv_nama_driver.setText(nama_driver);
        tv_plat_driver.setText(plat_mobil);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
//        layoutManager.setStackFromEnd(true);
//        layoutManager.setReverseLayout(true);
        rvChat.setLayoutManager(layoutManager);
        rvChat.setHasFixedSize(false);

        root = myRef.child(noHpUser+"-"+noHpDriver);
        progressDialog.dismiss();

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etPesan.getText().toString().equalsIgnoreCase("")){
                    Toast.makeText(getContext(), "Tolong Tulis Pesan Terlebih Dahulu", Toast.LENGTH_SHORT).show();
                }else{
                    Map<String,Object> map = new HashMap<String, Object>();
                    temp_key = root.push().getKey();
                    root.updateChildren(map);

                    Calendar calendar = Calendar.getInstance();
                    String tgl = DateFormat.format("dd/MM/yyyy HH:mm", calendar.getTime()).toString();

                    DatabaseReference message_root = root.child(temp_key);
                    Map<String,Object> map2 = new HashMap<String, Object>();
                    map2.put("msg",etPesan.getText().toString().trim());
                    map2.put("waktu", tgl);
                    map2.put("kondisi","Penumpang");

                    message_root.updateChildren(map2);
                    etPesan.setText("");
                }
            }
        });

        root.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                append_chat_conversatin(dataSnapshot);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                append_chat_conversatin(dataSnapshot);
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private String chat_msg, chat_user_name, chat_kondisi, chat_waktu;

    private void append_chat_conversatin(DataSnapshot dataSnapshot) {
        Iterator i = dataSnapshot.getChildren().iterator();
        while (i.hasNext()) {
            chat_kondisi = (String) ((DataSnapshot) i.next()).getValue();
            chat_msg = (String) ((DataSnapshot) i.next()).getValue();
            chat_waktu = (String) ((DataSnapshot) i.next()).getValue();

            list.add(new Chat(chat_kondisi, chat_msg, chat_waktu));
            rvAdapter =new rv_chat(list, getContext());
            rvChat.setAdapter(rvAdapter);
            rvChat.scrollToPosition(rvAdapter.getItemCount()-1);

            //            chat_conversation.append(chat_kondisi + " : " + chat_msg + " : "+chat_user_name+" : "+chat_waktu +"\n");
        }
    }
}
