package com.rizkiashari.myaddressbook.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.rizkiashari.myaddressbook.Adapter.AdapterList;
import com.rizkiashari.myaddressbook.Model.DataEmployees;
import com.rizkiashari.myaddressbook.Model.ResponseModel;
import com.rizkiashari.myaddressbook.Network.ApiService;
import com.rizkiashari.myaddressbook.R;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.rizkiashari.myaddressbook.databinding.ActivityMainBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Main extends AppCompatActivity implements AdapterList.OnItemClickCallback {

    private ActivityMainBinding binding;
    private AdapterList adapterList;
    private List<DataEmployees> listEmployee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getDataEmployee();
        binding.searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener(){
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                adapterList.getFilter().filter(newText);
                return false;
            }
        });
        binding.btnAllBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goAddress = new Intent(Main.this, AddressBook.class);
                startActivity(goAddress);
            }
        });
    }

    private void getDataEmployee() {
        ApiService.apiCall().getEmplpyeeList("2301900744", "Rizki Ashari").enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                if(response.body() != null){

                    if(response.isSuccessful()){
                        binding.rvEmployee.setVisibility(View.VISIBLE);
                        binding.progressMain.setVisibility(View.GONE);
                    }
                    if(response.body().getStatusCode() == 200){
                        listEmployee = response.body().getEmployees();
                        initRvData();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(Main.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initRvData() {
        adapterList = new AdapterList(listEmployee, this);
        binding.rvEmployee.setHasFixedSize(true);
        binding.rvEmployee.setLayoutManager(new LinearLayoutManager(Main.this));
        binding.rvEmployee.setAdapter(adapterList);
    }

    @Override
    public void onItemClicked(int id) {
        Intent goDetailEmployee = new Intent(Main.this, DetailEmployee.class);
        goDetailEmployee.putExtra("id", id);

        startActivity(goDetailEmployee);
    }
}