package smac.net.fifaworldcup;


import android.content.Intent;
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
public class GroupFragment extends Fragment {

    View view;
    FirebaseFirestore db;
    private List<Item> arrayList;
    private ListView groupListview;
    private BaseAdapter adapter;
    private TextView groupDetails;
    String TAG="firebaseChack";

    public GroupFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_group, container, false);

        groupListview=(ListView) view.findViewById(R.id.groupListviewId);

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
                    convertView=inflater.inflate(R.layout.group_listview_child,null);
                }
                TextView teamOne=(TextView) convertView.findViewById(R.id.teamOneId);
                TextView teamTwo=(TextView) convertView.findViewById(R.id.teamTwoId);
                TextView teamThree=(TextView) convertView.findViewById(R.id.teamThreeId);
                TextView teamFour=(TextView) convertView.findViewById(R.id.teamFourId);
                TextView group=(TextView) convertView.findViewById(R.id.groupAId);
                ImageView flagOne=(ImageView) convertView.findViewById(R.id.flagOneId);
                ImageView flagTwo=(ImageView) convertView.findViewById(R.id.flagTwoId);
                ImageView flagThree=(ImageView) convertView.findViewById(R.id.flagThreeId);
                ImageView flagFour=(ImageView) convertView.findViewById(R.id.flagFourId);
                //TODO: write variable for flag image

                teamOne.setText(arrayList.get(position).getTeam1());
                teamTwo.setText(arrayList.get(position).getTeam2());
                teamThree.setText(arrayList.get(position).getTeam3());
                teamFour.setText(arrayList.get(position).getTeam4());
                group.setText(arrayList.get(position).getGroup());
                Glide.with(getActivity().getBaseContext()).load(arrayList.get(position).getFlag1()).centerCrop().fitCenter().error(R.color.colorAccent).into(flagOne);
                Glide.with(getActivity().getBaseContext()).load(arrayList.get(position).getFlag2()).centerCrop().fitCenter().error(R.color.colorAccent).into(flagTwo);
                Glide.with(getActivity().getBaseContext()).load(arrayList.get(position).getFlag3()).centerCrop().fitCenter().error(R.color.colorAccent).into(flagThree);
                Glide.with(getActivity().getBaseContext()).load(arrayList.get(position).getFlag4()).centerCrop().fitCenter().error(R.color.colorAccent).into(flagFour);
                return convertView;
            }
        };

        groupListview.setAdapter(adapter);
        refreshDataView();

        adapter.notifyDataSetChanged();


        //groupDetails------------------------
        groupDetails=view.findViewById(R.id.groupDetailsId);

//        groupDetailsView();

        groupDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DetailsFragment detailsFragment = new DetailsFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.changeLayout, detailsFragment);
                fragmentTransaction.commit();
            }
        });

        return view;

    }

//    public void groupDetailsView(){
//        groupDetails.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                DetailsFragment detailsFragment = new DetailsFragment();
//                FragmentManager fragmentManager = getFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.fragment_group, detailsFragment);
//                fragmentTransaction.commit();
//            }
//        });
//    }

    public void refreshDataView(){
        arrayList.removeAll(arrayList);

        db.collection("groups")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());

                                String flag1 = "" + document.getString("flag1");
                                String flag2 = "" + document.getString("flag2");
                                String flag3 = "" + document.getString("flag3");
                                String flag4 = "" + document.getString("flag4");
                                String team1 = "" + document.getString("team1");
                                String team2 = "" + document.getString("team2");
                                String team3 = "" + document.getString("team3");
                                String team4 = "" + document.getString("team4");
                                String group = "" + document.getString("group");




                                Item item = new Item(flag1,flag2,flag3,flag4,team1,team2,team3,team4,group);
                                arrayList.add(item);
                            }

                            adapter.notifyDataSetChanged();
                            Log.d(TAG, "" + arrayList.toString());
                        } else {
                            Log.w(TAG, "Error getting documents.");
                        }
                    }
                });
    }

}
