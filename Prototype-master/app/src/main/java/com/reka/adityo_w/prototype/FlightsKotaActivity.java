package com.reka.adityo_w.prototype;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


import java.util.ArrayList;
import java.util.List;


public class FlightsKotaActivity extends ActionBarActivity{

    FrameLayout daftarBandara;
    ViewStub viewStub;
    final List<String> bandaraList = new ArrayList<String>();
    String displayName = "";
    ListView daftarListView;
    private static final List<String> historyData = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flights_kota);

        daftarBandara = (FrameLayout) findViewById(R.id.history_container_layout);
        EditText filterEditText = (EditText) findViewById(R.id.filter_text);
        filterEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                daftarBandara.removeAllViews();
                final List<String> tempHistoryList = new ArrayList<String>();
                tempHistoryList.addAll(bandaraList);

                for (String data : bandaraList) {
                    if (data.indexOf(s.toString()) == -1) {
                        tempHistoryList.remove(data);
                    }
                }
                viewStub = new ViewStub(FlightsKotaActivity.this, R.layout.history_schedule);

                viewStub.setOnInflateListener(new ViewStub.OnInflateListener() {

                    @Override
                    public void onInflate(ViewStub stub, View inflated) {
                        setUIElements(inflated, tempHistoryList);
                    }


                });
                daftarBandara.addView(viewStub);
                viewStub.inflate();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        setViewStub();
    };




    private void setViewStub() {
        bandaraList.add("Ambon(AMQ), Pattimura");
        bandaraList.add("Atambua(ABU), Atambua");
        bandaraList.add("Bajawa(BJW), SOA");
        bandaraList.add("Balikpapan(BPN), Sepinggan");
        bandaraList.add("Banda Aceh(BTJ), Sultan Iskandar Muda");
        bandaraList.add("Bandung(BDO), Husein Sastranegara");
        bandaraList.add("Banjarmasin(BDJ), Syamsudin Noor");
        bandaraList.add("Banyuwangi(BWX), Blimbingsari");
        bandaraList.add("Batam(BTH), Hang Nadim");
        bandaraList.add("Baubau(BUW), Baubau");
        bandaraList.add("Bengkulu(BKS), Fatmawati Soekarno");
        bandaraList.add("Berau(BEJ), Kalimarau");
        bandaraList.add("Biak(BIK), Frans Kaisiepo");
        bandaraList.add("Bima(BMU), Muhammad Salahuddin");
        bandaraList.add("Buli(WUB), Pattimura");
        bandaraList.add("Denpasar, Bali(DPS), Ngurah Rai")
        bandaraList.add("Fakfak(FKQ), Fakfak");
        bandaraList.add("Galela(GLX), Gamarmalamo");
        bandaraList.add("Gorontalo(GTO), Jalaluddin");
        bandaraList.add("Gunungsitoli(GNS), Gunung Sitoli");
        bandaraList.add("Jakarta-Cengkareng(CGK), Soekarno-Hatta");
        bandaraList.add("Jakarta-Halim(HLP), Halim Perdana Kusuma");
        bandaraList.add("Jambi(DJB), Sultan Thaha Syaifuddin");



        viewStub = new ViewStub(FlightsKotaActivity.this, R.layout.history_schedule);
        viewStub.setOnInflateListener(new ViewStub.OnInflateListener() {

            @Override
            public void onInflate(ViewStub stub, View inflated) {
                setUIElements(inflated, bandaraList);
            }
        });

        daftarBandara.addView(viewStub);
        viewStub.inflate();
    }

    private void setUIElements(View v, List<String> bandaraLists) {
        if(v != null) {
            historyData.clear();

            historyData.addAll(bandaraLists);

            daftarListView = (ListView) findViewById(R.id.history_list);
            daftarListView.setAdapter(new BeatListAdapter(this));

            registerForContextMenu(daftarListView);
        }
    }

    private static class BeatListAdapter extends BaseAdapter{

        private LayoutInflater inflater;

        static class ViewHolder{
            TextView hD;
        }

        public BeatListAdapter(Context context) {
            inflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return historyData.size();
        }

        @Override
        public Object getItem(int arg0) {
            return arg0;
        }

        @Override
        public long getItemId(int arg0) {
            return arg0;
        }

        @Override
        public View getView(int pos, View convertView, ViewGroup parent) {

            ViewHolder holder;

            if(convertView == null) {

                convertView = inflater.inflate(R.layout.history_list_view, null);
                holder = new ViewHolder();
                holder.hD = (TextView) convertView.findViewById(R.id.history_text);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.hD.setText(historyData.get(pos));
            return convertView;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_flights_kota, menu);
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
