package com.example.harshyadav.renerahomeslandlord;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.myViewHolder> {


    Context mContext;
    List<item> mData;
    private String name, phone, password, location, address, preferance;

    public Adapter(Context mContext, List<item> mData, String name, String phone, String password, String location, String address, String preferance ) {
        this.mContext = mContext;
        this.mData = mData;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.location = location;
        this.password = password;
        this.preferance = preferance;

    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View v = inflater.inflate(R.layout.card_item, viewGroup, false);
        return new myViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder myViewHolder, final int i) {

        myViewHolder.background.setImageResource(mData.get(i).getBackground());
        myViewHolder.type.setText(mData.get(i).getType());

        myViewHolder.background.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, mData.get(i).getType() , Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(mContext, NumberOfRooms.class);
                    intent.putExtra("name", name);
                    intent.putExtra("phone",phone);
                    intent.putExtra("password",password);
                    intent.putExtra("location",location);
                    intent.putExtra("address",address);
                    intent.putExtra("preferance",preferance);
                    intent.putExtra("type",mData.get(i).getType());
                    mContext.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder{

        ImageView background;
        TextView type;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            background = itemView.findViewById(R.id.background_img);
            type = itemView.findViewById(R.id.card_type);



        }
    }

}
