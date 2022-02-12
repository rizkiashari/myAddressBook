package com.rizkiashari.myaddressbook.Adapter;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.rizkiashari.myaddressbook.Model.DataEmployeeEntity;
import com.rizkiashari.myaddressbook.R;
import com.rizkiashari.myaddressbook.databinding.ItemAddressBinding;

import java.util.List;

public class AdapterAddress extends RecyclerView.Adapter<AdapterAddress.ViewHolder> {

    private List<DataEmployeeEntity> itemList;
    private List<DataEmployeeEntity> itemSelected;

    public AdapterAddress(List<DataEmployeeEntity> item){
        this.itemList = item;
        this.itemSelected = item;
    }

    public interface OnItemClickCallback {
        void onItemClicked(int id);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private  final ItemAddressBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ItemAddressBinding.bind(itemView);
        }
    }

    @NonNull
    @Override
    public AdapterAddress.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_address,parent, false);
        return new ViewHolder(view) ;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterAddress.ViewHolder holder, int position) {
        final DataEmployeeEntity item = itemSelected.get(position);
        holder.binding.nameAddressEmployee.setText(item.getName());
        holder.binding.cityAddressEmployee.setText(item.getCity());
        Glide.with(holder.itemView.getContext()).load(item.getPicture()).into(holder.binding.pictureAddressEmployee);
        holder.binding.btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goCall = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + item.getPhone()));
                holder.itemView.getContext().startActivity(goCall);
            }
        });
        holder.binding.btnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goEmail = new Intent(Intent.ACTION_SENDTO);
                goEmail.setData(Uri.parse("mailto:" + item.getEmail()));
                holder.itemView.getContext().startActivity(Intent.createChooser(goEmail, "Send Email"));
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemSelected.size();
    }
}
