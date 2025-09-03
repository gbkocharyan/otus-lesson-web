package utils;

import org.springframework.stereotype.Component;
import java.lang.annotation.Annotation;

@Component
public class AnnotationUtils {

  public <T extends Annotation> T getAnnotationInstance(Class<?> targetClass, Class<T> annotationClass) {
    if (targetClass.isAnnotationPresent(annotationClass)) {
      return targetClass.getDeclaredAnnotation(annotationClass);
    }
    throw new RuntimeException(String.format("Annotation @" + annotationClass.getSimpleName() + " is missing on " + targetClass.getCanonicalName()));
  }

}
