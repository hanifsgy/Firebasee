package com.example.hanifsugiyanto.firebasee;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText inputMessage;
    Button btnSend;
    RecyclerView recyclerView;


    //String variable
    String username;

    //variabel list chat
    private List<Chat> dataChat;
    private ChatAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataChat = new ArrayList<>();

        //ambil data dari activity input username

        username = getIntent().getStringExtra("username");


        inputMessage = (EditText) findViewById(R.id.inputMessage);
        btnSend = (Button) findViewById(R.id.btnSend);
        recyclerView = (RecyclerView) findViewById(R.id.recycleView);


        final Firebase firebase = new Firebase("https://eds.firebaseio.com");



        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                firebase.push().setValue(new Chat(username, inputMessage.getText().toString()));
                inputMessage.setText("");
            }


    });

        adapter = new ChatAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter.setListChat(dataChat);


        firebase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dataChat.clear();

                //setiap data chat yang masuk
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Chat chat = postSnapshot.getValue(Chat.class);
                    dataChat.add(chat);

                    //digunakan buat debugging penting ini buat masa-masa development
                    Log.d("data", "chat->() returned: " +chat.message + "user->"+chat.user );
                }

                //ketika di notify ada data yang dirubah, maka akan di refresh oleh recycleview
                adapter.notifyDataSetChanged();
                //menampilkan data yang baru counting harus dikurangi -1
                recyclerView.scrollToPosition(adapter.getItemCount()-1);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
