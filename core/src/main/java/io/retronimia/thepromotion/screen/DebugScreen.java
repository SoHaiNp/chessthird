package io.retronimia.thepromotion.screen;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import io.retronimia.thepromotion.map.DebugMap;

public class DebugScreen implements Screen {

    // 1. Utilitários para Gráficos
    private SpriteBatch spriteBatch;
    private OrthographicCamera orthographicCamera;
    private Viewport viewport;

    // 2. Ativos
    private Texture mapTexture;
    private Sprite mapSprite;
    private DebugMap debugMap;

    // 3. Dados
//    private static final float WORLD_WIDTH = 5760;
    private static final float WORLD_WIDTH = 2880;
//    private static final float WORLD_HEIGHT = 3600;
    private static final float WORLD_HEIGHT = 1800;

    @Override
    public void show() {
        // 1. Gráficos
        spriteBatch = new SpriteBatch();
         orthographicCamera = new OrthographicCamera(WORLD_WIDTH / 2f, WORLD_HEIGHT / 2f);
//        orthographicCamera = new OrthographicCamera(WORLD_WIDTH, WORLD_HEIGHT);
        viewport = new FitViewport(orthographicCamera.viewportWidth, orthographicCamera.viewportHeight, orthographicCamera);

        // 2. Ativos
        mapTexture = new Texture("new_world_chess_board.png");
        mapSprite = new Sprite(mapTexture);
        debugMap = new DebugMap(spriteBatch);
    }

    @Override
    public void render(float delta) {
        // 1. Limpar o Buffer da tela
        ScreenUtils.clear(Color.RED);

        // 2. Atualizar a Matriz de Projeção
        spriteBatch.setProjectionMatrix(orthographicCamera.combined);
        debugMap.debugFocusOnMapLayer(orthographicCamera);
        orthographicCamera.update();

        // 3. Desenhar na tela
        spriteBatch.begin();
        spriteBatch.draw(mapSprite, -WORLD_WIDTH / 2f, -WORLD_HEIGHT / 2f);
        debugMap.drawMapLayers(mapSprite);
        spriteBatch.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
