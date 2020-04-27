package id.co.myproject.angkutapps_penumpang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import id.co.myproject.angkutapps_penumpang.R;
import id.co.myproject.angkutapps_penumpang.model.*;

import java.util.List;

public class PagerLoginAdapter extends PagerAdapter {

    private List<viewPagerLogin> viewPagerLogins;
    private LayoutInflater layoutInflater;
    private Context context;

    ImageView imgSwipeLogin;
    TextView titleImgSwipe, descTitleImgSwipe;

    public PagerLoginAdapter(List<viewPagerLogin> viewPagerLogins, Context context) {
        this.viewPagerLogins = viewPagerLogins;
        this.context = context;
    }

    @Override
    public int getCount() {
        return viewPagerLogins.size();
    }

    @Override
    public boolean isViewFromObject( View view, Object object) {
        return view.equals(object);
    }

    @Override
    public Object instantiateItem( ViewGroup container, int position) {
        layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.frame_viewpager_login, container, false);

        imgSwipeLogin = view.findViewById(R.id.imgSwipeLogin);
        titleImgSwipe = view.findViewById(R.id.titleImageSwipe);
        descTitleImgSwipe = view.findViewById(R.id.tvDescriptionTitle);

        imgSwipeLogin.setImageResource(viewPagerLogins.get(position).getImage());
        titleImgSwipe.setText(viewPagerLogins.get(position).getTitle());
        descTitleImgSwipe.setText(viewPagerLogins.get(position).getDesc());

        container.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
        container.removeView((View)object);
    }
}
