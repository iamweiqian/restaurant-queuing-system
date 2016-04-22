package wqyap762.rprqs;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by wqyap762 on 22/04/16.
 */
public class SaveSharedPreferences {
    static final String PREF_HPNO= "hpno";

    static SharedPreferences getSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void setPrefHpno(Context context, String hpno)
    {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(PREF_HPNO, hpno);
        editor.commit();
    }

    public static String getPrefHpno(Context context)
    {
        return getSharedPreferences(context).getString(PREF_HPNO, "");
    }

    public static void clearHpno(Context context)
    {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.clear(); //clear all stored data
        editor.commit();
    }
}
