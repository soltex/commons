/**
 * 
 */
package com.vanstone.gm.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.im4java.core.CompositeCmd;
import org.im4java.core.ConvertCmd;
import org.im4java.core.GMOperation;
import org.im4java.core.IM4JavaException;
import org.im4java.core.IMOperation;
import org.im4java.core.MontageCmd;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vanstone.common.MyAssert;
import com.vanstone.common.util.image.ImagePropertyVO;
import com.vanstone.common.util.image.ImageUtil;
import com.vanstone.gm.Direction;
import com.vanstone.gm.Gravity;
import com.vanstone.gm.IGMImageHandler;

/**
 * @author shipeng
 */
public class DefaultGMImageHanderImpl implements IGMImageHandler {

  private static Logger LOG = LoggerFactory.getLogger(DefaultGMImageHanderImpl.class);

  public DefaultGMImageHanderImpl() {
    LOG.info("Start Initial Default GM Image Handler.");
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.vanstone.aiyutian.gmimagehandler.IGMImagerHandler#scaleImage(java .io.File,
   * java.io.File, java.lang.Integer, java.lang.Integer)
   */
  @Override
  public boolean scaleImage(File source, File target, Integer width, Integer height) {
    return this.scaleImage(source, target, DEFAULT_QUALITY, width, height);
  }

  @Override
  public boolean scaleImage(File source, File target, Integer quality, Integer width, Integer height) {
    MyAssert.notNull(source);
    MyAssert.notNull(target);
    if (width == null && height == null) {
      width = DEFAULT_WIDTH;
      height = DEFAULT_HEIGHT;
    }
    IMOperation operation = new IMOperation();
    operation.thumbnail(width, height);
    operation.quality(new Double(quality.intValue()));
    operation.addImage();
    operation.addImage();
    ConvertCmd cmd = new ConvertCmd(true);
    try {
      cmd.run(operation, source.getPath(), target.getPath());
      return true;
    } catch (IOException e) {
      e.printStackTrace();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (IM4JavaException e) {
      e.printStackTrace();
    }
    return false;
  }

  @Override
  public boolean scaleImageExtent(File source, File target, Integer width, Integer height) {
    return this.scaleImageExtent(source, target, DEFAULT_QUALITY, width, height);
  }

  @Override
  public boolean scaleImageExtent(File source, File target, Integer quality, Integer width, Integer height) {
    MyAssert.notNull(source);
    MyAssert.notNull(target);
    MyAssert.notNull(quality);

    if (width == null && height == null) {
      width = DEFAULT_WIDTH;
      height = DEFAULT_HEIGHT;
    }
    IMOperation operation = new IMOperation();
    operation.thumbnail(width, height);
    operation.background("white");
    operation.gravity("center");
    operation.extent(width, height);
    operation.quality(new Double(quality.intValue()));
    operation.addImage();
    operation.addImage();

    ConvertCmd cmd = new ConvertCmd(true);
    try {
      cmd.run(operation, source.getPath(), target.getPath());
      return true;
    } catch (IOException e) {
      e.printStackTrace();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (IM4JavaException e) {
      e.printStackTrace();
    }
    return false;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.vanstone.aiyutian.gmimagehandler.IGMImagerHandler#scaleImageByWidth (java.io.File,
   * java.io.File, int)
   */
  @Override
  public boolean scaleImageByWidth(File source, File target, int width) {
    return this.scaleImage(source, target, DEFAULT_QUALITY, width, null);
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.vanstone.aiyutian.gmimagehandler.IGMImagerHandler#scaleImageByHeight (java.io.File,
   * java.io.File, int)
   */
  @Override
  public boolean scaleImageByHeight(File source, File target, int height) {
    return this.scaleImage(source, target, DEFAULT_QUALITY, null, height);
  }

  @Override
  public boolean scaleImageByWidth(File source, File target, Integer quality, int width) {
    return this.scaleImage(source, target, quality, width, null);
  }

  @Override
  public boolean scaleImageByHeight(File source, File target, Integer quality, int height) {
    return this.scaleImage(source, target, quality, null, height);
  }

  @Override
  public boolean scaleImage(File source, File target, Integer quality, String strScale) {
    MyAssert.notNull(source);
    MyAssert.notNull(target);
    IMOperation operation = new IMOperation();
    operation.quality(new Double(quality.intValue()));
    operation.thumbnail();
    operation.addImage();
    operation.addImage();
    operation.addImage();
    ConvertCmd cmd = new ConvertCmd(true);
    try {
      cmd.run(operation, strScale, source.getPath(), target.getPath());
      return true;
    } catch (IOException e) {
      e.printStackTrace();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (IM4JavaException e) {
      e.printStackTrace();
    }
    return false;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.vanstone.aiyutian.gmimagehandler.IGMImagerHandler#mergeImages(java .io.File[],
   * com.vanstone.aiyutian.gmimagehandler.Direction, int, int)
   */
  @Override
  public boolean mergeImages(File[] sourceFiles, File targetFile, Direction direction, int xspace, int yspace) {
    if (sourceFiles == null || sourceFiles.length <= 0 || targetFile == null) {
      throw new IllegalArgumentException();
    }
    int minwidth = Integer.MAX_VALUE;
    int minheight = Integer.MAX_VALUE;

    if (direction == null) {
      direction = Direction.Vertical;
    }

    for (File source : sourceFiles) {
      try {
        ImagePropertyVO vo = ImageUtil.getProperty(new FileInputStream(source));
        minwidth = Math.min(minwidth, vo.getWidth());
        minheight = Math.min(minheight, vo.getHeight());
      } catch (FileNotFoundException e) {
        e.printStackTrace();
        return false;
      }
    }

    if (direction.equals(Direction.Vertical)) {
      // 垂直
      // 开始缩放宽度
      for (File source : sourceFiles) {
        IMOperation operation = new IMOperation();
        operation.bordercolor("white");
        operation.border(xspace, yspace / 2);
        operation.thumbnail(minwidth, null);
        operation.addImage();
        operation.addImage();
        ConvertCmd cmd = new ConvertCmd(true);
        try {
          cmd.run(operation, source.getPath(), source.getPath());
        } catch (IOException e) {
          e.printStackTrace();
          return false;
        } catch (InterruptedException e) {
          e.printStackTrace();
          return false;
        } catch (IM4JavaException e) {
          e.printStackTrace();
          return false;
        }
      }
      // 合并 - montage
      GMOperation operation = new GMOperation();
      int count = sourceFiles.length;
      operation.tile("1x" + count);
      operation.geometry(null, null, 0, 0);
      Collection<String> paths = new ArrayList<String>();
      for (File source : sourceFiles) {
        operation.addImage();
        paths.add(source.getPath());
      }
      operation.addImage();
      paths.add(targetFile.getPath());
      MontageCmd cmd = new MontageCmd(true);

      try {
        cmd.run(operation, (Object[]) paths.toArray(new String[paths.size()]));
        return true;
      } catch (IOException e) {
        e.printStackTrace();
      } catch (InterruptedException e) {
        e.printStackTrace();
      } catch (IM4JavaException e) {
        e.printStackTrace();
      }
    }
    return false;
  }

  @Override
  public boolean qualityImage(File sourceFile, File targetFile, int quality) {
    if (sourceFile == null || targetFile == null || quality <= 0) {
      throw new IllegalArgumentException();
    }
    IMOperation operation = new IMOperation();
    operation.quality((double) quality);
    operation.addImage();
    operation.addImage();

    ConvertCmd convertCmd = new ConvertCmd(true);
    try {
      convertCmd.run(operation, (Object[]) new String[] {sourceFile.getPath(), targetFile.getPath()});
      return true;
    } catch (IOException e) {
      e.printStackTrace();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (IM4JavaException e) {
      e.printStackTrace();
    }
    return false;
  }

  @Override
  public boolean watermarkImage(File sourceFile, File watermartFile, File targetFile, Gravity gravity, int dissolve) {
    if (sourceFile == null || watermartFile == null || targetFile == null) {
      throw new IllegalArgumentException();
    }
    IMOperation operation = new IMOperation();
    operation.gravity(gravity.getCode());
    operation.dissolve(dissolve);
    operation.addImage();
    operation.addImage();
    operation.addImage();
    CompositeCmd cmd = new CompositeCmd(true);
    try {
      cmd.run(operation, new Object[] {watermartFile.getPath(), sourceFile.getPath(), targetFile.getPath()});
      return true;
    } catch (IOException e) {
      e.printStackTrace();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (IM4JavaException e) {
      e.printStackTrace();
    }
    return false;
  }

  public static final Pattern W_PATTERN = Pattern.compile("(\\d+)");
  public static final Pattern WH_PATTERN = Pattern.compile("(\\d+)x(\\d+)");
  public static final Pattern WHS_PATTERN = Pattern.compile("(\\d+)x(\\d+)s");
  public static final Pattern H_PATTERN = Pattern.compile("x(\\d+)");

  @Override
  public boolean scaleImageByPattern(File source, File target, String pattern, int quality) {
    MyAssert.notNull(source);
    MyAssert.notNull(target);

    Matcher w_matcher = W_PATTERN.matcher(pattern);
    if (w_matcher.matches()) {
      // 100模式，只指定宽度
      IMOperation operation = new IMOperation();
      String width = w_matcher.group(1);
      operation.resize(Integer.parseInt(width), null);
      operation.quality(new Double(quality));
      operation.addImage();
      operation.addImage();

      ConvertCmd cmd = new ConvertCmd(true);
      try {
        cmd.run(operation, source.getPath(), target.getPath());
        return true;
      } catch (IOException e) {
        e.printStackTrace();
      } catch (InterruptedException e) {
        e.printStackTrace();
      } catch (IM4JavaException e) {
        e.printStackTrace();
      }
      return false;
    }

    Matcher wh_pattern = WH_PATTERN.matcher(pattern);
    if (wh_pattern.matches()) {
      // 100x100模式，指定了宽度和高度
      IMOperation operation = new IMOperation();
      String width = wh_pattern.group(1);
      String height = wh_pattern.group(2);
      operation.thumbnail(Integer.parseInt(width), Integer.parseInt(height), "^");
      operation.quality(new Double(quality));
      operation.gravity("center");
      operation.extent(Integer.parseInt(width), Integer.parseInt(height));
      operation.addImage();
      operation.addImage();
      ConvertCmd cmd = new ConvertCmd(true);
      try {
        cmd.run(operation, source.getPath(), target.getPath());
        return true;
      } catch (IOException e) {
        e.printStackTrace();
      } catch (InterruptedException e) {
        e.printStackTrace();
      } catch (IM4JavaException e) {
        e.printStackTrace();
      }
      return false;
    }
    Matcher whs_pattern = WHS_PATTERN.matcher(pattern);
    if (whs_pattern.matches()) {
      // 100x100s，模式
      IMOperation operation = new IMOperation();
      String width = whs_pattern.group(1);
      String height = whs_pattern.group(2);
      operation.resize(Integer.parseInt(width), Integer.parseInt(height));
      operation.quality(new Double(quality));
      operation.addImage();
      operation.addImage();
      ConvertCmd cmd = new ConvertCmd(true);
      try {
        cmd.run(operation, source.getPath(), target.getPath());
        return true;
      } catch (IOException e) {
        e.printStackTrace();
      } catch (InterruptedException e) {
        e.printStackTrace();
      } catch (IM4JavaException e) {
        e.printStackTrace();
      }
      return false;
    }
    Matcher h_pattern = H_PATTERN.matcher(pattern);
    if (h_pattern.matches()) {
      // x100，模式
      IMOperation operation = new IMOperation();
      String height = h_pattern.group(1);
      operation.resize(null, Integer.parseInt(height));
      operation.quality(new Double(quality));
      operation.addImage();
      operation.addImage();
      ConvertCmd cmd = new ConvertCmd(true);
      try {
        cmd.run(operation, source.getPath(), target.getPath());
        return true;
      } catch (IOException e) {
        e.printStackTrace();
      } catch (InterruptedException e) {
        e.printStackTrace();
      } catch (IM4JavaException e) {
        e.printStackTrace();
      }
      return false;
    }
    return false;
  }

  @Override
  public boolean scaleImageByPattern(File source, File target, String pattern) {
    return scaleImageByPattern(source, target, pattern, DEFAULT_QUALITY);
  }

}
