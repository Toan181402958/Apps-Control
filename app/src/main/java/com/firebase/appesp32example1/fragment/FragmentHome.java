package com.firebase.appesp32example1.fragment;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.firebase.appesp32example1.MainActivity;
import com.firebase.appesp32example1.R;
import com.firebase.appesp32example1.fragment.api.ApiService;
import com.firebase.appesp32example1.fragment.model.Example;
import com.firebase.appesp32example1.fragment.model.Main;
import com.firebase.appesp32example1.fragment.model.Weather;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentHome extends Fragment {
    private View view;
    ApplicationListAdapter adapter;
    ImageView imgStatusWeather;
    TextView tvTemperature;
    TextView tvStatusWeather;
    TextView tvDayHome;
    private List<Weather> weatherList;
    ImageView imgLight;
    TextView tvLight, tvFan;
    ImageView imgFan;
    private boolean statusLight = true;
    private boolean statusFan = true;
    Animation animationFan;
    RelativeLayout layoutFan;

    public static final String TAG = "Log_API";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        Init();

        getData();

        readDataFromFireBase();

        imgLight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pushDataToFireBaseLight();
            }
        });

        layoutFan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                pushDataToFireBaseFan();
            }
        });

        return view;
    }

    private void Init() {
        imgStatusWeather = view.findViewById(R.id.img_status_weather);
        tvTemperature = view.findViewById(R.id.tv_temperature);
        tvStatusWeather = view.findViewById(R.id.tv_status_weather);
        tvDayHome = view.findViewById(R.id.tv_day);
        imgLight = view.findViewById(R.id.img_light_on_or_off);
        imgFan = view.findViewById(R.id.img_propellerFan);
        tvLight = view.findViewById(R.id.tv_light_on_or_off);
        tvFan = view.findViewById(R.id.tv_fan);
        layoutFan = view.findViewById(R.id.layout_fan);
    }

    private void getData(){
        ApiService.apiService.getDataWeatherFromApi().enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                Example example = response.body();
                weatherList =  example.getWeather();
                Main main = example.getMain();
                Weather weather = weatherList.get(0);
                tvTemperature.setText(main.getTemp()+"°C");
                tvStatusWeather.setText(weather.getDescription());
                Picasso.get().load("http://openweathermap.org/img/wn/"+weather.getIcon()+"@2x.png")
                        .into(imgStatusWeather);
                long day = example.getDt();
                Date date = new Date(day * 1000);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE yyy-MM-dd HH:mm:ss");
                tvDayHome.setText(simpleDateFormat.format(date));
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {

            }
        });
    }

    private void readDataFromFireBase(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRefLight = database.getReference("example");
        DatabaseReference myRefFan = database.getReference("fan");
        myRefLight.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int strLight = snapshot.getValue(Integer.class);
//                Log.e("AAA", strLight);
                if (strLight == 1){
                    imgLight.setImageResource(R.drawable.lighton);
                    tvLight.setText("Light : ON");
                    statusLight = true;
                }else {
                    imgLight.setImageResource(R.drawable.lightoff);
                    tvLight.setText("Light : OFF");
                    statusLight = false;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        myRefFan.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String strFan = snapshot.getValue(String.class);
                Log.e("Fan_Status", strFan);
                assert strFan != null;
                if (strFan.equals("on")){
                    startAnimationFan();
                    statusFan = true;
                }else{
//                    animationFan.cancel();
                    statusFan = false;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void pushDataToFireBaseLight(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRefLight = database.getReference("example");

        if (statusLight == true){
            myRefLight.setValue(0);
        } else {
            myRefLight.setValue(1);
        }
    }

    private void pushDataToFireBaseFan(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRefFan = database.getReference("fan");
        if (statusFan == true){
            myRefFan.setValue("off");
            startAnimationFan();
        } else {
            myRefFan.setValue("on");
            animationFan.cancel();
        }
    }

    private void startAnimationFan(){
        animationFan = AnimationUtils.loadAnimation(getContext(), R.anim.anim_fan);
        imgFan.startAnimation(animationFan);
    }

}
