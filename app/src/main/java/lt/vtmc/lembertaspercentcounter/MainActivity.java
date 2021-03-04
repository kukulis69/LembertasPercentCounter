package lt.vtmc.lembertaspercentcounter;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import lt.vtmc.lembertaspercentcounter.R;

public class MainActivity extends AppCompatActivity {

    EditText etAmount;
    TextView tvPercent;
    SeekBar sbPercent;
    TextView tvTip;
    TextView tvTotal;
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etAmount = findViewById(R.id.editTextEa);
        tvPercent = findViewById(R.id.textViewProc);
        sbPercent = findViewById(R.id.SeekBarProc);
        tvTip = findViewById(R.id.TextViewTips);
        tvTotal = findViewById(R.id.TextViewTotalus);
        tvResult = findViewById(R.id.result);

        sbPercent.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int percent = progress;
                tvPercent.setText(String.valueOf(percent) + "%");
                calculate();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        etAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                calculate();
            }
        });
        tvResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvResult.setText("Amount - " + etAmount);
                tvResult.setText("Tip - " + tvTip);
                tvResult.setText("Total - " + tvResult);
            }
        });
    }
    private void calculate() {
        if (etAmount.length() == 0) {
            etAmount.requestFocus();
            etAmount.setError("Enter ammount");
        } else {
            double amount = Double.parseDouble(etAmount.getText().toString());
            int percent = sbPercent.getProgress();
            double tip = amount * percent / 100.0;
            double total = amount - tip;
            tvTip.setText(String.valueOf(tip));
            tvTotal.setText(String.valueOf(total));
        }
    }

}