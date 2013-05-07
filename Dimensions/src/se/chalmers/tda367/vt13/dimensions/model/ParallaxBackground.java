package se.chalmers.tda367.vt13.dimensions.model;
import java.util.ArrayList;
import java.util.List;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
public class ParallaxBackground {
	
	private List<ParallaxLayer> layers;
	private float width,height;
	
	public ParallaxBackground(float width, float height){
		this.layers = new ArrayList<ParallaxLayer>();
		this.width = width;
		this.height = height;
	}
	
	public void render(float xPos,float yPos,SpriteBatch sprite){
		for(ParallaxLayer layer: layers){
			float layerOffset = (xPos * layer.getxRatio() % width);
			layer.render(xPos-width/2f - layerOffset,yPos-height/2f,width,height,sprite);
			layer.render(xPos-width/2f - layerOffset+5f,yPos-height/2f,width,height,sprite);

		}
	}
	
	public List <ParallaxLayer> getLayers(){
		return this.layers;
	}
	
	public float getWidth(){
		return this.width;
	}
	
	public float getHeight(){
		return this.height;
	}
	
	public void addLayer(ParallaxLayer layer){
		layers.add(layer);
	}
	

}
