package com.siddiqui.sqlitedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button btn_add, btn_viewAll, btn_delete;
    private EditText et_name,et_age, et_search;
    private Switch sw_activeCustomer;
    private ListView lv_customerList;
    ArrayAdapter customerArrayAdapter;
    DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_add = findViewById(R.id.btn_add);
        btn_viewAll=findViewById(R.id.btn_viewall);
        et_name=findViewById(R.id.et_name);
        et_age = findViewById(R.id.editText2);
        sw_activeCustomer = findViewById(R.id.switch1);
        lv_customerList = findViewById(R.id.lv_customerlist);
        btn_delete = findViewById(R.id.deleteButton);
        et_search = findViewById(R.id.et_search);

        dataBaseHelper = new DataBaseHelper(MainActivity.this);

        showCustomersOnListView(dataBaseHelper);

        //button listeners
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomerModel customerModel;
                try {
                    customerModel = new CustomerModel(-1, et_name.getText().toString(),
                            Integer.parseInt(et_age.getText().toString()), sw_activeCustomer.isChecked());
                    Toast.makeText(MainActivity.this, customerModel.toString(), Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Error creating customer", Toast.LENGTH_SHORT).show();
                    customerModel = new CustomerModel(-1,"error",0,false);
                }

                DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);
                boolean success = dataBaseHelper.addOne(customerModel);
                Toast.makeText(MainActivity.this,"Success",Toast.LENGTH_SHORT).show();
                showCustomersOnListView(dataBaseHelper);
            }
        });

        btn_viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataBaseHelper = new DataBaseHelper(MainActivity.this);
//                Toast.makeText(MainActivity.this,everyone.toString(),Toast.LENGTH_SHORT).show();

                showCustomersOnListView(dataBaseHelper);
            }
        });
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"Select the record you want to delete",
                        Toast.LENGTH_SHORT).show();
                lv_customerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        CustomerModel clickedCustomer = (CustomerModel) parent.getItemAtPosition(position);
                        //parent is the list view, position is the part of list view that was clicked
                        dataBaseHelper.deleteOne(clickedCustomer);
                        showCustomersOnListView(dataBaseHelper);
                        Toast.makeText(MainActivity.this,"Deleted "+clickedCustomer.toString(),
                                Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                System.out.println("before text changed");
                System.out.println("before text: " +s);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                System.out.println("on text changed");
                System.out.println("After text "+s);
                String searchTerm = s+"";
                showCustomersOnListView(dataBaseHelper, searchTerm);
                Toast.makeText(MainActivity.this, "new search query generated",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void afterTextChanged(Editable s) {
                System.out.println("after text changed");
            }
        });
    }

    private void showCustomersOnListView(DataBaseHelper dataBaseHelper2) {
        customerArrayAdapter = new ArrayAdapter<CustomerModel>(MainActivity.this,
                android.R.layout.simple_list_item_1, dataBaseHelper2.getEverone());
        lv_customerList.setAdapter(customerArrayAdapter);
    }
    private void showCustomersOnListView(DataBaseHelper dataBaseHelper2, String sum) {
        customerArrayAdapter = new ArrayAdapter<CustomerModel>(MainActivity.this,
                android.R.layout.simple_list_item_1, dataBaseHelper2.getEverone(sum));
        lv_customerList.setAdapter(customerArrayAdapter);
    }
}
