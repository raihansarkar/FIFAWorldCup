package smac.net.fifaworldcup;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;


/**
 * A simple {@link Fragment} subclass.
 */
public class MatchFragment extends Fragment {

    View view;
    FirebaseFirestore db;
    private Button btnNextMatch;
    private List<Item> arrayList;
    private ListView matchListview;
    private BaseAdapter adapter;
    String TAG="firebaseChack";
    private AdView mAdView;

    public MatchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_match, container, false);

        matchListview=(ListView) view.findViewById(R.id.matchListviewId);



        //==================...........Admob ............==================
        MobileAds.initialize(getActivity().getBaseContext(),"ca-app-pub-9351979862618999~6905087848");

        mAdView = view.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        //============================Admob end====================================



        db = FirebaseFirestore.getInstance();

        arrayList=new ArrayList<Item>();
        adapter=new BaseAdapter() {
            LayoutInflater inflater= (LayoutInflater) getActivity().getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);

            @Override
            public int getCount() {
                return arrayList.size();
            }

            @Override
            public Object getItem(int position) {
                return arrayList.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                if (convertView==null){
                    convertView=inflater.inflate(R.layout.match_listview_child,null);
                }
                TextView teamOne=(TextView) convertView.findViewById(R.id.teamOneId);
                TextView teamTwo=(TextView) convertView.findViewById(R.id.teamTwoId);
                TextView date=(TextView) convertView.findViewById(R.id.dateId);
                TextView time=(TextView) convertView.findViewById(R.id.timeId);
                TextView goal=(TextView) convertView.findViewById(R.id.vsId);
                ImageView flagOne=(ImageView) convertView.findViewById(R.id.flagOneId);
                ImageView flagTwo=(ImageView) convertView.findViewById(R.id.flagTwoId);

                    teamOne.setText(arrayList.get(position).getTeam1());
                    teamTwo.setText(arrayList.get(position).getTeam2());
                    date.setText(arrayList.get(position).getDate());
                    time.setText(arrayList.get(position).getTime());
                    goal.setText(arrayList.get(position).getGoal());
                    Glide.with(getActivity().getBaseContext()).load(arrayList.get(position).getFlag1()).centerCrop().fitCenter().error(R.color.colorAccent).into(flagOne);
                    Glide.with(getActivity().getBaseContext()).load(arrayList.get(position).getFlag2()).centerCrop().fitCenter().error(R.color.colorAccent).into(flagTwo);
                    //here write about flags


                return convertView;
            }
        };

        matchListview.setAdapter(adapter);
        refreshDataView();

        adapter.notifyDataSetChanged();

//        next button ======================================
        btnNextMatch=view.findViewById(R.id.nextMatchBtnId);
        btnNextMatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NextMatchFragment nextMatchFragment = new NextMatchFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.changeLayout, nextMatchFragment);
                fragmentTransaction.commit();
            }
        });


        return view;


    }
    FragmentManager manager;
    LoadingFragment fragment;
    public void refreshDataView(){
        arrayList.removeAll(arrayList);

        manager=getFragmentManager();
        fragment=new LoadingFragment();
        manager.beginTransaction().replace(R.id.matchListId,fragment).commit();

        db.collection("matches")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());

                                String flag1 = "" + document.getString("flag1");
                                String flag2 = "" + document.getString("flag2");
                                String team1 = "" + document.getString("team1");
                                String team2 = "" + document.getString("team2");
                                String date = "" + document.getString("date");
                                String time = "" + document.getString("time");
                                String goal = "" + document.getString("goal");



                                Item item = new Item(flag1,flag2,team1,team2,date,time,goal);

                                if (item.getDate().equals(getDateTime())){
                                    arrayList.add(item);
                                }
                            }


                            manager.beginTransaction().remove(fragment).commit();


                            if (arrayList.size()<=0){
                                BlankFragment blankFragment = new BlankFragment();
                                FragmentManager fragmentManager = getFragmentManager();
                                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                fragmentTransaction.replace(R.id.changeLayout, blankFragment);
                                fragmentTransaction.commit();
                            }

                            adapter.notifyDataSetChanged();
                            Log.d(TAG, "" + arrayList.toString());
                        } else {
                            Log.w(TAG, "Error getting documents.");
                        }
                    }
                });
    }

    private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd");
        Date date = new Date();
        return dateFormat.format(date);
    }

}
