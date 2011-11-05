// This file is part of PariStreets.
//
// PariStreets is free software: you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// PariStreets is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.
//
// You should have received a copy of the GNU General Public License
//  along with PariStreets.  If not, see <http://www.gnu.org/licenses/>.
//
package org.elveos.paristreets.ui;

import org.elveos.paristreets.ui.R.id;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.ImageFormat;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class PariStreetsActivity extends Activity {
	protected static final String MEDIA_TYPE_IMAGE = null;
	/** Called when the activity is first created. */
	private Camera mCamera;
	private CameraPreview mPreview;
	ProgressDialog dialog;

	private PictureCallback mPicture = new PictureCallback() {
		@Override
		public void onPictureTaken(byte[] data, Camera camera) {
			Log.d("TAG_TODO", "picture taken");

			new OCRTask(data, dialog).execute();

			Intent resultIntent = new Intent(PariStreetsActivity.this,
					ResultActivity.class);

			startActivity(resultIntent);
		}
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		dialog = new ProgressDialog(PariStreetsActivity.this) {
			{
				setMessage("Analyzing image. Please wait...");
				setCancelable(false);
			}
		};

		// Create an instance of Camera
		mCamera = getCameraInstance();

		Camera.Parameters parameters = mCamera.getParameters();
		parameters.setPictureFormat(ImageFormat.JPEG);
		mCamera.setParameters(parameters);

		// Create our Preview view and set it as the content of our activity.
		mPreview = new CameraPreview(this, mCamera);
		FrameLayout preview = (FrameLayout) findViewById(id.camera_preview);
		preview.addView(mPreview);

		Button captureButton = (Button) findViewById(id.button_capture);
		captureButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// get an image from the camera
				mCamera.takePicture(null, null, mPicture);
			}
		});
	}

	/** A safe way to get an instance of the Camera object. */
	public static Camera getCameraInstance() {
		Camera c = null;
		try {
			c = Camera.open(); // attempt to get a Camera instance
		} catch (Exception e) {
			// Camera is not available (in use or does not exist)
		}
		return c; // returns null if camera is unavailable
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		mCamera.release();
	}
}