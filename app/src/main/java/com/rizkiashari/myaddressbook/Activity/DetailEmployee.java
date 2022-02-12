package com.rizkiashari.myaddressbook.Activity;

import androidx.appcompat.app.AppCompatActivity;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import com.rizkiashari.myaddressbook.Model.DataEmployees;
import com.rizkiashari.myaddressbook.Model.ResponseModel;
import com.rizkiashari.myaddressbook.Network.ApiService;
import com.rizkiashari.myaddressbook.R;
import com.rizkiashari.myaddressbook.databinding.ActivityDetailEmployeeBinding;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailEmployee extends AppCompatActivity {

    SQLiteDatabase database;
    List<DataEmployees> dataEmployeesItem;
    DataEmployees item;
    SupportMapFragment mapFragment;
    private ActivityDetailEmployeeBinding binding;
    Integer id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailEmployeeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        id = getIntent().getIntExtra("id", 0);

        binding.backDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        getDataEmployeeDetail();

        database = openOrCreateDatabase("AddressBook", MODE_PRIVATE, null);
        database.execSQL("CREATE TABLE IF NOT EXISTS addressBook (id INTEGER PRIMARY KEY, name VARCHAR, city VARCHAR, phone VARCHAR, email VARCHAR, picture VARCHAR)");

        binding.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDataToDB();
            }
        });
    }

    private void setAllData(DataEmployees dataEmployees){

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = null;

        Locale locale = new Locale("en", "EN");
        SimpleDateFormat format = new SimpleDateFormat("dd-MMMM-yyyy", locale);

        String currDate = dataEmployees.getRegistered().getDate();
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

        binding.detailEmployeeDate.setText("Member Since: "+ month + " "+ year);
        binding.detailEmployeeCity.setText("City: " + dataEmployees.getLocation().getCity() + ", "+ dataEmployees.getLocation().getCountry());
        binding.detailEmployeeName.setText(dataEmployees.getName().getFirst() + " " + dataEmployees.getName().getLast());
        binding.detailEmployeePhone.setText("Phone: "+ dataEmployees.getPhone());
        binding.detailEmployeeEmail.setText("Email: "+ dataEmployees.getEmail());

        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        if(mapFragment != null){
            mapFragment.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap googleMap) {
                     Double latitude = Double.parseDouble(dataEmployees.getLocation().getCoordinates().getLatitude());
                     Double longitude = Double.parseDouble(dataEmployees.getLocation().getCoordinates().getLongitude());
                    googleMap.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude)).title(dataEmployees.getLocation().getStreet().getName()));
                    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude),8));
                }
            });
        }
    }

    private void addDataToDB() {
        String name = binding.detailEmployeeName.getText().toString();
        String phone = binding.detailEmployeePhone.getText().toString();
        String email = binding.detailEmployeeName.getText().toString();
        String city = binding.detailEmployeeCity.getText().toString();
        String picture = item.getPicture().getThumbnail().toString();

        try {
            String sqlString = "insert into addressBook (name,city,phone,email,picture) VALUES (?,?,?,?,?)";
            SQLiteStatement sqLiteStatement = database.compileStatement(sqlString);
            sqLiteStatement.bindString(1, name);
            sqLiteStatement.bindString(2, city);
            sqLiteStatement.bindString(3, phone);
            sqLiteStatement.bindString(4, email);
            sqLiteStatement.bindString(5, picture);
            sqLiteStatement.execute();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private void getDataEmployeeDetail() {
        ApiService.apiCall().getDetailEmployee(id, "2301900744", "Rizki Ashari")
                .enqueue(new Callback<ResponseModel>() {
                    @Override
                    public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                        if(response.body() != null){
                            dataEmployeesItem = new ArrayList<>();
                            if(response.isSuccessful()){
                                binding.progressDetail.setVisibility(View.GONE);
                                binding.nestedScroll.setVisibility(View.VISIBLE);
                            }
                            if(response.body().getStatusCode() == 200){
                               dataEmployeesItem = response.body().getEmployees();
                               item = response.body().getEmployees().get(0);
                               setAllData(item);
                            }
                        }else{
                            Toast.makeText(DetailEmployee.this, "Data Not found",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseModel> call, Throwable t) {
                        Toast.makeText(DetailEmployee.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}