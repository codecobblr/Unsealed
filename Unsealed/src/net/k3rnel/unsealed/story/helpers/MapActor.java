package net.k3rnel.unsealed.story.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public abstract class MapActor extends Image {	


    private TextureAtlas atlas;

    public MapActor() {

    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }
    @Override
    public void draw(SpriteBatch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }
    public TextureAtlas getAtlas() {
        if( atlas == null ) {
            atlas = new TextureAtlas( Gdx.files.internal( "image-atlases/pages-info.atlas" ) );
        }
        return atlas;
    }
}
