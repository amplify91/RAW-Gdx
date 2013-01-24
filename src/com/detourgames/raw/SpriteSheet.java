package com.detourgames.raw;

import com.badlogic.gdx.graphics.Texture;

public class SpriteSheet {
	
	private Texture mTexture;
	private float[][] mFrames;
	
	public SpriteSheet(Texture spriteSheet, int sections, int[] linesInSection, int[] framesInLine, int[] frameWidths, int[] frameHeights){
		mTexture = spriteSheet;
		mFrames = createFrames(sections, linesInSection, framesInLine, frameWidths, frameHeights);
		
	}
	
	private float[][] createFrames(int sections, int[] linesInSection, int[] framesInLine, int[] frameWidths, int[] frameHeights){
		
		/**
		 * Sections are areas on the sprite sheet that contain only one size (or resolution) of sprite.
		 * linesInSection is the number of rows in each section.
		 * framesInLine is the number of frames in each row on each line.
		 * frameWidths and frameHeights are the sizes of all the sprite in each section.
		 * If this function returns null, something was improper about the parameters given.
		 */
		
		int numberOfFrames = 0;
		int lines = 0;
		for(int i = 0;i<linesInSection.length;i++){
			lines += linesInSection[i];
		}
		if(sections!=linesInSection.length || lines!= framesInLine.length || frameWidths.length != sections || frameHeights.length != sections){
			//Log.i("Texture", "Improper Texture. Check constructor parameters.");
			return null;
		}
		for(int i = 0;i<framesInLine.length;i++){
			numberOfFrames += framesInLine[i];
		}
		
		int x = 0;
		int y = 0;
		float[][] f = new float[numberOfFrames][4];
		int frameNum = 0;
		int line = 0;
		
		for(int i=0;i<sections;i++){
			for(int i2=0;i2<linesInSection[i];i2++){
				x = 0;
				for(int frame=0;frame<framesInLine[line];frame++){
					x = frame * frameWidths[i];
					f[frameNum] = createFrameUV(frameWidths[i],frameHeights[i],x,y);
					frameNum++;
				}
				y += frameHeights[i];
				line++;
			}
		}
		
		return f;
	}

	private float[] createFrameUV(int frameWidth, int frameHeight, int x, int y){
		
		float[] uv = new float[4];
		
		float width = (float)frameWidth / (float)mTexture.getWidth();
		float height = (float)frameHeight / (float)mTexture.getHeight();
		float u = (float)x / (float)mTexture.getWidth();
		float v = (float)y / (float)mTexture.getHeight();
		
		uv[0] = u;
		uv[1] = v;
		uv[2] = u + width;
		uv[3] = v + height;
		
		return uv;
	}
	
	public Texture getTexture(){
		return mTexture;
	}
	
	public float[][] getFrames(){
		return mFrames;
	}
	
}
