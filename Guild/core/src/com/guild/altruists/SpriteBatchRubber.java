package com.guild.altruists;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
//Вспомогательный класс для корректной работы приложения при любом разрешении
public class SpriteBatchRubber {
    SpriteBatch batch;
    double wpw;
    double hph;

    public SpriteBatchRubber(Guild game, SpriteBatch batch) {
        this.batch = batch;
        wpw = game.wpw;
        hph = game.hph;
    }

    public void draw(Texture texture, float x, float y, float width, float height) {
        batch.draw(texture, (float) (x * wpw), (float) (y * hph), (float) (width * wpw), (float) (height * hph));
    }
    public void draw(Texture texture, float x, float y, float width, float height, boolean circle) {
        if (circle) {
            batch.draw(texture, (float) (x * wpw), (float) (y * hph), (float) (width * wpw), (float) (height * wpw));
        }
    }
    public void draw(TextureRegion region, float x, float y, float originX, float originY, float width, float height,
                     float scaleX, float scaleY, float rotation) {
        batch.draw(region, (float) (x * wpw), (float) (y * hph), (float) (originX * wpw), (float) (originY * hph), (float) (width * wpw), (float) (height * hph), scaleX, scaleY, rotation);
    }
    public void draw(TextureRegion region, float x, float y, float originX, float originY, float width, float height,
                     float scaleX, float scaleY, float rotation, boolean circle) {
        if (circle) {
            batch.draw(region, (float) (x * wpw), (float) (y * hph), (float) (originX * wpw), (float) (originY * wpw), (float) (width * wpw), (float) (height * wpw), scaleX, scaleY, rotation);
        }
    }
}
