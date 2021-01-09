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
    public static TreeMap creaArbolDeDirectorios(final File rootDirectory) {
    TreeMap root = null;
    if (rootDirectory.exists() && rootDirectory.isDirectory()) {
      root = new TreeMap(rootDirectory.getAbsolutePath(), rootDirectory.length());
      llenarArbolDirectorio(root);
    }
    return root;
  }

  private static void llenarArbolDirectorio(final TreeMap root) {
    final File dir = new File(root.getName());
    final File[] files = dir.listFiles();
    for (File file : files) {
      final TreeMap tree = new TreeMap(file.getAbsolutePath(), file.length());
      root.addChild(tree);
      if (file.isDirectory()) {
        llenarArbolDirectorio(tree);
      }
    }
  }
}
