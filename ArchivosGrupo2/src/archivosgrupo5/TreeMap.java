
package archivosgrupo5;

//import java.awt.Color;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author **********
 */
public class TreeMap {

  private final List<TreeMap> children = new ArrayList<>();
  private String name = null;
  private long size = 0;
  //private Color color;

  public TreeMap(final String name, final long size) {
    this.name = name;
    this.size = size;
  }

  public void addChild(final TreeMap child) {
    children.add(child);
  }

  public String getName() {
    return name;
  }

  public long getSize() {
    long totalSize = 0;
    if (children.isEmpty()) {
      totalSize = size;
    } else {
      for (TreeMap child : children) {
        totalSize += child.getSize();
      }
    }
    size = totalSize;
    return size;
  }

//  public Color getColor() {
//    return color;
//  }

//  public void setColor(final Color color) {
//    this.color = color;
//  }

  public List<TreeMap> getChildren() {
    return children;
  }
    
}
