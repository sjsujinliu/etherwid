package com.example.etherwid;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import android.hardware.Camera;

public class CameraActivity extends AppCompatActivity implements SurfaceHolder.Callback {
    private Button btnCapture;
    private SurfaceView surfaceView;

    private Camera camera;
    private SurfaceHolder surfaceHolder;
    private Camera.PictureCallback pictureCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera_activity);
        surfaceView = findViewById(R.id.surfaceView);
        btnCapture = findViewById(R.id.btnCapture);

        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(this);
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        btnCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(camera == null){
                    Log.i("", "camera is null");
                }
                camera.takePicture(null, null, pictureCallback);
            }
        });

        pictureCallback = new Camera.PictureCallback() {
            @Override
            public void onPictureTaken(byte[] bytes, Camera camera) {
                Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                Bitmap cbmp = Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(), bmp.getHeight(), null, true);

                String pathFileName = currentDateFormat();
                storePhoteToStorage(cbmp, pathFileName);

                Toast.makeText(getApplicationContext(), "Done!!", Toast.LENGTH_LONG).show();
                CameraActivity.this.camera.startPreview();
            }
        };

    }


    private String currentDateFormat() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMddYYYY_HH_mm_ss");
        String curentTime = dateFormat.format(new Date());
        return curentTime;
    }

    private void storePhoteToStorage(Bitmap cbmp, String pathFileName) {
        File outputFile = new File(Environment.getExternalStorageDirectory(),
                "/DCIM/"+"photo_"+pathFileName+".jpg");
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
            cbmp.compress((Bitmap.CompressFormat.JPEG), 100,fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        try {
            Log.i("camera:", "before opening Camera");
            camera = Camera.open();
        }catch (Exception e) {
            e.printStackTrace();
        }
        if (camera != null) {
            Log.i("camera:", "inside camera not null");
            Camera.Parameters parameters;
            Log.i("camera:", "parameters");
            parameters = camera.getParameters();
            parameters.setPreviewFrameRate(20);
            parameters.setPreviewSize(352, 288);
            camera.setParameters(parameters);
            camera.setDisplayOrientation(90);
            try {
                camera.setPreviewDisplay(surfaceHolder);
                camera.startPreview();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
     }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        camera.stopPreview();
        camera.release();
        camera = null;

    }
}

