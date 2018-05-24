package net.superblaubeere27.whatsappcrash.whatsappcrasher;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        Button buttonShare = findViewById(R.id.shareMessage);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText message = findViewById(R.id.messageField);
                EditText charNum = findViewById(R.id.charNumField);

                if (message.getText() == null || message.getText().length() <= 0) {
                    Toast.makeText(MainActivity.this, "Please enter a message", Toast.LENGTH_SHORT).show();
                    return;

                }
                if (charNum.getText() == null || charNum.getText().length() <= 0) {
                    Toast.makeText(MainActivity.this, "Please enter a crash count", Toast.LENGTH_SHORT).show();
                    return;
                }

                StringBuilder sb = new StringBuilder(message.getText().toString());

                for (int i = 0; i < Integer.parseInt(charNum.getText().toString()); i++) {
                    sb.append("\u200E\u200F");
                }

                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("WhatsAppCrashText", sb.toString());
                clipboard.setPrimaryClip(clip);

                Toast.makeText(MainActivity.this, "Your message was copied to Clipboard", Toast.LENGTH_LONG).show();
            }
        });
        buttonShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText message = findViewById(R.id.messageField);
                EditText charNum = findViewById(R.id.charNumField);

                if (message.getText() == null || message.getText().length() <= 0) {
                    Toast.makeText(MainActivity.this, "Please enter a message", Toast.LENGTH_SHORT).show();
                    return;

                }
                if (charNum.getText() == null || charNum.getText().length() <= 0) {
                    Toast.makeText(MainActivity.this, "Please enter a crash count", Toast.LENGTH_SHORT).show();
                    return;
                }

                StringBuilder sb = new StringBuilder(message.getText().toString());

                for (int i = 0; i < Integer.parseInt(charNum.getText().toString()); i++) {
                    sb.append("\u200E\u200F");
                }

                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "WhatsAppCrash");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, sb.toString());
                startActivity(Intent.createChooser(sharingIntent, "WhatsApp crash message"));
            }
        });
    }
}
