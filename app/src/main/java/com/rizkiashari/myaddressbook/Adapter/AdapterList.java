package com.rizkiashari.myaddressbook.Adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.rizkiashari.myaddressbook.Model.DataEmployees;
import com.rizkiashari.myaddressbook.R;
import com.rizkiashari.myaddressbook.databinding.ItemEmployeeBinding;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AdapterList extends RecyclerView.Adapter<AdapterList.ViewHolder> implements Filterable {

    private List<DataEmployees> dataEmployeesList;
    private List<DataEmployees> itemSelected;
    private final OnItemClickCallback onItemClickCallback;

    public interface OnItemClickCallback {
        void onItemClicked(int id);
    }

    public AdapterList(List<DataEmployees> item, OnItemClickCallback onItemClickCallback){
        this.onItemClickCallback = onItemClickCallback;
        this.dataEmployeesList = item;
        this.itemSelected = item;
    }

    public static class ViewHolder extends  RecyclerView.ViewHolder{
        private final ItemEmployeeBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ItemEmployeeBinding.bind(itemView);
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String constraintStr = constraint.toString();
                if(constraintStr.isEmpty()){
                    itemSelected = dataEmployeesList;
                }else{
                    List<DataEmployees> dataResult = new ArrayList<>();
                    for (DataEmployees dataEmployees : dataEmployeesList){
                        String name = dataEmployees.getName().getFirst() + " " + dataEmployees.getName().getLast();
                        if (name.toLowerCase(Locale.ROOT).trim().contains(constraint.toString().toLowerCase(Locale.ROOT).trim())) {
                            dataResult.add(dataEmployees);
                        }
                    }
                    itemSelected = dataResult;
                }
                FilterResults results = new FilterResults();
                results.values = itemSelected;
                return results;
            }

            @SuppressLint("NotifyDataSetChanged")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                itemSelected = (List<DataEmployees>) results.values;
                notifyItemChanged(0, itemSelected.size());
                notifyDataSetChanged();
            }
        };
    }

    @NonNull
    @Override
    public AdapterList.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_employee, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterList.ViewHolder holder, int position) {
        final DataEmployees item = itemSelected.get(position);

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = null;

        Locale locale = new Locale("en", "EN");
        SimpleDateFormat format = new SimpleDateFormat("dd-MMMM-yyyy", locale);

        String currDate = item.getRegistered().getDate();
        String[] splitDate = currDate.split("T");
        String dateRegistered = splitDate[0];

        String tempDate = dateRegistered;

        try {
            date = dateFormat.parse(tempDate);
        }catch (ParseException e){
            e.printStackTrace();
        }

        String dateFormatResult = format.format(date.getTime());
        String [] memberSince = dateFormatResult.split("-");
        String month = memberSince[1];
        String year = memberSince[2];

        holder.binding.nameEmployee.setText("Name: "+item.getName().getFirst() + " " + item.getName().getLast());
        holder.binding.cityEmployee.setText("City: "+item.getLocation().getCity());
        holder.binding.phoneEmployee.setText("Phone" +item.getPhone());
        holder.binding.dateEmployee.setText("Member Since: "+month + " " + year);
        Glide.with(holder.itemView.getContext())
                .load(item.getPicture().getThumbnail())
                .into(holder.binding.pictureEmployee);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallback.onItemClicked(item.getEmployeeId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemSelected.size();
    }
}
