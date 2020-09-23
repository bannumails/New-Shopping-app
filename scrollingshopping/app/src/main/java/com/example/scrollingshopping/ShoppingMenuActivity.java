package com.example.scrollingshopping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ShoppingMenuActivity extends AppCompatActivity {

    private ArrayAdapter shopDataAdapter;
    private static final String ShoppingMenuActivity= "ShoppingMenuActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_menu);

        ListView listView = findViewById(R.id.listView);
        List<ShopData> list = getShopData();

        shopDataAdapter = new ShopDataAdapter(this, R.layout.shop_item, list);
        listView.setAdapter(shopDataAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d(ShoppingMenuActivity, "Inside onItemClick"+i);
                Toast.makeText(ShoppingMenuActivity.this,"Trying to buy "+ list.get(i).getName(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ShoppingMenuActivity.this, PurchaseActivity.class);
                intent.putExtra("name",list.get(i).getName());
                intent.putExtra("desc", list.get(i).getDescription());
                intent.putExtra("cost", list.get(i).getCost());
                intent.putExtra("image",list.get(i).getImageResource());
                startActivity(intent);
            }
        });

        EditText editText = findViewById(R.id.searchView);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                ShoppingMenuActivity.this.shopDataAdapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        Intent intent = getIntent();
        String action = intent.getAction();
        if("MainMenuSuccess".equalsIgnoreCase(action)){
            //TODO Deduct money

        }
    }
    
    private List<ShopData> getShopData(){
        
        ShopData data[] = {
                new ShopData("Bone", R.drawable.bone, "Good chew toy.", 1),
                new ShopData("Carrot", R.drawable.carrot, "Good chew carrot.", 1),
                new ShopData("Dog", R.drawable.dog, "Chews toy Dog.", 2),
                new ShopData("Flame", R.drawable.flame, "It burns....", 1),
                new ShopData("Grapes", R.drawable.grapes, "Your eat them.", 1),
                new ShopData("House", R.drawable.house, "As opposed to home.", 100),
                new ShopData("Lamp", R.drawable.lamp, "It lights....", 2),
                new ShopData("Mouse", R.drawable.mouse, "Not a rat...!", 1),
                new ShopData("Nail", R.drawable.nail, "Hammer required.", 1),
                new ShopData("Penguin", R.drawable.penguin, "Find Batman.", 10),
                new ShopData("Rocks", R.drawable.rocks, "Rolls Rocks.", 1),
                new ShopData("Star", R.drawable.star, "Like the sun but farther away.", 25),
                new ShopData("Toad", R.drawable.toad, "Like a frog.", 1),
                new ShopData("Van", R.drawable.van, "Has four wheels.", 10),
                new ShopData("Wheat", R.drawable.wheat, "Some breads have it.", 1),
                new ShopData("Yak", R.drawable.yak, "Yakity Yak Yak.", 15),
        };
        List<ShopData> shopDataList = new ArrayList<>();
        for (ShopData shopData : data){
            shopDataList.add(shopData);
        }
        return shopDataList;
    }

}