package wqyap762.rprqs;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

/**
 * Created by wqyap762 on 30/04/16.
 */
public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    List<Menu> menu = Collections.emptyList();

    public MenuAdapter(Context context, List<Menu> menu) {
        inflater = LayoutInflater.from(context);
        this.menu = menu;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.menu_row_layout, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Menu current = menu.get(position);
        holder.icon.setImageResource(current.iconId);
        holder.title.setText(current.title);
    }

    @Override
    public int getItemCount() {
        return menu.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView icon;
        TextView title;

        public MyViewHolder(View itemView) {
            super(itemView);
            icon = (ImageView) itemView.findViewById(R.id.listIcon);
            title = (TextView) itemView.findViewById(R.id.listText) ;
        }
    }
}
