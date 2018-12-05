package main;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class FileDownloader {

	final static int size = 1024;

	public void fileUrlReadAndDownload(String fileAddress, String localFileName, String downloadDir) {

		OutputStream outStream = null;
		URLConnection uCon = null;

		InputStream is = null;
		try {

			// System.out.println("-------Download Start------");

			URL Url;
			byte[] buf;
			int byteRead;
			int byteWritten = 0;
			Url = new URL(fileAddress);
			outStream = new BufferedOutputStream(new FileOutputStream(downloadDir + "/" + localFileName));

			uCon = Url.openConnection();
			is = uCon.getInputStream();
			buf = new byte[size];
			while ((byteRead = is.read(buf)) != -1) {
				outStream.write(buf, 0, byteRead);
				byteWritten += byteRead;
			}

			/*System.out.println("Download Successfully.");
			System.out.println("File name : " + localFileName);
			System.out.println("of bytes  : " + byteWritten);
			System.out.println("-------Download End--------");*/

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
				outStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void fileUrlDownload(String fileAddress, String downloadDir, String filename) {

		int slashIndex = fileAddress.lastIndexOf('/');
		int periodIndex = fileAddress.lastIndexOf('.');

		// Get File Name through File Address
		String fileName = filename;

		if (periodIndex >= 1 && slashIndex >= 0 && slashIndex < fileAddress.length() - 1) {
			fileUrlReadAndDownload(fileAddress, fileName, downloadDir);
		} else {
			System.err.println("path or file name NG.");
		}
	}

}
