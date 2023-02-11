package cellsociety.GUI;

import javafx.scene.paint.Color;

/**
 * @Author Han Zhang
 */


public abstract class VisualGrid {

  int width;
  int height;

  public VisualGrid(int Width, int Height) {
    width = Width;
    height = Height;
  }
  public abstract void updateGrid(int x, int y, String state);

//  public String chooseColor(String State){
//    //TODO change to resource bundle for different languages later or move to property file, very ugly
//
//  }

  public int getHeight() {
    return height;
  }

  public int getWidth() {
    return width;
  }
}
