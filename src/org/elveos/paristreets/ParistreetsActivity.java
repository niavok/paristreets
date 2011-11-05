package org.elveos.paristreets;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.TextView;

import com.googlecode.leptonica.android.Pix;
import com.googlecode.leptonica.android.ReadFile;
import com.googlecode.tesseract.android.TessBaseAPI;

public class ParistreetsActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView tv = new TextView(this);

		AssetManager assetManager = getAssets();
		InputStream imageStream;
		try {
			imageStream = assetManager.open("panneau_rue_paris.jpg");

			//Bitmap bitmap = BitmapFactory.decodeStream(imageStream);

			byte[] encodedData = readBytes(imageStream);
			Pix pix = ReadFile.readMem(encodedData);
			

			Log.d("ocr",Environment.getExternalStorageDirectory().getAbsolutePath());
			
			
			//int[] pixels = new int[bitmap.getWidth()* bitmap.getHeight()*3];
			
			//bitmap.getPixels(pixels, 0, 0, 0, 0, bitmap.getWidth(), bitmap.getHeight());
			
			TessBaseAPI tess = new TessBaseAPI();
			Log.d("ocr","before init");
			//boolean ok = tess.init("/android_asset/", "eng");
			boolean ok = tess.init("/mnt/sdcard/tessdata", "eng");
			
			Log.d("ocr","after init");
			
			if ( !ok ) {
				Log.e("ocr","Failed to initialize api");
				return;
			}
			
			//tess.init("/", null);
			//tess.setImage(pixels, bitmap.getWidth(), bitmap.getHeight(), 3, 3);
			tess.setImage(pix);
			tv.setText(tess.getUTF8Text());
			//ImageView iv = new ImageView(this);
			//iv.setImageBitmap(bitmap);
			setContentView(tv);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public byte[] readBytes(InputStream inputStream) throws IOException {
		  // this dynamically extends to take the bytes you read
		  ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();

		  // this is storage overwritten on each iteration with bytes
		  int bufferSize = 1024;
		  byte[] buffer = new byte[bufferSize];

		  // we need to know how may bytes were read to write them to the byteBuffer
		  int len = 0;
		  while ((len = inputStream.read(buffer)) != -1) {
		    byteBuffer.write(buffer, 0, len);
		  }

		  // and then we can return your byte array.
		  return byteBuffer.toByteArray();
		}
}