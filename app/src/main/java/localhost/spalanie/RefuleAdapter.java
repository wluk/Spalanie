package localhost.spalanie;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by wluka on 13.06.2017.
 */

public class RefuleAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<Refule> mDataSource;

    public RefuleAdapter(Context context, ArrayList<Refule> items) {
        mContext = context;
        mDataSource = items;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mDataSource.size();
    }

    @Override
    public Object getItem(int position) {
        return mDataSource.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View rowView = mInflater.inflate(R.layout.item_refule, parent, false);

        TextView DateRefuleText = (TextView) rowView.findViewById(R.id.RefuleDate);
        TextView LitersRefuleText = (TextView) rowView.findViewById(R.id.RefuleLiters);
        TextView CombustionRefuleText = (TextView) rowView.findViewById(R.id.RefuleCombustion);
        ImageView IconTank = (ImageView) rowView.findViewById(R.id.iconTank);

        // 1
        Refule singleRefule = (Refule) getItem(position);
        // 2
        //SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        //String format = formatter.format(singleRefule.getDate());
        DateRefuleText.setText(singleRefule.getId().toString());

        LitersRefuleText.setText(singleRefule.getSubBilling().toString());

        CombustionRefuleText.setText(singleRefule.getCombustion().toString());

        if ((position % 2) == 0) {
            IconTank.setImageResource(R.drawable.refule_blue);
        } else {
            IconTank.setImageResource(R.drawable.refule_green);
        }

        return rowView;
    }
}
