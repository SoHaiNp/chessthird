package io.retronimia.thepromotion.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Pawn extends Piece{

    // 1. Ativos
    protected Texture spriteTexture;
    protected Sprite pieceSprite;

    // 2. Dados
    protected Vector2 position;

    public Pawn() {
        // 1. Ativos
        spriteTexture = new Texture("pawn.png");
        pieceSprite = new Sprite(spriteTexture);

        // 2. Dados
        position = new Vector2(0, 0);
    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        spriteBatch.draw(pieceSprite, position.x, position.y);
    }

    @Override
    public void move() {
        float speed = Gdx.graphics.getDeltaTime();

        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            position.y += 20 * speed;
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            position.y -= 20 * speed;
        } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            position.x -= 20 * speed;
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            position.x += 20 * speed;
        }
    }
}
