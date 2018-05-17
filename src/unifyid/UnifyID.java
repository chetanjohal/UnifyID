/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unifyid;

/**
 *
 * @author johal_saab
 */
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.awt.image.BufferedImage;
import java.awt.Color;
import javax.imageio.ImageIO;
public class UnifyID {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		int height = 128;
                int width = 128;
		String temp;
                
		BufferedImage img = new BufferedImage(height, width, BufferedImage.TYPE_INT_ARGB);

		URL theUrl = new URL("https://www.random.org/integers/?num=3&min=0&max=255&col=1&base=10&format=plain&rnd=new");
		HttpURLConnection http = (HttpURLConnection)theUrl.openConnection();
		http.setRequestMethod("GET");
		BufferedReader br = new BufferedReader(new InputStreamReader(http.getInputStream()));
		ArrayList<Integer> list = new ArrayList<>();
		while ((temp = br.readLine()) != null) {
			list.add(Integer.parseInt(temp));
		}
		int count = list.size();
		System.out.println("Count: " + count);
		br.close();
		int red = 0, green = 0, blue = 0;
		count = 0;
		Color color; 
		red = list.get(count);
		count++;
		green = list.get(count); 
		count++;
		blue = list.get(count);
		for(int i = 0; i < height; i++){
			for(int j = 0; j < width; j++){
				color = new Color(red, green, blue);
				red++;
				if(red==256)
					red = 0;
				green++;
				if(green==256)
					green = 0;
				blue++;
				if(blue==256)
					blue = 0;
				
				img.setRGB(i, j, color.getRGB());
			}
		}
		
		File file = null;
		try{
			file = new File("RGBImage.png");
	        ImageIO.write(img, "png", file);
	      }catch(IOException e){
	        System.out.println("Error: " + e);
	      }
	}

}
