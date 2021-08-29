package com.hoyaok2.bestbuddy;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class Chatting_RecycleAdapter extends RecyclerView.Adapter<Chatting_RecycleAdapter.VH> {

    Context context;
    ArrayList<String> items;
    ArrayList<Integer> serverNum;

    public Chatting_RecycleAdapter(Context context, ArrayList<String> items, ArrayList<Integer> serverNum) {
        this.context = context;
        this.items = items;
        this.serverNum = serverNum;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VH(LayoutInflater.from(context).inflate(R.layout.chatting_list_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull Chatting_RecycleAdapter.VH holder, int position) {
        holder.chatnic.setText(items.get(position)+"님의 채팅이 도착했습니다.");

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class VH extends RecyclerView.ViewHolder{

        TextView chatnic;
        TextView chatdate;
        CircleImageView chatprofile;

        public VH(@NonNull View itemView) {
            super(itemView);
            chatnic = itemView.findViewById(R.id.chat_othernick);
            chatdate= itemView.findViewById(R.id.chat_date);
            chatprofile=itemView.findViewById(R.id.chat_profile);

            chatnic.setText("");
//            chatdate.setText("");


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, Chatting.class);
                    int pos = getAdapterPosition();
                    if (serverNum.get(pos) == 1) {
                        intent.putExtra("server", G.nickname + "&&" + items.get(pos));
                        //Toast.makeText(context, "if o", Toast.LENGTH_SHORT).show();

                    } else {
                        intent.putExtra("server", items.get(pos) + "&&" + G.nickname);
//                        Toast.makeText(context, "if x", Toast.LENGTH_SHORT).show();
                    }

                    context.startActivity(intent);

                }
            });
        }


    }
}
