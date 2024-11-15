package com.example.lab_2.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab_2.DAO.MonhocDAO;
import com.example.lab_2.MainActivity;
import com.example.lab_2.Model.Monhoc;
import com.example.lab_2.R;

import java.util.ArrayList;



public class MonhocAdapter extends RecyclerView.Adapter<MonhocAdapter.MonHocViewHolder>{

    private Context context;

    private ArrayList<Monhoc> marraylist;

    private MonhocDAO monhocDAO;

    public MonhocAdapter(Context context, ArrayList<Monhoc> marraylist) {
        this.context = context;
        this.marraylist = marraylist;
    }

    @NonNull
    @Override
    public MonHocViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.items_monhoc,parent,false);
        return new MonHocViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MonHocViewHolder holder, int position) {
        Monhoc i = marraylist.get(position);
        monhocDAO=new MonhocDAO(context);
        holder.tvTitleItems.setText(i.getTitle());
        holder.tvDateItems.setText(i.getDate());

        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)context).delete(position);
            }
        });

        holder.cbCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (i.getStatus()==0){
                    i.setStatus(1);
                    Toast.makeText(context, "Update Status thành công!", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(context, "Update status thất bại", Toast.LENGTH_SHORT).show();
                }

                boolean check = monhocDAO.updateStatus(i);
                notifyDataSetChanged();

                if (check == true){
                    Toast.makeText(context, "Cập nhật thành công!", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(context, "Cập nhật  thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });

        holder.imgUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)context).update(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (marraylist!=null)
            return marraylist.size();
        return 0;
    }

    public static class MonHocViewHolder extends RecyclerView.ViewHolder{
        private CheckBox cbCheck;

        private TextView tvTitleItems,tvDateItems,tvContentItems,tvTypeItems;

        private ImageView imgUpdate,imgDelete;
        public MonHocViewHolder(@NonNull View itemView) {
            super(itemView);

            cbCheck = itemView.findViewById(R.id.cbcheck);
            tvTitleItems = itemView.findViewById(R.id.tvtitleitems);
            imgUpdate = itemView.findViewById(R.id.imgupdate);
            imgDelete = itemView.findViewById(R.id.imgdelete);
            tvDateItems = itemView.findViewById(R.id.tvdateitems);
        }
    }



}
