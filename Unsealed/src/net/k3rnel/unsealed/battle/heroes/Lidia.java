/**
 * Unsealed: Whispers of Wisdom. 
 * 
 * Copyright (C) 2012 - Juan 'Nushio' Rodriguez
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License version 3 of 
 * the License as published by the Free Software Foundation
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */
package net.k3rnel.unsealed.battle.heroes;


import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.color;


import java.util.ArrayList;

import net.k3rnel.unsealed.battle.BattleEnemy;
import net.k3rnel.unsealed.battle.BattleEntity;
import net.k3rnel.unsealed.battle.BattleGrid;
import net.k3rnel.unsealed.battle.BattleHero;
import net.k3rnel.unsealed.battle.magic.Blast;
import net.k3rnel.unsealed.battle.magic.FireLion;
import net.k3rnel.unsealed.battle.magic.MagicEntity;
import net.k3rnel.unsealed.battle.magic.Shield;
import net.k3rnel.unsealed.battle.magic.Spikes;
import net.k3rnel.unsealed.battle.magic.IcePrison;
import net.k3rnel.unsealed.battle.magic.ThunderClaw;
import net.k3rnel.unsealed.battle.magic.Wind;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Timer.Task;

public class Lidia extends BattleHero {

    public Lidia(TextureAtlas atlas, int hp, int x, int y) {
        super(atlas, hp, x, y);
        AtlasRegion atlasRegion = atlas.findRegion("battle/entities/lidia");
        TextureRegion[][] lidia = atlasRegion.split(65,93);
        TextureRegion[] frames = new TextureRegion[5]; 
        frames[0] = lidia[0][0];
        frames[1] = lidia[0][1];
        frames[2] = lidia[0][2];
        frames[3] = lidia[0][3];
        frames[4] = lidia[0][4];
        Animation idle = new Animation(0.5f, frames);
        idle.setPlayMode(Animation.LOOP_PINGPONG);
        this.animations.put("idle", idle);
        Animation blocking = new Animation(1f,lidia[1][0]);
        blocking.setPlayMode(Animation.NORMAL);
        this.animations.put("blocking",blocking);
        frames = new TextureRegion[8]; 
        frames[0] = lidia[2][0];
        frames[1] = lidia[2][1];
        frames[2] = lidia[2][2];
        frames[3] = lidia[2][3];
        frames[4] = lidia[2][4];
        frames[5] = lidia[2][5];
        frames[6] = lidia[2][6];
        frames[7] = lidia[2][7];
        Animation attacking = new Animation(0.1f,frames);
        attacking.setPlayMode(Animation.NORMAL);
        this.animations.put("attacking",attacking);

        frames = new TextureRegion[7]; 
        frames[0] = lidia[3][0];
        frames[1] = lidia[3][1];
        frames[2] = lidia[3][2];
        frames[3] = lidia[3][3];
        frames[4] = lidia[3][4];
        frames[5] = lidia[3][5];
        frames[6] = lidia[3][6];
        attacking = new Animation(0.1f,frames);
        attacking.setPlayMode(Animation.NORMAL);
        this.animations.put("altattacking",attacking);
        frames = new TextureRegion[3]; 
        frames[0] = lidia[0][4];
        frames[1] = lidia[0][2];
        frames[2] = lidia[0][0];
        Animation release = new Animation(0.1f,frames);
        release.setPlayMode(Animation.NORMAL);
        this.animations.put("released",release);
        this.setState(BattleEntity.stateIdle);
    
        this.setWidth(65);
        this.setHeight(93);
        getShield();
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        if(currentAnimation.isAnimationFinished(stateTime)){
            switch(this.getState()){
                case BattleEntity.stateAttacking:
                    hit = false;
                    setState(BattleEntity.stateIdle);
                    break;
                case BattleEntity.stateAltAttacking:
                    hit = false;
                    setState(BattleEntity.stateIdle);
                    break;
                case BattleEntity.stateBlocking:
                    break;
            }
        }
        if(this.getState() == BattleEntity.stateAttacking){
            if(currentAnimation.animationDuration<stateTime+0.4f&&!hit){
                hit = true;
                switch(magicType){
                    case 0:
                        showBlast(true);
                        break;
                    case 1:
                        //                        showRyuKen(true); //Haha
                    case 2:
                        showGroundSpikes(true);                        
                        break;
                    case 3:
                        showFireLion(true);
                        break;
                    case 4:
                        showWind(true);
                        break;
                    case 5:
                        showWater(true);
                        break;
                    case 6:{
                        if(this.getGridYInt()==0){
                            showThunder(true,0);
                            showThunder(true,1);
                        }
                        else if(this.getGridYInt()==1){
                            showThunder(true,0);
                            showThunder(true,1);
                            showThunder(true,2);
                        }else{
                            showThunder(true,1);
                            showThunder(true,2);
                        }
                    }
                    break;

                }
            }
        }else if(this.getState() == BattleEntity.stateBlocking){
            shield.act(delta);
            getShield().setGrid(this.getGridXInt(),this.getGridYInt());
        }else if(this.getState() == BattleEntity.stateAltAttacking){
            if(currentAnimation.animationDuration<stateTime+0.3f&&!hit){
                hit = true;
                switch(magicType){
                    case 1:{

                        if(BattleGrid.checkGrid(this.getGridXInt()+1,this.getGridYInt())!=null){
                            BattleEntity entity = BattleGrid.checkGrid(this.getGridXInt()+1,this.getGridYInt());
                            if(entity instanceof BattleEnemy){
                                if(entity.getState() == BattleEntity.stateBlocking){
                                    if(entity.setHp(entity.getHp()-30)){
                                        BattleGrid.enemies.removeValue((BattleEnemy)entity,false);
                                        BattleGrid.clearGrid(entity.getGridXInt(),entity.getGridYInt());
                                        BattleGrid.checkState();
                                    }else{
                                        entity.setStatus(BattleEntity.statusBurned);
                                    }
                                }else{
                                    if(entity.setHp(entity.getHp()-60)){
                                        BattleGrid.enemies.removeValue((BattleEnemy)entity,false);
                                        BattleGrid.clearGrid(entity.getGridXInt(),entity.getGridYInt());
                                        BattleGrid.checkState();
                                    }else{
                                        entity.setStatus(BattleEntity.statusBurned);
                                    }
                                }
                            }
                        }
                        break;
                        
                    }
                    case 7:
                        if(BattleGrid.checkGrid(this.getGridXInt()+2,this.getGridYInt())!=null){
                            BattleEntity entity = BattleGrid.checkGrid(this.getGridXInt()+2,this.getGridYInt());
                            if(entity instanceof BattleEnemy){
                                if(entity.getState() == BattleEntity.stateBlocking){
                                    if(entity.setHp(entity.getHp()-30)){
                                        BattleGrid.enemies.removeValue((BattleEnemy)entity,false);
                                        BattleGrid.clearGrid(entity.getGridXInt(),entity.getGridYInt());
                                        BattleGrid.checkState();
                                    }else{
                                        entity.setStatus(BattleEntity.statusBurned);
                                    }
                                }else{
                                    if(entity.setHp(entity.getHp()-60)){
                                        BattleGrid.enemies.removeValue((BattleEnemy)entity,false);
                                        BattleGrid.clearGrid(entity.getGridXInt(),entity.getGridYInt());
                                        BattleGrid.checkState();
                                    }else{
                                        entity.setStatus(BattleEntity.statusBurned);
                                    }
                                }
                            }
                        }
                        break;

                }
            }
        }
        for(int i = 0; i< magics.size(); i++){
            if(magics.get(i).destroyMe){
                magics.remove(i);
                i--;
            }else{
                magics.get(i).act(delta);
            }
        }            

    }
    public void reset(){
        this.getActions().clear();
        this.actions = sequence( color(Color.WHITE) );
        this.addAction(actions);
        this.setStatus(BattleEntity.statusNormal);
        this.setState(BattleEntity.stateIdle);
        magics = new ArrayList<MagicEntity>();
    }
    public void showFireLion(boolean show){
        tmpMagic = new FireLion(atlas,4f,this);
        tmpMagic.setVisible(false);
        tmpMagic.offsetY = 0;
        tmpMagic.offsetX = 0;
        tmpMagic.maxDistance = 5;
        if(show){  
            tmpMagic.setGrid(this.getGridXInt()+1,this.getGridYInt());
        }
        tmpMagic.setVisible(show);
        magics.add(tmpMagic);
    }
    public void showBlast(boolean show){
        tmpMagic = new Blast(atlas,0,6.5f,this);
        tmpMagic.setVisible(false);
        tmpMagic.offsetY = -30;
        tmpMagic.offsetX = -(int)this.getWidth();
        if(show)
            tmpMagic.setGrid(this.getGridXInt(),this.getGridYInt());
        tmpMagic.setVisible(show);
        magics.add(tmpMagic);
    }

    public void showGroundSpikes(boolean show){
        tmpMagic = new Spikes(atlas,1,this);
        tmpMagic.setVisible(false);
        if(show)
            tmpMagic.setGrid(this.getGridXInt(),this.getGridYInt());
        tmpMagic.setVisible(show);
        magics.add(tmpMagic);
    }
    public void showWind(boolean show){
        tmpMagic = new Wind(atlas,this);
        tmpMagic.setVisible(false);
        if(show)
            tmpMagic.setGrid(this.getGridXInt(),this.getGridYInt());
        tmpMagic.setVisible(show);
        magics.add(tmpMagic);
    }
    public void showWater(boolean show){
        tmpMagic = new IcePrison(atlas,this);
        tmpMagic.setVisible(show);
        magics.add(tmpMagic);
    }
    public void showThunder(boolean show,int pos){
        tmpMagic = new ThunderClaw(atlas,this);
        tmpMagic.setVisible(show);
        tmpMagic.setGridY(pos);
        magics.add(tmpMagic);
    }
    //    public Cannonball getCannonball(){
    //        if(ball==null){
    //            ball = new Cannonball(atlas,0,1,this);
    //            ball.setVisible(false);
    //        }
    //        return ball;
    //    }
    //    public void showCannonball(boolean show){
    //        if(ball==null)
    //            getCannonball();
    //        ball.offsetY = -30;
    //        ball.offsetX = -(int)this.getWidth();
    //        ball.speedX = 0.01f;
    //        ball.isArc = true;
    //        ball.initialPos = this.getY();
    //        ball.maxDistance = this.getGridX()+2;
    //        if(show)
    //            ball.setGrid(this.getGridX(),this.getGridY());
    //        ball.setVisible(show);
    //    }
    public Shield getShield(){
        if(shield==null){
            shield = new Shield(atlas,this);
            shield.setVisible(false);
        }
        return shield;
    }

    @Override
    public void draw(SpriteBatch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        for(int i = 0; i< magics.size(); i++){
            magics.get(i).draw(batch,parentAlpha);
        }       
        if(this.getState()==BattleEntity.stateBlocking)
            shield.draw(batch, parentAlpha);
    }

    /**
     * @return the mana
     */
    public int getMana() {
        return mana;
    }
    /**
     * @param mana the mana to set
     */
    public void setMana(int mana) {
        this.mana = mana;
        if(this.mana<0)
            this.mana = 0;
        if(this.mana > 30)
            this.mana = 30;
    }
    @Override
    public Task nextTask() {
        currentTask = new Task() {
            @Override
            public void run() {
                switch(getState()){
                    case BattleEntity.stateBlocking:
                        if(getMana()>=1){
                            setMana(getMana()-1);
                        }else{
                            setMana(0);
                            setState(BattleEntity.stateIdle);
                        }
                        break;
                    default:
                        if(getMana()<30)
                            setMana(getMana()+1);
                        break;
                }

            }
        };
        return currentTask;
    }
}
