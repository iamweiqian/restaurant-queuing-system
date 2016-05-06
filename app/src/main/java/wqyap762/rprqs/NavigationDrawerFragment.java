package wqyap762.rprqs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationDrawerFragment extends Fragment {

    private ActionBarDrawerToggle mDrawerToggle;

    public NavigationDrawerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
        TextView userNameText = (TextView) layout.findViewById(R.id.userNameText);
        TextView userHpnoText = (TextView) layout.findViewById(R.id.userHpnoText);
        String userName = SaveSharedPreferences.getPrefName(getActivity());
        String userHpno = SaveSharedPreferences.getPrefHpno(getActivity());
        userNameText.setText(userName);
        userHpnoText.setText("+6" + userHpno);
        RecyclerView recyclerView = (RecyclerView) layout.findViewById(R.id.drawerList);
        MenuAdapter adapter = new MenuAdapter(getActivity(), getMenu());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return layout;
    }

    public static List<Menu> getMenu() {
        List<Menu> menu = new ArrayList<>();
        int[] icons = {R.drawable.ic_action_order, R.drawable.ic_action_view, R.drawable.ic_action_track};
        String[] titles = {"Order Food", "View Order", "Track Order"};
        for (int i = 0; i < icons.length && i < titles.length; i++) {
            Menu current = new Menu();
            current.iconId = icons[i];
            current.title = titles[i];
            menu.add(current);
        }
        return menu;
    }

    public void setUp(int fragmentId, DrawerLayout drawerLayout, Toolbar toolbar) {
        View containerView = getActivity().findViewById(fragmentId);
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
            }
        };
        drawerLayout.setDrawerListener(mDrawerToggle);
        drawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });
    }
}