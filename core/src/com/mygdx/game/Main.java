package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

public class Main extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	private FreeTypeFontGenerator font;
	private FreeTypeFontGenerator.FreeTypeFontParameter font_parameter;
	private BitmapFont bitmap;
	private ShapeRenderer sr;
	private int threes, fours, fives = 0;
	private boolean top, middle, bottom, top2, middle2, bottom2 = false;
	private double average = 0;


	@Override
	public void create () {
		batch = new SpriteBatch();
		font = new FreeTypeFontGenerator(Gdx.files.internal("font_file.ttf"));
		font_parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		font_parameter.size = 50;
		font_parameter.color = Color.BLACK;
		bitmap = font.generateFont(font_parameter);
		sr = new ShapeRenderer();
	}

	@Override
	public void render () {
		ScreenUtils.clear(Color.SKY);			//background color

		sr.begin(ShapeRenderer.ShapeType.Filled);
		sr.setColor(Color.WHITE);
		sr.rect(400, 505, 50, 50);			//buttons for grade input
		sr.rect(400, 405, 50, 50);
		sr.rect(400, 305, 50, 50);

		sr.setColor(Color.LIGHT_GRAY);						//calculate button
		sr.rect(600, 350, 350, 75);

		sr.end();


		batch.begin();
		bitmap.draw(batch, "Enter Your 3s:", 30, 550);			//strings
		bitmap.draw(batch, "Enter Your 4s:", 30, 450);
		bitmap.draw(batch, "Enter Your 5s:", 30, 350);
		bitmap.draw(batch, "CALCULATE", 650, 405);
		bitmap.draw(batch, "AVERAGE:", 30, 250);

		bitmap.draw(batch, Integer.toString(threes), 400, 550);		//changes numbers on the buttons
		bitmap.draw(batch, Integer.toString(fours), 400, 450);
		bitmap.draw(batch, Integer.toString(fives), 400, 350);
		bitmap.draw(batch, average+"", 400, 250);

		if(top) {			//increments grade numbers with left click
			threes++;
			top = false;
		}
		if(middle) {
			fours++;
			middle = false;
		}
		if(bottom) {
			fives++;
			bottom = false;
		}

		if(top2) {			//decrements grade numbers with right click
			threes--;
			top2 = false;
		}
		if(middle2) {
			fours--;
			middle2 = false;
		}
		if(bottom2) {
			fives--;
			bottom2 = false;
		}

		batch.end();


		if(Gdx.input.getX() >= 400 && Gdx.input.getX() <= 450) {
			if(Gdx.input.isButtonJustPressed(0)) {
				if(Gdx.input.getY() >= 35 && Gdx.input.getY() <= 85)
					top = true;

				if(Gdx.input.getY() >= 135 && Gdx.input.getY() <= 185)
					middle = true;

				if(Gdx.input.getY() >= 235 && Gdx.input.getY() <= 285)
					bottom = true;
			}
			if(Gdx.input.isButtonJustPressed(1)) {
				if(Gdx.input.getY() >= 35 && Gdx.input.getY() <= 85)
					top2 = true;

				if(Gdx.input.getY() >= 135 && Gdx.input.getY() <= 185)
					middle2 = true;

				if(Gdx.input.getY() >= 235 && Gdx.input.getY() <= 285)
					bottom2 = true;
			}
		}

		if(Gdx.input.isButtonJustPressed(0)) {
			if(Gdx.input.getX() >= 600 && Gdx.input.getX() <= 950){
					calculate();
			}
		}

	}


	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}


	public void calculate() {
		double total = threes + fours + fives;
		average = (threes*3 + fours*4 + fives*5) / total;
		System.out.println(average);
	}
}
