package com.example.mydaysheduler;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

@SuppressWarnings("ALL")
public class Home extends Fragment {
    private SQLiteDatabase mdatabase;
    private EventsAdap madapter;
    ImageView deleteall;
    FloatingActionButton floatingActionButton;

    EventDBHelper mhelper;
    RecyclerView recyclerView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View myview2= inflater.inflate(R.layout.home,container,false);

        floatingActionButton=myview2.findViewById(R.id.floatingActionButton2);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // startActivity(new Intent(getContext(),alarm.class));
                startActivity(new Intent(getContext(),MainActivity.class).putExtra("frag","myfrag"));
            }
        });


try {



    mhelper = new EventDBHelper(getContext());
    mdatabase = mhelper.getWritableDatabase();
    recyclerView = myview2.findViewById(R.id.recycleview);
    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    madapter = new EventsAdap(getContext(), getallitem());
    recyclerView.setAdapter(madapter);

    new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            removeItem((String)viewHolder.itemView.getTag());
           // Log.d("HOME", (String)viewHolder.itemView.getTag());
        }
    }).attachToRecyclerView(recyclerView);


    Toast.makeText(getContext(), "nooo error", Toast.LENGTH_SHORT).show();
}catch (Exception e){
    Toast.makeText(getContext(), "error"+e, Toast.LENGTH_SHORT).show();
}

        return  myview2;
    }
    private Cursor getallitem(){
        return mdatabase.query(Eventlisting.Evententr.TABLE_NAME,null,null,null,null,null,null );
    }

    private void removeItem(String name){
        mdatabase.execSQL("DELETE FROM eventst WHERE name ='"+name+"'");
        madapter.swapCursor(getallitem());
    }



    public void changeHomefrag() {}
}
