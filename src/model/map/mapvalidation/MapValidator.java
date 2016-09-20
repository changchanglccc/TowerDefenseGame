package model.map.mapvalidation;
/**
 * This is a interface named MapValidator, easy to be realized in different validators.  
 * 
 * @author LiChong
 *
 */
public interface  MapValidator {
/**
 * Validated the path      
 * 
 * @return path validation result 
 */
    boolean validate(); 
}
