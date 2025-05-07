package io.retronimia.thepromotion.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import io.retronimia.thepromotion.entity.Pawn;
import space.earlygrey.shapedrawer.ShapeDrawer;

public class DebugMap {

    // 1. Utilitários para Gráficos
    private ShapeDrawer shapeDrawer;

    // 2. Ativos
    private Rectangle[] mapLayers;
    private Vector2[] coordinates;

    // 3. Dados
    private static final float LAYER_WIDTH = 1440;
    private static final float LAYER_HEIGHT = 900;
    private int mapLayerToFocus = 0;

    public DebugMap(SpriteBatch spriteBatch) {
        // 1. Utilitários
        shapeDrawer = new ShapeDrawer(spriteBatch);

        coordinates = new Vector2[4];
        coordinates[0] = new Vector2(-1440, 0);
        coordinates[1] = new Vector2(0, 0);
        coordinates[2] = new Vector2(-1440, -900);
        coordinates[3] = new Vector2(0, -900);

        // 2. Ativos
        mapLayers = new Rectangle[4];
        mapLayers[0] = new Rectangle(coordinates[0].x, coordinates[0].y, LAYER_WIDTH, LAYER_HEIGHT);
        mapLayers[1] = new Rectangle(coordinates[1].x, coordinates[1].y, LAYER_WIDTH, LAYER_HEIGHT);
        mapLayers[2] = new Rectangle(coordinates[2].x, coordinates[2].y, LAYER_WIDTH, LAYER_HEIGHT);
        mapLayers[3] = new Rectangle(coordinates[3].x, coordinates[3].y, LAYER_WIDTH, LAYER_HEIGHT);
    }

    public void drawMapLayers(TextureRegion textureRegion) {
        shapeDrawer.setTextureRegion(textureRegion);

        for (Rectangle mapLayer : mapLayers) {
            for (Vector2 coordinate : coordinates) {
                shapeDrawer.rectangle(mapLayer, Color.BLACK, 10);
                shapeDrawer.filledCircle(mapLayer.getCenter(coordinate), 50);
            }
        }
    }

    public void debugFocusOnMapLayer(OrthographicCamera orthographicCamera) {
        if (Gdx.input.isKeyPressed(Input.Keys.NUMPAD_0)) {
            mapLayerToFocus = 0;
        } else if (Gdx.input.isKeyPressed(Input.Keys.NUMPAD_1)) {
            mapLayerToFocus = 1;
        } else if (Gdx.input.isKeyPressed(Input.Keys.NUMPAD_2)) {
            mapLayerToFocus = 2;
        } else if (Gdx.input.isKeyPressed(Input.Keys.NUMPAD_3)) {
            mapLayerToFocus = 3;
        }

        // Criar um Vetor que armazena a coordenada do alvo a ser centralizado
        Vector2 mapLayerCenterCoordinates = mapLayers[mapLayerToFocus].getCenter(coordinates[mapLayerToFocus]);
        Vector3 mapLayerCenter = new Vector3(mapLayerCenterCoordinates.x, mapLayerCenterCoordinates.y, 0);

        //Criar um Vetor que armazena a posição da câmera
        Vector3 currentCameraPosition = orthographicCamera.position;

        // Interpolar o Vetor de posição da câmera, usando como alvo o centro que será focado
        currentCameraPosition.lerp(mapLayerCenter, 0.1f);

        orthographicCamera.position.set(currentCameraPosition);
        // orthographicCamera.position.set(mapLayers[mapLayerToFocus].getCenter(coordinates[mapLayerToFocus]), 0);
    }

}
