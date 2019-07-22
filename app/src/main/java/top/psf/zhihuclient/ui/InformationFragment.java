package top.psf.zhihuclient.ui;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import top.psf.zhihuclient.R;

public class InformationFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_information, container, false);
//        SearchView searchView = view.findViewById(R.id.sv_search);
//        ViewCompat.setOnApplyWindowInsetsListener(searchView, new OnApplyWindowInsetsListener() {
//            @Override
//            public WindowInsetsCompat onApplyWindowInsets(View v, WindowInsetsCompat insets) {
//                Log.e("xxxxxxxxxxxxx", "received on insets on Search view");
//                return null;
//            }
//        });
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(getView() == null){
            return;
        }
        ViewCompat.requestApplyInsets(getView());
    }
}
