package utils;

import java.lang.annotation.Annotation;

public class AnnotationUtils {

  public  <T extends Annotation> T getAnnotation(Class<T> annotationClass) {
    if (getClass().isAnnotationPresent(annotationClass)) {
      return getClass().getDeclaredAnnotation(annotationClass);
    }
    throw new RuntimeException(String.format("Class %s has no annotation @Path", getClass().getCanonicalName()));
  }
}
