package com.auditorias.fuerzasespeciales.webServicies;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.Window;

import com.auditorias.fuerzasespeciales.R;
import com.auditorias.fuerzasespeciales.utils.AsyncTaskGral;
import com.auditorias.fuerzasespeciales.utils.Delegate;
import com.auditorias.fuerzasespeciales.utils.Utils;

import org.apache.commons.io.IOUtils;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

public class AsyncTaskGralSinToken extends AsyncTask<String, String, String> {
    public static final String TAG = AsyncTaskGral.class.getName();

    private Delegate mDelegate;
    private Context mContext;
    private String message;
    private ProgressDialog statusDialog;
    private Dialog dialog;

    public AsyncTaskGralSinToken(Context mContext, Delegate mDelegate, String message) {
        this.mDelegate = mDelegate;
        this.mContext = mContext;
        this.message = message;
    }

    protected void onPreExecute() {
        //statusDialog = new ProgressDialog(mContext);
        /*if (message != null) {
            statusDialog.setMessage(message);
            statusDialog.setCancelable(false);
            if (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP ||
                    Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP_MR1) {
                statusDialog.setIndeterminate(true);
                Drawable drawable = new ProgressBar(mContext).getIndeterminateDrawable().mutate();
                drawable.setColorFilter(ContextCompat.getColor(mContext, R.color.green_principal),
                        PorterDuff.Mode.SRC_IN);
                statusDialog.setIndeterminateDrawable(drawable);
            } else {
                statusDialog.setIndeterminate(false);
            }
            statusDialog.show();
        }*/
        dialog = new Dialog(mContext, R.style.CustomDialogTheme);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_carga_procesos);
        dialog.show();
    }

    @Override
    protected String doInBackground(String... data) { //in the data will come 0 -> Post/Get 1 -> Url 2 -> data in Json
        publishProgress("Cargando data...");

        HttpClient client = new DefaultHttpClient();
        HttpConnectionParams.setConnectionTimeout(client.getParams(), 10000);   //Timeout Limit
        HttpResponse response;
        InputStreamReader inputStreamReader = null;
        try {
            StringBuilder url = new StringBuilder();
            //url.append(new Encryption().decryptAES(ScanConstants.ADA_PUBLIC));
            url.append(Constantes.BASE_URL_GETS);
            url.append(data[1]);
            Log.e(TAG, url.toString());

            if (Constantes.METHOD_GET.equals(data[0])) { //the link was be complete in the url in data[1]
                HttpGet get = new HttpGet(url.toString().replace(" ", "%20"));//.replace(" ","%20")
                response = client.execute(get);
            } else {
                HttpPost httpPost = new HttpPost(url.toString());
                //2 -> Data in json
                Log.d(TAG, "Data Json: " + data[2]);
                StringEntity se = new StringEntity(data[2], HTTP.UTF_8);
                httpPost.setHeader("Accept", "application/json");
                httpPost.setHeader("Content-type", "application/json");
                httpPost.setEntity(se);

                response = client.execute(httpPost);
            }

            if (response != null) {

                Header[] headers = response.getAllHeaders();

                inputStreamReader = new InputStreamReader(response.getEntity().getContent());
                BufferedReader rd = new BufferedReader(inputStreamReader);

                String val = IOUtils.toString(rd);
                if (mDelegate != null) {
                    mDelegate.executeInBackground(val, obtener(headers));
                }
                return val;
            } else {
                return null;
            }
        } catch (Exception e) {
            Log.e("AGU", "", e);
            Utils.escribirEnLog(mContext, Log.getStackTraceString(e));
            Utils.escribirLogServicio(mContext, "AsyncTaskGral", Log.getStackTraceString(e));
            return "AGU: " + e.getMessage();
        } finally {
            if (inputStreamReader != null) {
                Utils.closeInputStream(inputStreamReader);
            }
        }
        //return null;
    }

    private HashMap<String, String> convertHeadersToHashMap(Header[] headers) {
        HashMap<String, String> result = new HashMap<String, String>(headers.length);
        for (Header header : headers) {
            result.put(header.getName(), header.getValue());
        }
        return result;
    }

    private String obtener(Header[] headers) {
        String param = "";
        HashMap<String, String> result = new HashMap<String, String>(headers.length);
        for (Header header : headers) {
            if (header.getName().equals("Authorization")) {
                param = header.getValue();
            }
            //result.put(header.getName(), header.getValue());
        }
        return param;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        //if (message != null && statusDialog != null)
        //    statusDialog.dismiss();

        if (mDelegate != null) {
            Log.e(TAG, s);
            mDelegate.getDelegate(s);
        }
        if (dialog.isShowing()) {
            dialog.dismiss();
        }
    }
}
