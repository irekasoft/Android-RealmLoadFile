package com.irekasoft.realmreadfile;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;


public class MainActivity extends AppCompatActivity {



  private MyListAdapter mAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    Log.i("ast", "getAssets " + getAssets());

    // SETUP REALM
    File file = new File(getAssets() + "/");
    RealmConfiguration config = new RealmConfiguration.Builder(this).name("Test.realm").build();
    Realm realm = Realm.getInstance(config);

    RealmResults<RealmTestClass0> results = realm.where(RealmTestClass0.class).findAll();
    Log.i("results", "aa");

    for (RealmTestClass0 c : results) {
      Log.i("RealmTestClass0", "aa");

    }
    ArrayList<RealmTestClass0> items = new ArrayList<>();

    // GET LIST VIEW AND SET ADAPTER
    ListView listView = (ListView) findViewById(R.id.listView);
    mAdapter = new MyListAdapter(items, this);
//    listView.setAdapter(mAdapter);

    // ADD TAP LISTENER
    AdapterView.OnItemClickListener mMessageClickedHandler = new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ((TextView) view).setText("selected");
      }
    };
    listView.setOnItemClickListener(mMessageClickedHandler);

  }

  public class MyListAdapter extends BaseAdapter {

    private ArrayList<RealmTestClass0> items;
    private Context mContext;

    public MyListAdapter(ArrayList<RealmTestClass0> persons, Context context) {
      this.items = items;
      this.mContext = context;
    }

    @Override
    public int getCount() {
      return this.items.size();
    }

    @Override
    public Object getItem(int position) {
      return items.get(position);
    }

    @Override
    public long getItemId(int position) {
      return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

      RealmTestClass0 p = (RealmTestClass0) this.getItem(position);

      ViewHolder holder;

      if (convertView == null) {
        holder = new ViewHolder();
        convertView = LayoutInflater.from(mContext).inflate(R.layout.list_cell, parent, false);
        holder.name = (TextView) convertView.findViewById(R.id.mMainLabel);
        holder.city = (TextView) convertView.findViewById(R.id.mCityLabel);
        convertView.setTag(holder);
      } else
        holder = (ViewHolder) convertView.getTag();
      holder.name.setText(p.getStringValue());
      holder.city.setText(String.valueOf(p.getIntegerValue()));

      return convertView;

    }

    public void notifyDataSetChanged() {

    }

    private class ViewHolder {
      TextView name;
      TextView city;

    }
  }
}
