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

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsFragment extends Fragment {

    View view;
    FirebaseFirestore db;
    private TextView detailsGroup;
    private List<Item> arrayList;
    private ListView detailsListview;
    private BaseAdapter adapter;
    String TAG="firebaseChack";
    private AdView mAdView;

    public DetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_details, container, false);

        detailsListview=(ListView) view.findViewById(R.id.detailsListviewId);

        //==================...........Admob ............==================
        MobileAds.initialize(getActivity().getBaseContext(),"ca-app-pub-3940256099942544~3347511713");

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
                    convertView=inflater.inflate(R.layout.details_listview_child,null);
                }
                TextView teamOne=(TextView) convertView.findViewById(R.id.team1Id);
                TextView teamTwo=(TextView) convertView.findViewById(R.id.team2Id);
                TextView teamThree=(TextView) convertView.findViewById(R.id.team3Id);
                TextView teamFour=(TextView) convertView.findViewById(R.id.team4Id);

                TextView pl1=(TextView) convertView.findViewById(R.id.pl1Id);
                TextView pl2=(TextView) convertView.findViewById(R.id.pl2Id);
                TextView pl3=(TextView) convertView.findViewById(R.id.pl3Id);
                TextView pl4=(TextView) convertView.findViewById(R.id.pl4Id);

                TextView gd1=(TextView) convertView.findViewById(R.id.gd1Id);
                TextView gd2=(TextView) convertView.findViewById(R.id.gd2Id);
                TextView gd3=(TextView) convertView.findViewById(R.id.gd3Id);
                TextView gd4=(TextView) convertView.findViewById(R.id.gd4Id);

                TextView pst1=(TextView) convertView.findViewById(R.id.pst1Id);
                TextView pst2=(TextView) convertView.findViewById(R.id.pst2Id);
                TextView pst3=(TextView) convertView.findViewById(R.id.pst3Id);
                TextView pst4=(TextView) convertView.findViewById(R.id.pst4Id);

                TextView group=(TextView) convertView.findViewById(R.id.groupAId);

                //TODO: write variable for flag image

                teamOne.setText(arrayList.get(position).getTeam1());
                teamTwo.setText(arrayList.get(position).getTeam2());
                teamThree.setText(arrayList.get(position).getTeam3());
                teamFour.setText(arrayList.get(position).getTeam4());
                pl1.setText(arrayList.get(position).getPl1());
                pl2.setText(arrayList.get(position).getPl2());
                pl3.setText(arrayList.get(position).getPl3());
                pl4.setText(arrayList.get(position).getPl4());
                gd1.setText(arrayList.get(position).getGd1());
                gd2.setText(arrayList.get(position).getGd2());
                gd3.setText(arrayList.get(position).getGd3());
                gd4.setText(arrayList.get(position).getGd4());
                pst1.setText(arrayList.get(position).getPst1());
                pst2.setText(arrayList.get(position).getPst2());
                pst3.setText(arrayList.get(position).getPst3());
                pst4.setText(arrayList.get(position).getPst4());
                group.setText(arrayList.get(position).getGroup());

                return convertView;
            }
        };

        detailsListview.setAdapter(adapter);
        refreshDataView();

        adapter.notifyDataSetChanged();

        //groupDetails------------------------
        detailsGroup=view.findViewById(R.id.groupViewDetailsId);
        detailsGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GroupFragment groupFragment = new GroupFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.changeLayout, groupFragment);
                fragmentTransaction.commit();

            }
        });

        return view;
    }

    FragmentManager manager;
    LoadingFragment fragment;

    public void refreshDataView(){
        //arrayList.removeAll(arrayList);
        manager=getFragmentManager();
        fragment=new LoadingFragment();
        manager.beginTransaction().replace(R.id.fragment_details,fragment).commit();

        db.collection("groups")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());

                                String team1 = "" + document.getString("team1");
                                String team2 = "" + document.getString("team2");
                                String team3 = "" + document.getString("team3");
                                String team4 = "" + document.getString("team4");
                                String pl1 = "" + document.getString("pl1");
                                String pl2 = "" + document.getString("pl2");
                                String pl3 = "" + document.getString("pl3");
                                String pl4 = "" + document.getString("pl4");
                                String gd1 = "" + document.getString("gd1");
                                String gd2 = "" + document.getString("gd2");
                                String gd3 = "" + document.getString("gd3");
                                String gd4 = "" + document.getString("gd4");
                                String pst1 = "" + document.getString("pst1");
                                String pst2 = "" + document.getString("pst2");
                                String pst3 = "" + document.getString("pst3");
                                String pst4 = "" + document.getString("pst4");
                                String group = "" + document.getString("group");




                                Item item = new Item(team1,team2,team3,team4,pl1,pl2,pl3,pl4,gd1,gd2,gd3,gd4,pst1,pst2,pst3,pst4,group);
                                arrayList.add(item);
                            }

                            manager.beginTransaction().remove(fragment).commit();

                            adapter.notifyDataSetChanged();
                            Log.d(TAG, "" + arrayList.toString());
                        } else {
                            Log.w(TAG, "Error getting documents.");
                        }
                    }
                });
    }

}
