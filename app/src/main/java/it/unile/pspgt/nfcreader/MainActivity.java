package it.unile.pspgt.nfcreader;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class MainActivity extends Activity {
    private NfcAdapter adapter;
    private TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text=(TextView) findViewById(R.id.text);
        adapter=NfcAdapter.getDefaultAdapter(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onResume(){
        super.onResume();
        PendingIntent pending = PendingIntent.getActivity(this, 0, new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
        adapter.enableForegroundDispatch(this,pending,null,null);
    }
    public void onNewIntent(Intent i){
        String action=i.getAction();
        if (action.equals(NfcAdapter.ACTION_TAG_DISCOVERED)) {
           Log.i("TAG", "Tagletto");
            Bundle bundle=i.getExtras();
            Tag tag=bundle.getParcelable(NfcAdapter.EXTRA_TAG);

            //  Ndef ntag= Ndef.get(tag)
               /* ntag.connect();
                NdefMessage message = ntag.getNdefMessage();
                ntag.close();
                NdefRecord[] nrecord=message.getRecords();*/
                //byte[] id=nrecord[0].getId();
            text.setText(tag.getId().toString());
        }
    }
}
