package com.example.roshnisoni.inscribe;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.view.ViewGroup;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class display extends AppCompatActivity {
    DatabaseHelper helper;
    private ListView lv;
    ArrayList<String> listData;
    private String keyword;
    ArrayAdapter adapter;
    ArrayList<String> search_result_arraylist;
    SearchView searchView;
    private Typeface mTypeface;
private LinearLayout ly;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        ly = (LinearLayout) findViewById(R.id.linear_layout);

        lv=(ListView)findViewById(R.id.listView1) ;
        helper=new DatabaseHelper(this);
        populateListView();
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listData) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                // Cast the list view each item as text view
                TextView item = (TextView) super.getView(position, convertView, parent);
                item.setTextColor(Color.parseColor("#000080"));

                item.setTypeface(item.getTypeface(), Typeface.BOLD);

                item.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);

                return item;
            }
        };
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapter, View view,int position,long id) {
                String selectedFromList = (String)(lv.getItemAtPosition(position));
                String con=helper.getContent(selectedFromList);
                Intent i = new Intent(display.this, DisplayContentActivity.class);
                i.putExtra("title",selectedFromList);
                i.putExtra("content",con);
                startActivity(i);

            }
            });
    }


    public void openEditor(View v){
        Intent i = new Intent(display.this, TextNotesActivity.class);
        startActivity(i);
    }

    public void  populateListView(){
        Cursor data=helper.getData();
       listData=new ArrayList<>();
        while(data.moveToNext()){
            listData.add(data.getString(1));

        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);

        MenuItem search_item = menu.findItem(R.id.mi_search);

       searchView = (SearchView) search_item.getActionView();
        searchView.setFocusable(false);
        searchView.setQueryHint("Search");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {


            @Override
            public boolean onQueryTextSubmit(String s) {
                search_result_arraylist = new ArrayList<>();
                //clear the previous data in search arraylist if exist
                search_result_arraylist.clear();

                keyword = s;
                //lv=(ListView)findViewById(R.id.listView1);
                //checking  arraylist for items containing search keyword
                for(int i =0 ;i < listData.size();i++){
                    if(listData.get(i).contains(keyword)){
                        search_result_arraylist.add(listData.get(i).toString());
                    }
                }

                adapter = new ArrayAdapter<String>(display.this,android.R.layout.simple_list_item_1,search_result_arraylist){
                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {
                        // Cast the list view each item as text view
                        TextView item = (TextView) super.getView(position, convertView, parent);
                        item.setTextColor(Color.parseColor("#000080"));

                        item.setTypeface(item.getTypeface(), Typeface.BOLD);

                        item.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);

                        return item;
                    }
                };
                lv.setAdapter(adapter);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if(searchView.getQuery().length()==0){
                    adapter = new ArrayAdapter<String>(display.this,android.R.layout.simple_list_item_1,listData){
                        @Override
                        public View getView(int position, View convertView, ViewGroup parent) {
                            // Cast the list view each item as text view
                            TextView item = (TextView) super.getView(position, convertView, parent);
                            item.setTextColor(Color.parseColor("#000080"));

                            item.setTypeface(item.getTypeface(), Typeface.BOLD);

                            item.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);

                            return item;
                        }
                    };
                    lv.setAdapter(adapter);
                }
                return false;
            }
        });


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch(item.getItemId()) {
            case R.id.editpwd:
                Intent i = new Intent(display.this, EditPassword.class);
                startActivity(i);

                return(true);}
        return super.onOptionsItemSelected(item);
    }


    @Override

    public void onBackPressed() {
        new AlertDialog.Builder(this)
             //  .setIcon(android.R.drawable.logo2.png)
                .setTitle("Exit Inscribe")
                .setMessage("Are you sure you want to exit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //super.onBackPressed();
                        //Or used finish();
                        moveTaskToBack(true);
                        display.this.finish();
                        //finish();
                    }

                })
                .setNegativeButton("No", null)
                .show();

    }

}
