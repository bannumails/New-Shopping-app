package com.example.scrollingshopping;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class ScrollingActivity extends AppCompatActivity {

    private Button btnLogout;
    private TextView cash;
    SearchView searchView;
    ArrayAdapter<ShopData > adapter;
    ListView listView;

    public class ShopData {

        public String name;
        public int id;
        public int imageResource;
        public String description;
        public int cost;
        public boolean purchased;

        public ShopData(String nm, int res, String desc, int cst) {

            name = nm;
            imageResource = res;
            description = desc;
            cost = cst;
            purchased = false;
        }

    }

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
    public Amount amountavailable = null;

    public class Amount{

        public int addCash(int cash){
            int newcash = cash+100;
            return newcash;
        }

        public int deductCash(int amount,int price){
            int reducedamount = amount-price;
            if(amount<0){
                throw  new RuntimeException("Insufficient cash");
            }
            return reducedamount;
        }
    }

    class CustomDialogClass extends Dialog
    {
        private final TextView balanceamount;
        public int m_id;
        public LinearLayout m_view;
        public CustomDialogClass(@NonNull Context context, TextView balanceamount)
        {
            super(context);
            this.balanceamount = balanceamount;
        }

        public CustomDialogClass(@NonNull Context context) {
            super(context);
            balanceamount = null;
        }

        //shopitem class
        @Override
        protected void onCreate(Bundle savedInstanceState)
        {

            super.onCreate(savedInstanceState);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            setContentView(R.layout.shop_dialog);
            //setContentView(R.layout.activity_scrolling);
            Button yes = this.findViewById(R.id.purchaseButton);
            yes.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {

                    for (int i=0; i<m_view.getChildCount();++i)
                    {
                        View  v=  m_view.getChildAt(i);
                        Button rem = v.findViewById(R.id.nameButton);
                        Button cst = v.findViewById(R.id.costButton);
                        String cst1 = cst.getText().toString();

                        cst1 = cst1.replace("$","");

                        String balanceamount1 = balanceamount.getText().toString();

                        int reducemoney = Integer.parseInt( balanceamount.getText().toString());
                        Integer avaialble = amountavailable.deductCash(Integer.parseInt(balanceamount1),Integer.parseInt( cst1)) ;
                        //  Intent intent = new Intent(getApplicationContext() , ScrollingActivity.class);
                       // Log.d(TAG, "onClick: ");

                        //   intent.putExtra("amount", avaialble);
                        //   startActivity(intent);
                        TextView cash = findViewById(R.id.textViewmoney);
                       // cash.setText("1001");
                        cash.setText(avaialble.toString());


                        if(rem.getText()==data[m_id].name)
                        {
                            m_view.removeViewAt(i);
                        }
                    }

                    dismiss();
                }
            });

            Button no = this.findViewById(R.id.CancelButton);
            no.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {

                    dismiss();
                }
            });

        }

        public void SetItems(int id)
        {
            m_id=id;

        }

        public void setDetails(int id)
        {

            m_id=id;

            ImageView imageview = this.findViewById(R.id.imageViewer);
            imageview.setImageResource(data[m_id].imageResource);

            TextView Dname =this.findViewById(R.id.name);
            Dname.setText(data[m_id].name);

            TextView Dcost =this.findViewById(R.id.cost);
            Dcost.setText("$"  + data[m_id].cost);

            TextView Ddesc =this.findViewById(R.id.desc);
            Ddesc.setText(data[m_id].description);



        }
    }

    public CustomDialogClass customDialog =null;


    public void ShopClicked(int id)
    {
        customDialog.show();
        customDialog.setDetails(id);

    }

    private void InitShopItems(List<ShopData> shopData) {
        LayoutInflater inflator = LayoutInflater.from(this);
        LinearLayout layout = findViewById(R.id.scrollView);
        customDialog.m_view = layout;

        for (int i = 0; i < shopData.size(); i++) {
            View myshopItem = inflator.inflate(R.layout.shop_item, null);
            final int tmp_id = i;

            View.OnClickListener click = new View.OnClickListener() {
                public int id = tmp_id;

                @Override
                public void onClick(View view) {
                    // Log.d("Dialog", "Clicked");

                    ShopClicked(id);
                }
            };


            Button nameButton = myshopItem.findViewById(R.id.nameButton);
            nameButton.setText(shopData.get(i).name);
            nameButton.setOnClickListener(click);

            Button costButton = myshopItem.findViewById(R.id.costButton);
            costButton.setText("$" + shopData.get(i).cost);
            costButton.setOnClickListener(click);

            ImageButton imageButton = myshopItem.findViewById(R.id.imageButton);
            imageButton.setImageResource(shopData.get(i).imageResource);
            imageButton.setOnClickListener(click);


            layout.addView(myshopItem);
        }
    }

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("Dialog", "Starting");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        toolBarLayout.setTitle(getTitle());
        btnLogout = findViewById(R.id.button_logout);
        customDialog = new CustomDialogClass(this);
        btnLogout.setOnClickListener(view ->{
            Toast.makeText(ScrollingActivity.this, "You have been logout", Toast.LENGTH_SHORT).show();
            finish();
        });



            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });
            customDialog = new CustomDialogClass(this, (TextView) findViewById(R.id.textViewmoney));
            amountavailable = new Amount();
            List<ShopData> list = new ArrayList<>();
            for (int i =0;i<data.length;i++){
                list.add(data[i]);
            }
            InitShopItems(list);
            cash = findViewById(R.id.textViewmoney);
            cash.setText("100");
            Button Amount = findViewById(R.id.amountbutton);
            Amount.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int newamount = amountavailable.addCash(Integer.parseInt(cash.getText().toString()));
                    cash.setText("" + newamount);
                }

            });


        adapter = new ArrayAdapter<ShopData>(this, android.R.layout.simple_list_item_1,data);
        listView.setAdapter(adapter);

        searchView = findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                System.out.println("OnSubmit "+ query);
                if(false){
                    adapter.getFilter().filter(query);
                }else{
                    Toast.makeText(ScrollingActivity.this, "No Match found",Toast.LENGTH_LONG).show();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                adapter.getFilter().filter(query);
                System.out.println("OnText Changes "+ query);
                List<ShopData> list = new ArrayList<>();
                for (int i =0;i<1;i++){
                    list.add(data[i]);
                }
                InitShopItems(list);

                return false;
            }
        });

    }

    /*public void updatecash(View view)
    {
        Intent intent = getIntent();
        String message = intent.getStringExtra("amount");

        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.textViewmoney);
        textView.setText(message);
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}