package com.detourgames.raw;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Vector;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonWriter.OutputType;
import com.badlogic.gdx.utils.OrderedMap;

public class LevelLoader {

	/*
	 * A class for accessing level files, parsing them, loading them, and
	 * returning the contents of a level to the GameManager.
	 * 
	 * It should only need a few methods: createLevel(levelnumber), //should
	 * possibly be called in the constructor getLevel(), and getLevelItem()
	 * (like getTiles() or getEnemies()).
	 */

	Level mLevel;
	SpriteFactory mSpriteFactory;
	SpriteSheet mSpriteSheet;

	int levelWidth = 0;
	int levelHeight = 0;
	int sprites = 0;

	public LevelLoader(Level level) {
		mLevel = level;
		Texture texture = new Texture(Gdx.files.internal("sprite_tiles.png"));
		mSpriteSheet = new SpriteSheet(texture, 3, new int[]{3,1,5}, new int[]{8,8,4,0,7,16,16,16,16}, new int[]{128,1024,64}, new int[]{128,320,64});
		mLevel.setSpriteSheet(mSpriteSheet);
		mSpriteFactory = new SpriteFactory(mLevel);
		// if(lvln==0){
		// createRandomTileMap(); TODO
		// }else{
		// createLevelFromFile(lvln, level);
		// mSpriteFactory.createHero(2, 3);
		// }

		// sprites += HUD.HUD_SPRITES;
	}

	public void createLevelFromFile(int ln) {
		FileHandle jsonId = Gdx.files.internal("levels/tonkatrucks.json");
		JsonReader reader=new JsonReader();
		String jsonString=jsonId.readString();
		Object o=reader.parse(jsonString);
		CreateLevelFromJsonObject((OrderedMap)o);


	}

	private FileHandle getFileHandle(int ln) {
		FileHandle levelID = null;

		if (ln == 1) {
			levelID = Gdx.files.internal("levels/tonkatrucks.json");
		}

		return levelID;
	}

	private void CreateLevelFromJsonObject(OrderedMap<String,Object> map)
	{
		Float objectWidth=(Float)map.get("width");
		Float objectHeight=(Float)map.get("height");
		Array tileSetO=(Array)map.get("tilesets");
		OrderedMap tileSetProperties=(OrderedMap)tileSetO.items[0];
		OrderedMap tileProperties=(OrderedMap)tileSetProperties.get("tileproperties");
		int levelWidth=objectWidth.intValue();
		int levelHeight=objectHeight.intValue();
		@SuppressWarnings("unchecked")
		Array layers= (Array) map.get("layers");
		OrderedMap tileLayer=(OrderedMap)layers.items[0];
		Array gridBleh=(Array)tileLayer.get("data");
		int[]gridArr=new int[gridBleh.size];
		for(int i=0;i<gridBleh.size;i++)
		{			
			Float tile=(Float)gridBleh.get(i);
			gridArr[i]=tile.intValue();		
		}
		sprites = 0;
		int i = 0;
		for(int y=levelHeight-1;y>-1;y--){
			for(int x=0;x<levelWidth;x++){
				if(gridArr[i]!=0){
					//ph_tiles[sprites] = createTile(gridArr[i], x, y, level);
					int tileNum=gridArr[i]-1;
					if(!tileProperties.containsKey(""+tileNum))
					{
						i++;
						continue;
					}
					OrderedMap tileTypeOM = (OrderedMap)tileProperties.get(""+tileNum);
					String tileTypeS = (String)tileTypeOM.get("TileType");
					int tileType=Integer.parseInt(tileTypeS);
					mSpriteFactory.createTile((float)x/2f, (float)y/2f, 0, tileType);
					sprites++;
				}
				i++;
			}
		}
		mSpriteFactory.createHero(2, 2);
	}
}
