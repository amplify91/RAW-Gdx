package com.detourgames.raw.Menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.OrderedMap;
import com.detourgames.raw.SpriteSheet;

public class MenuLoader {

	/*
	 * A class for accessing level files, parsing them, loading them, and
	 * spawning the contents of the level with SpriteFactory.
	 * 
	 */

	MenuLevel mLevel;
	MenuSpriteFactory mSpriteFactory;
	SpriteSheet mSpriteSheet;

	int levelWidth = 0;
	int levelHeight = 0;
	
	public final static float MAP_SCALE = 2.0f;// Tiles/meter

	public MenuLoader(MenuLevel level) {
		mLevel = level;
		Texture texture = new Texture(Gdx.files.internal("sprite_sheet_w1.png"));
		//mSpriteSheet = new SpriteSheet(texture, 3, new int[]{3,1,5}, new int[]{8,8,4,0,7,16,16,16,16}, new int[]{128,1024,64}, new int[]{128,320,64});
		mSpriteSheet = new SpriteSheet(texture, 5, new int[]{2,7,14,3,2},
				new int[]{128,128,64,64,64,64,64,64,64,32,32,32,32,32,32,32,32,32,32,32,32,32,32,16,16,16,8,8},
				new int[]{32,64,128,256,512}, new int[]{32,64,128,256,512});
		mLevel.setSpriteSheet(mSpriteSheet);
		mSpriteFactory = new MenuSpriteFactory(mLevel);

	//	mSpriteFactory.createBackgroundTile(6.25f, 3.75f, 28, 0.2f);
		mSpriteFactory.createHUDElements();
		// if(lvln==0){
		// createRandomTileMap(); TODO
		// }else{
		// createLevelFromFile(lvln, level);
		// mSpriteFactory.createHero(2, 3);
		// }

		// sprites += HUD.HUD_SPRITES;
	}

	@SuppressWarnings("unchecked")
	public void createLevelFromFile(int ln) {
		FileHandle jsonId = getFileHandle(ln);
		JsonReader reader=new JsonReader();
		String jsonString=jsonId.readString();
		Object o=reader.parse(jsonString);
		CreateLevelFromJsonObject((OrderedMap<String, Object>)o);


	}

	private FileHandle getFileHandle(int ln) {
		FileHandle levelID = null;

		if (ln == 1) {
			levelID = Gdx.files.internal("levels/RAWtest2.json");
		}

		return levelID;
	}

	@SuppressWarnings("unchecked")
	private void CreateLevelFromJsonObject(OrderedMap<String,Object> map){
		
		levelWidth = ((Float)map.get("width")).intValue();
		levelHeight = ((Float)map.get("height")).intValue();
		
		Array<?> tileSetO = (Array<?>)map.get("tilesets");
		OrderedMap<String, ?> tileSetProperties = (OrderedMap<String, ?>)tileSetO.items[0];
		@SuppressWarnings("unused")
		OrderedMap<String, ?> tileProperties = (OrderedMap<String, ?>)tileSetProperties.get("tileproperties");
		
		Array<?> layers = (Array<?>) map.get("layers");
		OrderedMap<String, ?> foregroundLayer = null;
		OrderedMap<String, ?> terrainLayer = null;
		OrderedMap<String, ?> actorsObjectsLayer = null;
		OrderedMap<String, ?> eventsLayer = null;
		OrderedMap<String, ?> background1Layer = null;
		OrderedMap<String, ?> background2Layer = null;
		OrderedMap<String, ?> background3Layer = null;
		Array<?> foregroundData = null;
		Array<?> terrainData = null;
		Array<?> actorsObjectsData = null;
		@SuppressWarnings("unused")
		Array<?> eventsObjects = null;
		Array<?> background1Data = null;
		Array<?> background2Data = null;
		Array<?> background3Data = null;
		
		OrderedMap<String, ?> tileLayer;
		String name;
		for(int i=0;i<layers.size;i++){
			tileLayer = (OrderedMap<String, ?>)layers.items[i];
			name = (String)tileLayer.get("name");
			if(name.equalsIgnoreCase("Foreground")){
				foregroundLayer = (OrderedMap<String, ?>)layers.items[i];
				foregroundData = (Array<?>)foregroundLayer.get("data");
			}else if(name.equalsIgnoreCase("Terrain")){
				terrainLayer = (OrderedMap<String, ?>)layers.items[i];
				terrainData = (Array<?>)terrainLayer.get("data");
			}else if(name.equalsIgnoreCase("Actors and Objects")){
				actorsObjectsLayer = (OrderedMap<String, ?>)layers.items[i];
				actorsObjectsData = (Array<?>)actorsObjectsLayer.get("data");
			}else if(name.equalsIgnoreCase("Events")){
				eventsLayer = (OrderedMap<String, ?>)layers.items[i];
				eventsObjects = (Array<?>)eventsLayer.get("objects");
			}else if(name.equalsIgnoreCase("Background")){
				background1Layer = (OrderedMap<String, ?>)layers.items[i];
				background1Data = (Array<?>)background1Layer.get("data");
			}else if(name.equalsIgnoreCase("Background2")){
				background2Layer = (OrderedMap<String, ?>)layers.items[i];
				background2Data = (Array<?>)background2Layer.get("data");
			}else if(name.equalsIgnoreCase("Background3")){
				background3Layer = (OrderedMap<String, ?>)layers.items[i];
				background3Data = (Array<?>)background3Layer.get("data");
			}
		}
		
		int[] foreground = new int[foregroundData.size];
		for(int i=0;i<foreground.length;i++){
			foreground[i] = ((Float)foregroundData.get(i)).intValue();		
		}
		int[] terrain = new int[terrainData.size];
		for(int i=0;i<terrain.length;i++){
			terrain[i] = ((Float)terrainData.get(i)).intValue();		
		}
		int[] actorsObjects = new int[actorsObjectsData.size];
		for(int i=0;i<actorsObjects.length;i++){
			actorsObjects[i] = ((Float)actorsObjectsData.get(i)).intValue();		
		}
		// TODO events
		int[] background1 = new int[background1Data.size];
		for(int i=0;i<background1.length;i++){
			background1[i] = ((Float)background1Data.get(i)).intValue();		
		}
		int[] background2 = new int[background2Data.size];
		for(int i=0;i<background2.length;i++){
			background2[i] = ((Float)background2Data.get(i)).intValue();		
		}
		int[] background3 = new int[background3Data.size];
		for(int i=0;i<background3.length;i++){
			background3[i] = ((Float)background3Data.get(i)).intValue();		
		}
		
		mSpriteFactory.createHUDElements();
	}
}
