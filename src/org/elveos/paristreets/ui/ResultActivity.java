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

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class ResultActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		WebView wv = new WebView(this);

		String html = "<html><body>some <b>html</b> here</body></html>";
		wv.loadData(html, "text/html", "UTF-8");
		setContentView(wv);
	}
}
