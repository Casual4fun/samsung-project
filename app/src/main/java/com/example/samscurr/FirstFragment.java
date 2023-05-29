package com.example.samscurr;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import com.example.samscurr.currenter.currenter;

import com.example.samscurr.databinding.FragmentFirstBinding;
import com.google.gson.Gson;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        int euro = 1;

        TextView textdollar = binding.usdtextview;
        TextView textrouble = binding.rubtextview;
        TextView textjuan = binding.cnytextview;
        TextView textlira = binding.trytextview;

        TextView setDate = binding.textviewFirst;
        currenter currs = new currenter();
        String command = "date";
        try {
            String date = currs.getCurrencyInfo(command);
            setDate.setText("Курс актуален на текушую дату: " + date);

            command = "dollar";
            date = currs.getCurrencyInfo(command);
            float num = Float.parseFloat(date);
            textdollar.setText(String.valueOf(euro*num));

            command = "rouble";
            date = currs.getCurrencyInfo(command);
            num = Float.parseFloat(date);
            textrouble.setText(String.valueOf(euro*num));

            command = "juan";
            date = currs.getCurrencyInfo(command);
            num = Float.parseFloat(date);
            textjuan.setText(String.valueOf(euro*num));

            command = "lira";
            date = currs.getCurrencyInfo(command);
            num = Float.parseFloat(date);
            textlira.setText(String.valueOf(euro*num));

        } catch (IOException e) {
            e.printStackTrace();
            setDate.setText("Курс актуален на текущую дату: НЕ УДАЛОСЬ ЗАГРУЗИТЬ ДАННЫЕ");
        }

        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = view.getId();

                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });
        binding.chechcurrency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editText = binding.euroEnter;
                String euenter = editText.getText().toString();
                if (!euenter.isEmpty())
                {
                    int euro = Integer.parseInt(euenter);
                    try {
                        String command = "dollar";
                        String info = currs.getCurrencyInfo(command);
                        float num = Float.parseFloat(info);
                        String roundf = String.format("%.1f", euro*num).replace(",", ".");
                        textdollar.setText(roundf);

                        command = "rouble";
                        info = currs.getCurrencyInfo(command);
                        num = Float.parseFloat(info);
                        roundf = String.format("%.1f", euro*num).replace(",", ".");
                        textrouble.setText(roundf);

                        command = "juan";
                        info = currs.getCurrencyInfo(command);
                        num = Float.parseFloat(info);
                        roundf = String.format("%.1f", euro*num).replace(",", ".");
                        textjuan.setText(roundf);

                        command = "lira";
                        info = currs.getCurrencyInfo(command);
                        num = Float.parseFloat(info);
                        roundf = String.format("%.1f", euro*num).replace(",", ".");
                        textlira.setText(roundf);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else
                {
                    Toast toast = Toast.makeText(getContext(), "Вы ввели что-то не так!", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}