/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package archivosgrupo5;

import java.io.File;

/**
 *
 * @author Grupo5
 */
public class ArbolDirectorios {
    public static TreeMap creaArbolDeDirectorios (File rootDirectory) {
    TreeMap<String> root=null;
    if (rootDirectory.exists() && rootDirectory.isDirectory()) {
      root = new TreeMap(rootDirectory.getAbsolutePath(), rootDirectory.length());
      llenarArbolDirectorio(root);
    }
    return root;
  }
  private static void llenarArbolDirectorio(TreeMap<String> root) {
    File dir = new File(root.getRoot().getContent());
    File[] files = dir.listFiles();
    for (File file : files) {
      TreeMap tree = new TreeMap(file.getAbsolutePath(), file.length());
      root.getRoot().addChild(tree);
        
      if (file.isDirectory()) {
        llenarArbolDirectorio(tree);
      }
    }
  }
}
