package io.retronimia.thepromotion.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class Piece {

    abstract void draw(SpriteBatch spriteBatch);

    abstract void move();
}
