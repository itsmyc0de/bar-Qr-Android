package sudheesh.a16mb.com.m_d;


/**
 * Created by KUTTAN on 25-11-2016.
 */
/*
public class barcode extends AppCompatActivity implements ZXingScannerView.ResultHandler{

    private static final int REQUEST_CAMERA = 1;

    private ZXingScannerView zXingScannerView;

    private  static  int camid= Camera.CameraInfo.CAMERA_FACING_BACK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        zXingScannerView=new ZXingScannerView(this);
        setContentView(zXingScannerView);
        int currentapiversion=android.os.Build.VERSION.SDK_INT;

        if(currentapiversion >= android.os.Build.VERSION_CODES.M){
            if(check_Permission()){
                Toast.makeText(getApplicationContext(),"Granted",Toast.LENGTH_SHORT).show();
            }
            else {
                request_permisssion();
            }
        }

    }

    private void request_permisssion() {

        ActivityCompat.requestPermissions(this,new String[]{CAMERA},REQUEST_CAMERA);
    }

    private boolean check_Permission() {

        return (ContextCompat.checkSelfPermission(getApplicationContext(),CAMERA) == PackageManager.PERMISSION_GRANTED);


    }


public void onRequestPermissionsResult(int requestcode,String permission[],int[] grantResults){

    switch (requestcode){

        case REQUEST_CAMERA:
            if(grantResults.length > 0){

                boolean cameraAccepted=grantResults[0] == PackageManager.PERMISSION_GRANTED;

                if(cameraAccepted){
                    Toast.makeText(getApplicationContext(),"Permission Graned And accessing Camera",Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(getApplicationContext(),"Permission Denied And cannot accessing Camera",Toast.LENGTH_SHORT).show();
                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){

                        if(shouldShowRequestPermissionRationale(CAMERA)){

                            showMessageOKCANCEL("You need to Acess the Permissios",new DialogInterface.OnClickListener(){

                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){

                                       requestPermissions(new String[]{CAMERA},REQUEST_CAMERA);


                                    }
                                }
                            });

                            return;
                        }
                    }
                }
            }
            break;
    }

}

    private void showMessageOKCANCEL(String s, DialogInterface.OnClickListener onClickListener) {

        new android.support.v7.app.AlertDialog.Builder(barcode.this)
                .setMessage(s)
                .setPositiveButton("OK",onClickListener)
                .setNegativeButton("CANCEL",null)
                .create()
                .show();
  }

    @Override
    public void onResume() {
        super.onResume();

        int current_api= Build.VERSION.SDK_INT;
        if(current_api >= Build.VERSION_CODES.M){

            if(check_Permission()){

                if(zXingScannerView == null){

                    zXingScannerView=new ZXingScannerView(this);
                    setContentView(zXingScannerView);
                }

                zXingScannerView.setResultHandler(this);
                zXingScannerView.startCamera(camid);
            }else {

                request_permisssion();
            }
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        zXingScannerView.stopCamera();
        zXingScannerView=null;
    }

    @Override
    public void handleResult(final Result R_result) {

        final String result=R_result.getText();
        Log.e("QRCODESCANNER",R_result.getText());
        Log.e("QRCODESCANNER",R_result.getBarcodeFormat().toString());

        final AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Scan Results");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                zXingScannerView.resumeCameraPreview(barcode.this);

            }
            });

      builder.setNeutralButton("Visit", new DialogInterface.OnClickListener() {
                    @Override
                          public void onClick(DialogInterface dialog, int which) {
                        Intent browser_intent=new Intent(Intent.ACTION_VIEW, Uri.parse(result));
                        startActivity(browser_intent);
                    }
                });

                builder.setMessage(R_result.getText());
                AlertDialog alertDialog=builder.create();
                alertDialog.show();
            }



    }

*/



import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.zxing.Result;
import me.dm7.barcodescanner.zxing.ZXingScannerView;
import static android.Manifest.permission.CAMERA;

public class barcode extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    private static final int REQUEST_CAMERA = 1;
    private ZXingScannerView mScannerView;
    private static int camId = Camera.CameraInfo.CAMERA_FACING_BACK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mScannerView = new ZXingScannerView(this);
        setContentView(mScannerView);
        int currentapiVersion = android.os.Build.VERSION.SDK_INT;
        if (currentapiVersion >= android.os.Build.VERSION_CODES.M) {
            if (checkPermission()) {
                Toast.makeText(getApplicationContext(), "Permission already granted", Toast.LENGTH_LONG).show();
            } else {
                requestPermission();
            }
        }
    }

    private boolean checkPermission() {
        return ( ContextCompat.checkSelfPermission(getApplicationContext(), CAMERA ) == PackageManager.PERMISSION_GRANTED);
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{CAMERA}, REQUEST_CAMERA);
    }

    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CAMERA:
                if (grantResults.length > 0) {

                    boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    if (cameraAccepted){
                        Toast.makeText(getApplicationContext(), "Permission Granted, Now you can access camera", Toast.LENGTH_LONG).show();
                    }else {
                        Toast.makeText(getApplicationContext(), "Permission Denied, You cannot access and camera", Toast.LENGTH_LONG).show();
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            if (shouldShowRequestPermissionRationale(CAMERA)) {
                                showMessageOKCancel("You need to allow access to both the permissions",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                    requestPermissions(new String[]{CAMERA},
                                                            REQUEST_CAMERA);
                                                }
                                            }
                                        });
                                return;
                            }
                        }
                    }
                }
                break;
        }
    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new android.support.v7.app.AlertDialog.Builder(barcode.this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    @Override
    public void onResume() {
        super.onResume();

        int currentapiVersion = android.os.Build.VERSION.SDK_INT;
        if (currentapiVersion >= android.os.Build.VERSION_CODES.M) {
            if (checkPermission()) {
                if(mScannerView == null) {
                    mScannerView = new ZXingScannerView(this);
                    setContentView(mScannerView);
                }
                mScannerView.setResultHandler(this);
                mScannerView.startCamera(camId);
            } else {
                requestPermission();
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mScannerView.stopCamera();
        mScannerView = null;
    }

    @Override
    public void handleResult(Result rawResult) {

        final String result = rawResult.getText();
        Log.e("QRCodeScanner", rawResult.getText());
        Log.e("QRCodeScanner", rawResult.getBarcodeFormat().toString());

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Scan Result");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mScannerView.resumeCameraPreview(barcode.this);
            }
        });
        builder.setNeutralButton("Visit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(result));
                startActivity(browserIntent);
            }
        });
        builder.setMessage(rawResult.getText());
        AlertDialog alert1 = builder.create();
        alert1.show();

        Toast.makeText(getApplicationContext(),"Scan Value:"+result,Toast.LENGTH_SHORT).show();
    }}
