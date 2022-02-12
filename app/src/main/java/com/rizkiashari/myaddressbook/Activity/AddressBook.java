package com.rizkiashari.myaddressbook.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import com.rizkiashari.myaddressbook.Adapter.AdapterAddress;
import com.rizkiashari.myaddressbook.Model.DataEmployeeEntity;
import com.rizkiashari.myaddressbook.R;
import com.rizkiashari.myaddressbook.databinding.ActivityAddressBookBinding;

import java.util.ArrayList;
import java.util.List;

public class AddressBook extends AppCompatActivity {

    private ActivityAddressBookBinding binding;

    Integer id;
    String name, city, phone, email, picture;

    private List<DataEmployeeEntity> itemEmployee;
    private AdapterAddress adapterAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddressBookBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.arrowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        itemEmployee = new ArrayList<>();
        binding.rvEmployee.setLayoutManager(new LinearLayoutManager(this));
        adapterAddress = new AdapterAddress(itemEmployee);
        binding.rvEmployee.setAdapter(adapterAddress);

        getDataAddress();
    }

    private void getDataAddress() {
        try {
            SQLiteDatabase db = this.openOrCreateDatabase("AddressBook", MODE_PRIVATE, null);

            Cursor cursor = db.rawQuery("SELECT * FROM addressBook", null);
            Integer idDataIdx = cursor.getColumnIndex("id");
            Integer nameDataIdx = cursor.getColumnIndex("name");
            Integer cityDataIdx = cursor.getColumnIndex("city");
            Integer phoneDataIdx = cursor.getColumnIndex("phone");
            Integer emailDataIdx = cursor.getColumnIndex("email");
            Integer pictureDataIdx = cursor.getColumnIndex("picture");

            while (cursor.moveToNext()){
                id = cursor.getInt(idDataIdx);
                name = cursor.getString(nameDataIdx);
                city = cursor.getString(cityDataIdx);
                phone = cursor.getString(phoneDataIdx);
                email = cursor.getString(emailDataIdx);
                picture = cursor.getString(pictureDataIdx);

                DataEmployeeEntity employeeEntity = new DataEmployeeEntity(id,phone,name,city,email,picture);

                itemEmployee.add(employeeEntity);
            }
            cursor.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}