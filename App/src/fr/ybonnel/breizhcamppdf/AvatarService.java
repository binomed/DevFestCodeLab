/*
 * Copyright 2013- Yan Bonnel
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package fr.ybonnel.breizhcamppdf;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import android.graphics.Bitmap;

public enum AvatarService {
	INSTANCE;

	private Map<String, Bitmap> cache = new HashMap<String, Bitmap>();

	public Bitmap getImage(URL url) throws IOException {
		if (!cache.containsKey(url.toString())) {
			// cache.put(url.toString(), Image.getInstance(url));
		}
		return cache.get(url.toString());
	}

	public Bitmap getImage(String url) throws IOException {
		if (!cache.containsKey(url)) {
			System.out.println(url);
			// Image avatar = Image.getInstance(url);
			// int height = 40;
			// float factor = ((float)height)/ avatar.getHeight();
			// int width = (int)(avatar.getWidth() * factor);
			// avatar.scaleToFit(width, height);
			// cache.put(url, avatar);
		}
		return cache.get(url);
	}

}
