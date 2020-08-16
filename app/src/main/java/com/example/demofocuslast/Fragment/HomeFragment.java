package com.example.demofocuslast.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.demofocuslast.Interface.SendImageListenner;
import com.example.demofocuslast.Interface.SendTextListenner;
import com.example.demofocuslast.Adapter.ViewPagerAdapter;
import com.example.demofocuslast.MainActivity;
import com.example.demofocuslast.R;

public class HomeFragment extends Fragment {
    ImageView imgMenu;
    Button btnGet;
    TextView txtCentHome;
    SendImageListenner sendImageListenner;
    SendTextListenner sendTextListenner;
    private ViewPagerAdapter viewPagerAdapter;
    private ViewPager viewPager;
    private String centHomes = null;
    private String centHome2 = null;

    public HomeFragment() {
        // Required empty public constructor
    }

    public void setSendTextListenner(SendTextListenner sendTextListenner) {
        this.sendTextListenner = sendTextListenner;
    }

    public void setSendImageListenner(SendImageListenner sendImageListenner) {
        this.sendImageListenner = sendImageListenner;
    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        viewPager = view.findViewById(R.id.viewPager);
        imgMenu = view.findViewById(R.id.imgMenu);
        txtCentHome = view.findViewById(R.id.txtCentHome);
        if (centHomes != null) {
            txtCentHome.setText(centHome2);
        }
        sendTextListenner.sendCent(centHome2);
        imgMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).openDrawer();
            }
        });


        viewPagerAdapter = new ViewPagerAdapter(getContext().getApplicationContext(), sendImageListenner);


        viewPager.setPageTransformer(true, new ZoomOutPageTransformer());
        viewPager.setAdapter(viewPagerAdapter);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences sharedPreferencesCent = this.getActivity().getSharedPreferences("centData", Context.MODE_PRIVATE);

        String cent = sharedPreferencesCent.getString("keyCent", "");
        txtCentHome.setText(cent);
//        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("imageSave",Context.MODE_PRIVATE);
//        int images = sharedPreferences.getInt("keyImg", 0);
//        viewPagerAdapter.getItemPosition(images);
//        viewPager.setAdapter(viewPagerAdapter);
    }

    public class ZoomOutPageTransformer implements ViewPager.PageTransformer {
        private static final float MIN_SCALE = 0.85f;
        private static final float MIN_ALPHA = 0.5f;

        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();
            int pageHeight = view.getHeight();

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0);

            } else if (position <= 1) { // [-1,1]
                // Modify the default slide transition to shrink the page as well
                float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
                float vertMargin = pageHeight * (1 - scaleFactor) / 2;
                float horzMargin = pageWidth * (1 - scaleFactor) / 2;
                if (position < 0) {
                    view.setTranslationX(horzMargin - vertMargin / 2);
                } else {
                    view.setTranslationX(-horzMargin + vertMargin / 2);
                }

                // Scale the page down (between MIN_SCALE and 1)
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);

                // Fade the page relative to its size.
                view.setAlpha(MIN_ALPHA +
                        (scaleFactor - MIN_SCALE) /
                                (1 - MIN_SCALE) * (1 - MIN_ALPHA));

            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0);
            }
        }
    }

    public void receiveCent(String cent) {
        centHomes = cent;
        centHome2 = centHomes;
        if (txtCentHome != null) {
            txtCentHome.setText(centHomes);
        }

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }
}