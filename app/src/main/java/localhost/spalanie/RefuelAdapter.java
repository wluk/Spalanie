package localhost.spalanie;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;


class RefuelAdapter extends BaseAdapter {

    private final LayoutInflater mInflater;
    private final ArrayList<Refuel> mDataSource;

    public RefuelAdapter(Context context, ArrayList<Refuel> items) {
        mDataSource = items;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
        int refuel_id = mDataSource.get(position).getId();
        return refuel_id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View rowView = mInflater.inflate(R.layout.item_refule, parent, false);

        TextView DateRefuelText = (TextView) rowView.findViewById(R.id.RefuelDate);
        TextView LitersRefuelText = (TextView) rowView.findViewById(R.id.RefuelLiters);
        TextView CombustionRefuelText = (TextView) rowView.findViewById(R.id.RefuelCombustion);
        TextView IdRefuelText = (TextView) rowView.findViewById(R.id.RefuelId);
        ImageView IconTank = (ImageView) rowView.findViewById(R.id.iconTank);


        Refuel singleRefuel = (Refuel) getItem(position);

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String format = formatter.format(singleRefuel.getDate());
        DateRefuelText.setText(format);
        IdRefuelText.setText("#" + singleRefuel.getId());

        LitersRefuelText.setText(singleRefuel.getSubBilling().toString());

        CombustionRefuelText.setText(singleRefuel.getCombustion().toString());

        if ((position % 2) == 0) {
            IconTank.setImageResource(R.drawable.refule_blue);
        } else {
            IconTank.setImageResource(R.drawable.refule_green);
        }

        return rowView;
    }
}
