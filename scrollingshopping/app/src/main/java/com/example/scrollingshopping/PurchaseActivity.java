package com.example.scrollingshopping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class PurchaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String desc = intent.getStringExtra("desc");
        int image = intent.getIntExtra("image", 0);
        int cost = intent.getIntExtra("cost", 0);

        ImageView imageView = findViewById(R.id.imageViewer);
        imageView.setImageResource(image);

        TextView nameView= findViewById(R.id.name);
        nameView.setText(name);
        TextView descView = findViewById(R.id.desc);
        descView.setText(desc);
        TextView costView = findViewById(R.id.cost);
        costView.setText(Integer.toString(cost));

        Button purchaseButton = findViewById(R.id.purchaseButton);
        Button cancelButton = findViewById(R.id.CancelButton);

        purchaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(PurchaseActivity.this,"Successfully bought "+ name, Toast.LENGTH_SHORT).show();
                Intent shoppingMenuIntent = new Intent(PurchaseActivity.this, ShoppingMenuActivity.class);
                shoppingMenuIntent.setAction("MainMenuSuccess");
                startActivity(intent);
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent shoppingMenuIntent = new Intent(PurchaseActivity.this, ShoppingMenuActivity.class);
                shoppingMenuIntent.setAction("MainMenuCancel");
                startActivity(intent);
            }
        });
    }
}