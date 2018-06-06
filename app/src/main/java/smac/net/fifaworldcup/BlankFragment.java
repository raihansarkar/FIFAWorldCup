package smac.net.fifaworldcup;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment {


    View view;
    private Button btnNextMatch;
    public BlankFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_blank, container, false);

        btnNextMatch=view.findViewById(R.id.btnNextMatchId);
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

}
