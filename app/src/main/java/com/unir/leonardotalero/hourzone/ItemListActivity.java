package com.unir.leonardotalero.hourzone;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

/**
 * An activity representing a list of Items. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link ItemDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class ItemListActivity extends AppCompatActivity {




    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;
    private boolean a;
    private String country_selected;
    private List<Country> listado;
    private static final int RESULT_SETTINGS = 1;
    private  SimpleItemRecyclerViewAdapter adaptador;
    Calendar localTime;
private Date date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);
         localTime = Calendar.getInstance(TimeZone.getDefault());

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        a = sharedPrefs.getBoolean("prefActivePreference", false);
        country_selected=sharedPrefs.getString("sync_frequency", "Colombia,Bogota");
        if(!a){
            listado=new ArrayList<Country>();
            for (Country item: CountryContent.ITEMS){
                listado.add(item);

            }


        }else{
            if(country_selected.equals("-1")){
                listado=CountryContent.ITEMS;
            }else{

                Country country = CountryContent.ITEM_MAP.get(country_selected);
                listado=new ArrayList<Country>();
                listado.add(country);
            }


        }







        updateTextView();






        View recyclerView = findViewById(R.id.item_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);





        if (findViewById(R.id.item_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }

        Thread t = new Thread() {

            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                updateTextView();
                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        };

        t.start();

    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
         adaptador = new SimpleItemRecyclerViewAdapter(listado);
        recyclerView.setAdapter(adaptador);

    }

    public class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final List<Country> mValues;

        public SimpleItemRecyclerViewAdapter(List<Country> items) {
            mValues = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.mItem = mValues.get(position);
            holder.mIdView.setText(mValues.get(position).getName());
          //  holder.mTexttimeView.setText("test");
            //holder.mContentView.setText(mValues.get(position).getTimezone().getDisplayName());
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            //  TimeZone tz = mValues.get(position).getTimezone();
               Calendar calendar = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
                SimpleDateFormat formatter_= new SimpleDateFormat("MMMM dd yyyy HH:mm:ss z");
                TimeZone tzo= mValues.get(position).getTimezone();
                formatter_.setTimeZone(tzo);
                date=calendar.getTime();
                String newtime=formatter_.format(date);
                Log.i(tzo.getDisplayName(), newtime);

               //
               holder.mTexttimeView.setText(newtime);
               // holder.mTextClockView.setTimeZone(tzo.toString());
               // CharSequence hora = holder.mTextClockView.getText();
               // holder.mTextClockView.
               // Log.i("hora",hora.toString());
                // holder.mTextclockView.setText(currentDate.toString());
               // holder.mTextclockView.setTextAppearance(3);
                //holder.mTextclockView.setTimeZone(mValues.get(position).getTimezone().toString());
               // String tz=holder.mTextclockView.getTimeZone();
               // Log.i("czc","_"+tz+"_"+mValues.get(position).getTimezone().toString());
            }

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mTwoPane) {
                        Bundle arguments = new Bundle();
                        arguments.putString(ItemDetailFragment.ARG_ITEM_ID, holder.mItem.getName());
                        ItemDetailFragment fragment = new ItemDetailFragment();
                        fragment.setArguments(arguments);
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.item_detail_container, fragment)
                                .commit();
                    } else {
                        Context context = v.getContext();
                        Intent intent = new Intent(context, ItemDetailActivity.class);
                        intent.putExtra(ItemDetailFragment.ARG_ITEM_ID, holder.mItem.getName());

                        context.startActivity(intent);
                    }
                }
            });
        }


        @Override
        public int getItemCount() {
            return listado.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public final TextView mIdView;
            public final TextView mTexttimeView;
            //public final TextClock mTextClockView;
            public Country mItem;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                mIdView = (TextView) view.findViewById(R.id.id);
                //mContentView = (TextView) view.findViewById(R.id.content);
                mTexttimeView = (TextView) view.findViewById(R.id.texttime);
                //mTextClockView= (TextClock) view.findViewById(R.id.textclock);

            }

            @Override
            public String toString() {
                return super.toString() + " '" + "'";
            }
        }
    }

    private void updateTextView() {
        //adaptador=new SimpleItemRecyclerViewAdapter(listado);
       // adaptador.notifyDataSetChanged();
        // notifyDataSetChanged();
     //   String time = "hh:mm"; // 12:00


        View recyclerView = findViewById(R.id.item_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);


        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        // Handle item selection
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            case R.id.action_settings:
                botonSettingsActividadClick();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    public void botonSettingsActividadClick()
    {
        Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
        startActivityForResult(intent, RESULT_SETTINGS);
        //startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case RESULT_SETTINGS:
                Log.i("back of activity", "This is my message back of activity settings");
                break;

        }

    }
}
