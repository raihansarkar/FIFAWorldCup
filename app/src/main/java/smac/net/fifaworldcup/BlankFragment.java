package smac.net.fifaworldcup;


import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment {


    View view;
    private Button btnNextMatch;

    //coundown=====================
    private String EVENT_DATE_TIME = "2018-12-31 10:30:00";
    private String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private LinearLayout linear_layout_1, linear_layout_2;
    private TextView tv_days, tv_hour, tv_minute, tv_second;
    private Handler handler = new Handler();
    private Runnable runnable;

   CountDownTimer countDownTimer;
   long millis=0;
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

        //coundown====================================
//        initUI();
//        countDownStart();
        tv_days=view.findViewById(R.id.tv_days);
        tv_hour=view.findViewById(R.id.tv_hour);
        tv_minute=view.findViewById(R.id.tv_minute);
        tv_second=view.findViewById(R.id.tv_second);


        String date = "2018-06-14 21:00:00";
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());


        try {

            millis = df.parse(date).getTime() - System.currentTimeMillis();

        } catch (ParseException e) {
            e.printStackTrace();
        }
        countDownTimer=new CountDownTimer(millis,1000) {
            long different=0;
            @Override
            public void onTick(long millisUntilFinished) {

                different=millisUntilFinished;
                // Log.d("rtime",String.valueOf(different));
                long secondsInMilli = 1000;
                long minutesInMilli = secondsInMilli * 60;
                long hoursInMilli = minutesInMilli * 60;
                long daysInMilli = hoursInMilli * 24;

                long elapsedDays = different / daysInMilli;
                different = different % daysInMilli;

                long elapsedHours = different / hoursInMilli;
                different = different % hoursInMilli;

                long elapsedMinutes = different / minutesInMilli;
                different = different % minutesInMilli;

                long elapsedSeconds = different / secondsInMilli;


                tv_days.setText(String.valueOf(elapsedDays));
                tv_hour.setText(String.valueOf(elapsedHours));
                tv_minute.setText(String.valueOf(elapsedMinutes));
                tv_second.setText(String.valueOf(elapsedSeconds));

            }

            @Override
            public void onFinish() {

                tv_days.setVisibility(View.GONE);
                tv_hour.setVisibility(View.GONE);
                tv_minute.setVisibility(View.GONE);
                tv_second.setVisibility(View.GONE);

                MatchFragment matchFragment = new MatchFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.changeLayout, matchFragment);
                fragmentTransaction.commit();


            }
        }.start();


        return view;
    }

}
