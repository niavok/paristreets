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

import android.app.ProgressDialog;
import android.os.AsyncTask;

public class OCRTask extends AsyncTask<Void, Void, String> {
	private ProgressDialog dialog;
	private byte[] picture;

	public OCRTask(byte[] picture, ProgressDialog dialog) {
		this.dialog = dialog;
		this.picture = picture;
	}

	@Override
	protected void onPreExecute() {
		dialog.show();
	}

	@Override
	protected String doInBackground(Void... params) {
		// TODO: Perform OCR here
		return "plop";
	}

	@Override
	protected void onPostExecute(String result) {
		dialog.hide();
	}
}
